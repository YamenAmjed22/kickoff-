package com.yu.kickoff.service;

import com.yu.kickoff.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeaderBoardService {

    private final UserService userService;
    private final MatchStatisticsService matchStatisticsService;
    private final ObjectService objectService;

    @Autowired
    public LeaderBoardService(UserService userService,
                              MatchStatisticsService matchStatisticsService,
                              ObjectService objectService) {
        this.userService = userService;
        this.matchStatisticsService = matchStatisticsService;
        this.objectService = objectService;
    }

    public List<Map<String, Object>> getLeaderBoardRows () {
        List<User> users = userService.getAllUsers();

        List<Map<String, Object>> leaderboard = new ArrayList<>();

        // Populate the leaderboard with user details and placeholder scores
        for (User user : users) {
            Map<String, Object> row = new HashMap<>();
            row.put("name", user.getFirstName() + " " + user.getLastName());
            row.put("username", user.getUsername());
            row.put("score", matchStatisticsService.getUserTotalScore(user.getUsername())); // Placeholder for the score
            leaderboard.add(row);
        }

        return leaderboard;

    }


}
