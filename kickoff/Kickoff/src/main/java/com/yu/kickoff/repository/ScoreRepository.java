package com.yu.kickoff.repository;

import com.yu.kickoff.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ScoreRepository extends JpaRepository<Score, String> {
    Optional<Score> findByTitle(String title);
}
