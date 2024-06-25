package com.yu.kickoff.service;

import com.yu.kickoff.model.MatchRegisteration;
import com.yu.kickoff.model.MatchSchedule;
import com.yu.kickoff.model.PositionEnum;
import com.yu.kickoff.model.User;
import com.yu.kickoff.repository.MatchRegisterationRespository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchRegisterationService {
    private final MatchRegisterationRespository matchRegisterationRespository;
    private final MatchScheduleService matchScheduleService;
    private final ObjectService objectService;
    private final UserService userService;
    private final MatchStatisticsService matchStatisticsService;
    private final RelationsService relationsService;


    @Autowired
    public MatchRegisterationService(MatchRegisterationRespository matchRegisterationRespository,
                                     MatchScheduleService matchScheduleService,
                                     ObjectService objectService,
                                     UserService userService,
                                     MatchStatisticsService matchStatisticsService,
                                     RelationsService relationsService) {
        this.matchRegisterationRespository = matchRegisterationRespository;
        this.matchScheduleService = matchScheduleService;
        this.objectService = objectService;
        this.userService = userService;
        this.matchStatisticsService = matchStatisticsService;
        this.relationsService = relationsService;
    }

    public List<Map<String, Object>> getAllMatchesByUsername(Long pitchId, String username) {
        List<Map<String, Object>> response = new ArrayList<>();

        List<Map<String, Object>> scheduleList = matchScheduleService.getAllSchedulesByPitchId(pitchId);

        for (Map<String, Object> match : scheduleList) {
            Map<String, Object> data = new HashMap<>();
            Map<Long, Map<String, Object>> positions = new HashMap<>();
            data.put("id", match.get("id"));
            data.put("time", match.get("time"));
            data.put("day", match.get("day"));
            data.put("state", match.get("state"));

            MatchSchedule matchSchedule = matchScheduleService.getScheduleById(
                    objectService.getLongValue(match, "id")
            );

            List<MatchRegisteration> registeredList = matchRegisterationRespository.findAllByMatchScheduleId(matchSchedule);
            Long scoreSum = 0L;

            for (MatchRegisteration register : registeredList) {
                Map<String, Object> userData = new HashMap<>();
                User targetUser = register.getUserName();
                scoreSum += matchStatisticsService.getUserTotalScore(targetUser.getUsername());

                userData.put("username", targetUser.getUsername());
                userData.put("position_number", register.getPositionNumber());
                userData.put("team_number", register.getTeamNumber());
                userData.put("position", register.getPosition().name());

                User sourceUser = userService.getUserByUsername(username);
                Object relationType = relationsService.getRelationType(sourceUser, targetUser);

                userData.put("relation_type", relationType);

                positions.put(register.getPositionNumber(), userData);

                String profileImage = "";
                if (register.getUserName().getProfileImage() != null) {
                    profileImage = Base64.getEncoder().encodeToString(register.getUserName().getProfileImage());
                }

                userData.put("profileImage", profileImage);

            }

            data.put("score_sum", scoreSum);
            data.put("positions", positions);
            data.put("registered_players", positions.size());

            response.add(data);
        }

        return response;
    }

    public List<MatchRegisteration> getAllMatchRegisterationsByMatchSchedule(MatchSchedule matchSchedule) {
        return matchRegisterationRespository.findAllByMatchScheduleId(matchSchedule);
    }

    public void registerForMatch(Map<String, Object> request) {
        Long scheduleId = objectService.getLongValue(request, "scheduleId");
        String username = objectService.getStringValue(request, "username");
        MatchSchedule matchSchedule = matchScheduleService.getScheduleById(scheduleId);
        User user = userService.getUserByUsername(username);

        PositionEnum positionEnum = null;
        if (objectService.getStringValue(request, "position").equals(PositionEnum.REFEREE.name())) positionEnum = PositionEnum.REFEREE;
        else if (objectService.getStringValue(request, "position").equals(PositionEnum.GOAL_KEEPER.name())) positionEnum = PositionEnum.GOAL_KEEPER;
        else if (objectService.getStringValue(request, "position").equals(PositionEnum.NORMAL_PLAYER.name())) positionEnum = PositionEnum.NORMAL_PLAYER;

        MatchRegisteration matchRegisteration = new MatchRegisteration(
                matchSchedule,
                user,
                objectService.getLongValue(request, "team_number"),
                positionEnum,
                objectService.getLongValue(request, "position_number")
        );

        matchRegisterationRespository.save(matchRegisteration);
    }

    @Transactional
    public void deleteRegisteration(Long scheduleId, String username) {
        MatchSchedule matchSchedule = matchScheduleService.getScheduleById(scheduleId);
        User user = userService.getUserByUsername(username);

        matchRegisterationRespository.deleteByMatchScheduleIdAndUserName(matchSchedule, user);
    }

    @Transactional
    public void deleteRegisteration(MatchSchedule matchSchedule) {
        matchRegisterationRespository.deleteByMatchScheduleId(matchSchedule);
    }


}
