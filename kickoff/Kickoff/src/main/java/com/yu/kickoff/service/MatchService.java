package com.yu.kickoff.service;

import com.yu.kickoff.model.Match;
import com.yu.kickoff.model.MatchStatistics;
import com.yu.kickoff.model.Pitch;
import com.yu.kickoff.model.User;
import com.yu.kickoff.repository.MatchRepository;
import com.yu.kickoff.repository.MatchStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final UserService userService;
    private final MatchStatisticsRepository matchStatisticsRepository;
    private final ObjectService objectService;

    @Autowired
    public MatchService(MatchRepository matchRepository,
                        UserService userService,
                        MatchStatisticsRepository matchStatisticsRepository,
                        ObjectService objectService) {
        this.matchRepository = matchRepository;
        this.userService = userService;
        this.matchStatisticsRepository = matchStatisticsRepository;
        this.objectService = objectService;
    }

    public Match createMatch(Timestamp time, Pitch pitch, User referee) {
        Match match = new Match(
                time,
                pitch,
                referee
        );

        match = matchRepository.save(match);

        return match;
    }

    public List<Map<String, Object>> getNeedJudgementMatch(String refereeName) {
        List<Map<String, Object>> respond = new ArrayList<>();

        User referee = userService.getUserByUsername(refereeName);
        Optional<Match> match = matchRepository.findTopByRefereeIdOrderByTimestampDesc(referee);

        if (!match.isPresent()) return respond;

        List<MatchStatistics> statistics = matchStatisticsRepository.findByMatchId(match.get());
        if (statistics.get(0).getGoals() > -1) return respond;



        for (MatchStatistics statistic : statistics) {


            Map<String, Object> item = new HashMap<>();
            item.put("matchId", match.get().getId());
            item.put("username", statistic.getUserName().getUsername());
            item.put("name", statistic.getUserName().getFirstName() + " " + statistic.getUserName().getLastName());
            item.put("position", statistic.getPosition().name());
            item.put("team", statistic.getTeamNumber());

            respond.add(item);
        }

        return respond;
    }

    public void submitRefereeStatisticsForm(Map<String, Map<String, Object>> request) {
        for (var item : request.entrySet()) {
            User user = userService.getUserByUsername(item.getKey());
            Match match = matchRepository.getById(objectService.getLongValue(item.getValue(), "matchId"));

            MatchStatistics matchStatistics = matchStatisticsRepository.findByUserNameAndMatchId(user, match);

            Long yelloCard = objectService.getLongValue(item.getValue(), "yellowCard");
            Long redCard = objectService.getLongValue(item.getValue(), "redCard");
            Long goals = objectService.getLongValue(item.getValue(), "goals");
            Long fouls = objectService.getLongValue(item.getValue(), "fouls");
            Long motm = objectService.getLongValue(item.getValue(), "motm");

            matchStatistics.setYellowCard(yelloCard);
            matchStatistics.setRedCard(redCard);
            matchStatistics.setGoals(goals);
            matchStatistics.setFouls(fouls);
            matchStatistics.setMotm(motm);

            matchStatisticsRepository.save(matchStatistics);
        }
    }
}
