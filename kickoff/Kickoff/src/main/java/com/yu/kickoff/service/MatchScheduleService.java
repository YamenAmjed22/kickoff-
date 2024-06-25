package com.yu.kickoff.service;

import com.yu.kickoff.model.*;
import com.yu.kickoff.repository.MatchRegisterationRespository;
import com.yu.kickoff.repository.MatchScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class MatchScheduleService {
    private final MatchScheduleRepository matchScheduleRepository;
    private final PitchService pitchService;
    private final ObjectService objectService;
    private final MatchService matchService;
    private final MatchRegisterationRespository matchRegisterationRespository;
    private final MatchStatisticsService matchStatisticsService;

    @Autowired
    public MatchScheduleService(MatchScheduleRepository matchScheduleRepository,
                                PitchService pitchService,
                                ObjectService objectService,
                                MatchService matchService,
                                MatchRegisterationRespository matchRegisterationRespository,
                                MatchStatisticsService matchStatisticsService) {
        this.matchScheduleRepository = matchScheduleRepository;
        this.pitchService = pitchService;
        this.objectService = objectService;
        this.matchService = matchService;
        this.matchRegisterationRespository = matchRegisterationRespository;
        this.matchStatisticsService = matchStatisticsService;
    }

    public MatchSchedule getScheduleById(Long id) {
        return matchScheduleRepository.findById(id).orElseThrow(() -> new IllegalStateException("No schedule with the given id."));
    }

    public List<Map<String, Object>> getAllSchedulesByPitchId(Long pitchId) {

        List<Map<String, Object>> response = new ArrayList<>();

        Pitch pitch = pitchService.getPitchByPitchId(pitchId);

        List<MatchSchedule> matchScheduleList = matchScheduleRepository.findByPitchId(pitch);

        for (MatchSchedule schedule : matchScheduleList) {
            Map<String, Object> item = new HashMap<>();

            Time timestamp = schedule.getStartTime();

            // Format for 24-hour system hour (HH:mm)
            SimpleDateFormat sdfHour = new SimpleDateFormat("HH:mm");

            // Formatting Timestamp to 24-hour system hour
            String formattedTime = sdfHour.format(timestamp);

            item.put("id", schedule.getId());
            item.put("time", formattedTime);
            item.put("day", schedule.getDay());
            item.put("state", schedule.getStatus());
            item.put("pitchId", schedule.getPitchId().getId());

            response.add(item);
        }

        return response;
    }

    public long countMatchSchedulesWithinTimeRange(Time givenTime, String givenDay) {
        Time startTimeBefore = new Time(givenTime.getTime() - (2 * 3600 * 1000 - 1));
        Time startTimeAfter = new Time(givenTime.getTime() + (2 * 3600 * 1000 - 1));
        return matchScheduleRepository.countMatchSchedulesWithinTimeRange(startTimeBefore, startTimeAfter, givenDay);
    }


    public void createSchedule(Map<String, Object> request) {

        Long pitchId = objectService.getLongValue(request, "pitchId");

        Pitch pitch = pitchService.getPitchByPitchId(pitchId);

        Timestamp timestamp = null;

        String dateString = objectService.getStringValue(request, "timestamp");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parsedDate = dateFormat.parse(dateString);
            long millis = parsedDate.getTime();
            timestamp = new Timestamp(millis);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Time time = new Time(timestamp.getTime());
        String day = objectService.getStringValue(request, "day");

        if (countMatchSchedulesWithinTimeRange(time, day) > 0) {
            throw new IllegalStateException("There is match before at least two hours !!!!");
        }
        MatchSchedule matchSchedule = new MatchSchedule(
                time,
                pitch,
                day
        );

        matchScheduleRepository.save(matchSchedule);

    }

    public void deleteSchedule(Map<String, Object> request) {
        matchScheduleRepository.deleteById(objectService.getLongValue(request, "id"));
    }

    @Transactional
    public void startMigration(Timestamp startTime) {
        startTime.setNanos(0);

        List<MatchSchedule> matchSchedulesList = matchScheduleRepository.findAllByStartTime(new Time(startTime.getTime()));

        for (MatchSchedule matchSchedule : matchSchedulesList) {

            String todayName = LocalDate.now().getDayOfWeek().name().toLowerCase();
            todayName = todayName.substring(0, 1).toUpperCase() + todayName.substring(1);

            if (!matchSchedule.getDay().equals(todayName)) {
                System.out.println("Not today's match");
                continue; // not today's match
            }

            // // // step 1 (create match):

            List<MatchRegisteration> registerationsList = matchRegisterationRespository.findAllByMatchScheduleId(matchSchedule);

            if (registerationsList.size() != 11) {
                matchRegisterationRespository.deleteByMatchScheduleId(matchSchedule);
                System.out.println("Not 11 players");
                continue;
            }

            User referee = null;
            for (MatchRegisteration registeration : registerationsList) {
                if (registeration.getPosition() == PositionEnum.REFEREE) {
                    referee = registeration.getUserName();
                    break;
                }
            }

            // Impossible case (at least in my mind)
            if (referee == null) {
                matchRegisterationRespository.deleteByMatchScheduleId(matchSchedule);
                System.out.println("No referee here !");
                continue;
            }

            // create match
            Match match = matchService.createMatch(startTime, matchSchedule.getPitchId(), referee);

            // // // step 2 (fill match statistics):
            for (MatchRegisteration registeration : registerationsList) {
                if (registeration.getUserName().getId() != referee.getId()) // Migrate players only (not referee).
                    matchStatisticsService.migrate(match, registeration);
            }

            // // // step 3 (delete relevant registrations)
            matchRegisterationRespository.deleteByMatchScheduleId(matchSchedule);
        }

        System.out.println("-----------------------------");
        System.out.println("Migration finished !");
        System.out.println("-----------------------------");
    }
}