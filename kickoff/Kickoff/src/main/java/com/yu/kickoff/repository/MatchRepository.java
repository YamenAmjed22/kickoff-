package com.yu.kickoff.repository;

import com.yu.kickoff.model.Match;
import com.yu.kickoff.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    @Query("SELECT m FROM Match m WHERE m.refereeId = :referee ORDER BY m.timestamp DESC")
    Optional<Match> findTopByRefereeIdOrderByTimestampDesc(@Param("referee") User referee);

}
