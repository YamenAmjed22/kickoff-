package com.yu.kickoff.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class MatchSchedule {
    @Id
    @SequenceGenerator(
            name= "id",
            sequenceName = "id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id"
    )
    private Long id ;

    @Column(name = "start_time")
    private Time startTime ;

    private String status ;

    private String day ;

    private Timestamp timestamp ;

    @ManyToOne
    @JoinColumn(name = "pitch_id")
    private Pitch pitchId ;

    @JsonIgnore
    @OneToMany(mappedBy = "matchScheduleId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatchRegisteration> matchRegisterationList;

    public MatchSchedule() {
    }
    public MatchSchedule(Time startTime, Pitch pitchId, String day) {
        this.day = day;
        this.startTime = startTime;
        this.status = "ACTIVE";
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.pitchId = pitchId;
    }
    // setter and getter //
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Pitch getPitchId() {
        return pitchId;
    }

    public void setPitchId(Pitch pitchId) {
        this.pitchId = pitchId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
