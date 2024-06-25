package com.yu.kickoff.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
@Entity
@IdClass(MatchRegisterationCk.class)
public class MatchRegisteration {
    @Id
    @ManyToOne
    @JoinColumn(name = "match_schedule_id" , referencedColumnName = "id" , nullable = false)
    private MatchSchedule matchScheduleId ;
    @Id
    @ManyToOne
    @JoinColumn(name = "user_name" , referencedColumnName = "id" , nullable = false)
    private User userName ;
    @Column(name = "team_number")
    private  Long TeamNumber ;

    @Column(name = "position_number")
    private  Long positionNumber ;
    private Timestamp timestamp ;

    @Enumerated(value = EnumType.STRING)
    PositionEnum position;
    // no para const //
    public MatchRegisteration() {
    }
    // all para const //
    public MatchRegisteration(MatchSchedule matchScheduleId, User userName, Long teamNumber, PositionEnum position, Long positionNumber) {
        this.matchScheduleId = matchScheduleId;
        this.userName = userName;
        TeamNumber = teamNumber;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.position = position;
        this.positionNumber = positionNumber;
    }
    // setter and getter //
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

    public Long getTeamNumber() {
        return TeamNumber;
    }

    public void setTeamNumber(Long teamNumber) {
        TeamNumber = teamNumber;
    }

    public Long getPositionNumber() {
        return positionNumber;
    }

    public void setPositionNumber(Long positionNumber) {
        this.positionNumber = positionNumber;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public PositionEnum getPosition() {
        return position;
    }

    public void setPosition(PositionEnum position) {
        this.position = position;
    }
}
