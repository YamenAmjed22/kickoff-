package com.yu.kickoff.controller;

import com.yu.kickoff.repository.MatchScheduleRepository;
import com.yu.kickoff.service.MatchScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class MatchScheduleController {

    private final MatchScheduleRepository matchScheduleRepository;
    private final MatchScheduleService matchScheduleService;

    @Autowired
    public MatchScheduleController(MatchScheduleRepository matchScheduleRepository,
                                   MatchScheduleService matchScheduleService) {
        this.matchScheduleRepository = matchScheduleRepository;
        this.matchScheduleService = matchScheduleService;
    }

    @GetMapping("/get-schedule/{pitchId}")
    public List<Map<String, Object>> getAllSchedulesByPitchId(@PathVariable Long pitchId) {
        return matchScheduleService.getAllSchedulesByPitchId(pitchId);
    }

    @PostMapping("/create-schedule")
    public void createSchedule(@RequestBody Map<String, Object> request) {
        matchScheduleService.createSchedule(request);
    }

    @DeleteMapping("/delete-schedule")
    public void deleteSchedule(@RequestBody Map<String, Object> request) {
        matchScheduleService.deleteSchedule(request);
    }
}
