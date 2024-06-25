package com.yu.kickoff.service;

import com.yu.kickoff.model.Score;
import com.yu.kickoff.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoreService {
    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public Score getScoreByTitle(String title) {
        Score score = scoreRepository.findByTitle(title).orElseThrow(() -> new IllegalStateException("Wrong Score title !"));
        return score;
    }
}
