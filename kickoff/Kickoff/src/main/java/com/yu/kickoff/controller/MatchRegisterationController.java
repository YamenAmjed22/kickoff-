package com.yu.kickoff.controller;

import com.yu.kickoff.service.MatchRegisterationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class MatchRegisterationController {
    private final MatchRegisterationService matchRegisterationService;

    @Autowired
    public MatchRegisterationController(MatchRegisterationService matchRegisterationService) {
        this.matchRegisterationService = matchRegisterationService;
    }

    @GetMapping(path = "/get-all-matches/pitch/{pitchId}/user/{username}")
    public List<Map<String, Object>> getAllMatchesByUsername(
            @PathVariable Long pitchId,
            @PathVariable String username
    ) {
        return matchRegisterationService.getAllMatchesByUsername(pitchId, username);
    }

    @PostMapping(path = "/register-in-match")
    public void registerForMatch(
            @RequestBody Map<String, Object> request
    ) {
        matchRegisterationService.registerForMatch(request);
    }

    @DeleteMapping(path = "/delete-registration-in-match/schedule/{scheduleId}/user/{username}")
    public void deleteRegisteration(
            @PathVariable Long scheduleId,
            @PathVariable String username
    ) {
        matchRegisterationService.deleteRegisteration(scheduleId, username);
    }
}
