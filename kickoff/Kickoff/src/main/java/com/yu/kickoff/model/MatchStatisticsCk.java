package com.yu.kickoff.model;

import java.io.Serializable;

public class MatchStatisticsCk implements Serializable {
    private Match matchId ;
    private User userName ;
    // no para cons //
    public MatchStatisticsCk() {
    }
    // all para cons //
    public MatchStatisticsCk(Match matchId, User user) {
        this.matchId = matchId;
        this.userName = user;
    }
    // setter and getter //

    public Match getMatchId() {
        return matchId;
    }

    public void setMatchId(Match matchId) {
        this.matchId = matchId;
    }

    public User getUser() {
        return userName;
    }

    public void setUser(User user) {
        this.userName = user;
    }
}
