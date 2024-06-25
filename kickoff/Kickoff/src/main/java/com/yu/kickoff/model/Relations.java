package com.yu.kickoff.model;

import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@IdClass(RelationCk.class)
public class Relations {
    @Id
    @ManyToOne
    @JoinColumn(name = "source_user_name" , referencedColumnName = "id" , nullable = false)
    private User sourceUserName ;
    @Id
    @ManyToOne
    @JoinColumn(name = "target_user_name" , referencedColumnName = "id" , nullable = false)
    private User targetUserName ;

    @Enumerated(value = EnumType.STRING)
    private RelationEnum type ;
    private Timestamp timestamp ;
    // no para const //
    public Relations() {
    }
    // all para cons //
    public Relations(User sourceUserName, User targetUserName, RelationEnum type, Timestamp timestamp) {
        this.sourceUserName = sourceUserName;
        this.targetUserName = targetUserName;
        this.type = type;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
    //setter and getter //
    public User getSourceUserName() {
        return sourceUserName;
    }

    public void setSourceUserName(User sourceUserName) {
        this.sourceUserName = sourceUserName;
    }

    public User getTargetUserName() {
        return targetUserName;
    }

    public void setTargetUserName(User targetUserName) {
        this.targetUserName = targetUserName;
    }

    public RelationEnum getType() {
        return type;
    }

    public void setType(RelationEnum type) {
        this.type = type;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}
