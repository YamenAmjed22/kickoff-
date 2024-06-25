package com.yu.kickoff.model;

import jakarta.persistence.*;


@Entity
public class NotificationMessage {
    // ATT
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
    private String text ;
    // no para constructor //
    public NotificationMessage() {
    }
    // all para constructor //
    public NotificationMessage(Long id, String text) {
        this.id = id;
        this.text = text;
    }
    // setter and getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
