package com.yu.kickoff.threads;

import com.yu.kickoff.service.MatchScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class WatchdogThread {

    private final MatchScheduleService matchScheduleService;

    @Autowired
    public WatchdogThread(MatchScheduleService matchScheduleService) {
        this.matchScheduleService = matchScheduleService;
    }

    private Timestamp getTimestampBefore2Hours () {
        LocalDateTime now = LocalDateTime.now();

        // Subtract 2 hours from the current time
        LocalDateTime twoHoursBefore = now.minusHours(2);

        // Convert to Timestamp for comparison
        Timestamp twoHoursBeforeTimestamp = Timestamp.valueOf(twoHoursBefore);

        return twoHoursBeforeTimestamp;
    }

    @Scheduled(cron = "0 0 * * * *") // This cron expression means "At minute 0 past every hour"
    public void startMigration() {
        Timestamp time = getTimestampBefore2Hours();

        matchScheduleService.startMigration(time);

        System.out.println("-----------------------------");
        System.out.println(
                "Migration started at: "
                + new Time(time.getTime())
                + " FOR ALL Schedules WHERE start_time BEFORE 2-HOURS."
        );
        System.out.println("-----------------------------");
    }
}
