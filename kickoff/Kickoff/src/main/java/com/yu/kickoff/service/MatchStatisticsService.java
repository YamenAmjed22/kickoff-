package com.yu.kickoff.service;

import com.yu.kickoff.model.Match;
import com.yu.kickoff.model.MatchRegisteration;
import com.yu.kickoff.model.MatchStatistics;
import com.yu.kickoff.model.User;
import com.yu.kickoff.repository.MatchStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchStatisticsService {
    private final MatchStatisticsRepository matchStatisticsRepository;
    private final UserService userService;
    private final ObjectService objectService;
    private final ScoreService scoreService;


    @Autowired
    public MatchStatisticsService(MatchStatisticsRepository matchStatisticsRepository,
                                  UserService userService,
                                  ObjectService objectService,
                                  ScoreService scoreService) {
        this.matchStatisticsRepository = matchStatisticsRepository;
        this.userService = userService;
        this.objectService = objectService;
        this.scoreService = scoreService;
    }

    public Map<String, Object> getTotalStatisticsByUser(String username) {

        User user = userService.getUserByUsername(username);

        Map<String, Object> statistics = matchStatisticsRepository.findSumsByUserName(username);
        return statistics;
    }

    public Long getUserTotalScore(String username) {
        User user = userService.getUserByUsername(username);

        Map<String, Object> statistics = getTotalStatisticsByUser(username);

        Set<String> scoresToBeCalculated = new HashSet<>();
        Collections.addAll(scoresToBeCalculated,
                "goals",
                "yellowCard",
                "redCard",
                "fouls",
                "motm");

        long totalScore = 0;

        for(Map.Entry<String, Object> entry : statistics.entrySet()) {
            if (scoresToBeCalculated.contains(entry.getKey())) {
                if (objectService.getLongValue(statistics, entry.getKey()) < 0L) continue; // Match requires judgement.
                totalScore += objectService.getLongValue(statistics, entry.getKey()) * scoreService.getScoreByTitle(entry.getKey()).getScore();
            }
        }

        return totalScore;
    }

    public Map<String, Object> getUserStatisticsByUsername(String username) {
        Map<String, Object> response = matchStatisticsRepository.findSumsByUserName(username);
        response.put("scores", getUserTotalScore(username));

        return response;
    }

    public List<Map<String, Object>> getMatchHistoryByUsername(String username) {
        List<Map<String, Object>> response = new ArrayList<>();
        List<MatchStatistics> matchStatistics = matchStatisticsRepository.findAllByUserName(username);
        for (var item : matchStatistics) {
            Map<String, Object> itemData = new HashMap<>();

            if (item.getGoals() < 0L) continue; // Match requires judgement.

            itemData.put("goals", item.getGoals());
            itemData.put("yellowCard", item.getYellowCard());
            itemData.put("redCard", item.getRedCard());
            itemData.put("fouls", item.getFouls());
            itemData.put("motm", item.getMotm());

            long score = 0;
            for (Map.Entry<String, Object> entry : itemData.entrySet()) {
                score +=  objectService.getLongValue(itemData, entry.getKey()) * scoreService.getScoreByTitle(entry.getKey()).getScore();
            }

            itemData.put("score", score);
            itemData.put("position", item.getPosition());
            itemData.put("location", item.getMatchId().getPitchId().getAddress());
            itemData.put("referee", item.getMatchId().getRefereeId().getUsername());
            itemData.put("date", item.getMatchId().getTimestamp().toLocalDateTime().toLocalDate());

            response.add(itemData);
        }

        return response;
    }

    public void migrate(Match match, MatchRegisteration matchRegisteration) {
        MatchStatistics matchStatistics = new MatchStatistics(
            match,
            matchRegisteration.getUserName(),
            -1L,
            -1L,
            -1L,
            -1L,
            -1L,
            matchRegisteration.getPosition(),
            matchRegisteration.getTeamNumber(),
            matchRegisteration.getPositionNumber()
        );

        matchStatisticsRepository.save(matchStatistics);
    }
}
