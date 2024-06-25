package com.yu.kickoff.controller;

import com.yu.kickoff.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class MatchController {
    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping(path = "/get-match-need-judge/referee/{refereeUsername}")
    public List<Map<String, Object>> getNeedJudgementMatch(@PathVariable String refereeUsername) {
        return matchService.getNeedJudgementMatch(refereeUsername);
    }

    @PostMapping(path = "/submit-referee-statistics-form")
    public void submitRefereeStatisticsForm(@RequestBody Map<String, Map<String, Object>> request) {
        matchService.submitRefereeStatisticsForm(request);
    }
}
