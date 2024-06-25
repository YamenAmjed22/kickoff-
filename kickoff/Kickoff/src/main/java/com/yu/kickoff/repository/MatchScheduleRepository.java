package com.yu.kickoff.repository;

import com.yu.kickoff.model.ContactUs;
import com.yu.kickoff.model.MatchSchedule;
import com.yu.kickoff.model.Pitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface MatchScheduleRepository extends JpaRepository<MatchSchedule,Long> {
    List<MatchSchedule> findByPitchId(Pitch pitch);

    @Query("SELECT COUNT(ms) FROM MatchSchedule ms WHERE ms.startTime BETWEEN :startTimeMinus2Hours AND :startTimePlus2Hours")
    long countMatchesWithin4Hours(@Param("startTimeMinus2Hours") Timestamp startTimeMinus2Hours, @Param("startTimePlus2Hours") Timestamp startTimePlus2Hours);

    @Query("SELECT COUNT(ms) FROM MatchSchedule ms " +
            "WHERE ms.day = :givenDay " +
            "AND (" +
            "(ms.startTime >= :startTimeBefore AND ms.startTime <= :startTimeAfter) OR " +
            "(ms.startTime <= :startTimeBefore AND ms.startTime >= :startTimeAfter)" +
            ")")
    long countMatchSchedulesWithinTimeRange(@Param("startTimeBefore") Time startTimeBefore,
                                            @Param("startTimeAfter") Time startTimeAfter,
                                            @Param("givenDay") String givenDay);

    List<MatchSchedule> findAllByStartTime(Time startTime);
}
