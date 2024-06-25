package com.yu.kickoff.model;

import java.io.Serializable;

public class MatchRegisterationCk implements Serializable {
    private MatchSchedule matchScheduleId ;
    private User userName ;
    // constructors //
    public MatchRegisterationCk() {
    }
    // ALL para
    public MatchRegisterationCk(MatchSchedule matchScheduleId, User userName) {
        this.matchScheduleId = matchScheduleId;
        this.userName = userName;
    }
    // getter and setter //
    public MatchSchedule getMatchScheduleId() {
        return matchScheduleId;
    }

    public void setMatchScheduleId(MatchSchedule matchScheduleId) {
        this.matchScheduleId = matchScheduleId;
    }

    public User getUserName() {
        return userName;
    }

    public void setUserName(User userName) {
        this.userName = userName;
    }
}
