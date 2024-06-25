package com.yu.kickoff.model;

import java.io.Serializable;

public class RelationCk implements Serializable {
    private User sourceUserName ;
    private User targetUserName ;
    // no para cons //
    public RelationCk() {
    }
    // all para const //
    public RelationCk(User sourceUserName, User targetUserName) {
        this.sourceUserName = sourceUserName;
        this.targetUserName = targetUserName;
    }
    // setter and getter //
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
}
