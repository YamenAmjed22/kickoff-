package com.yu.kickoff.controller;

import com.yu.kickoff.service.LeaderBoardService;
import com.yu.kickoff.service.ScoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class ScoreController {
    private final LeaderBoardService leaderBoardService;
    private final ScoreService scoreService;

    public ScoreController(LeaderBoardService leaderBoardService, ScoreService scoreService) {
        this.leaderBoardService = leaderBoardService;
        this.scoreService = scoreService;
    }

    @GetMapping
    public List<Map<String, Object>>  getLeaderBoardRows() {
        return leaderBoardService.getLeaderBoardRows();
    }
}
