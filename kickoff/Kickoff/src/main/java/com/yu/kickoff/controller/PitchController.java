package com.yu.kickoff.controller;

import com.yu.kickoff.model.City;
import com.yu.kickoff.model.Pitch;
import com.yu.kickoff.repository.PitchRepository;
import com.yu.kickoff.service.PitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class PitchController {

    private final PitchRepository pitchRepository;
    private final PitchService pitchService;

    @Autowired
    public PitchController(PitchRepository pitchRepository,
                           PitchService pitchService) {
        this.pitchRepository = pitchRepository;
        this.pitchService = pitchService;
    }

    @GetMapping("/get-all-pitches")
    public List<Map<String, Object>> getAllCitiesNames() {
        return pitchService.getAllPitches();
    }
}
