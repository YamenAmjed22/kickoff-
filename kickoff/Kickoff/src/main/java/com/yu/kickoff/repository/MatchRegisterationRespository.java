package com.yu.kickoff.repository;

import com.yu.kickoff.model.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRegisterationRespository extends JpaRepository<MatchRegisteration, MatchRegisterationCk> {
    @Query("SELECT mr FROM MatchRegisteration mr WHERE mr.matchScheduleId = :matchScheduleId")
    List<MatchRegisteration> findAllByMatchScheduleId(@Param("matchScheduleId") MatchSchedule matchScheduleId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MatchRegisteration mr WHERE mr.matchScheduleId = :matchScheduleId AND mr.userName = :userName")
    void deleteByMatchScheduleIdAndUserName(@Param("matchScheduleId") MatchSchedule matchScheduleId, @Param("userName") User userName);

    List<MatchRegisteration> findByMatchScheduleId(MatchSchedule matchSchedule);

    @Transactional
    void deleteByMatchScheduleId(MatchSchedule matchSchedule);
}
