package com.yu.kickoff.model;

import jakarta.persistence.*;

@Entity
public class UpgradeAccountRequest {
    @Id
    @SequenceGenerator(
            name= "upgrade_account_request_sequence",
            sequenceName = "upgrade_account_request_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "upgrade_account_request_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Lob
    @Column(name = "id_card_face_1")
    private byte[] idCardFace1;

    @Lob
    @Column(name = "id_card_face_2")
    private byte[] idCardFace2;

    public UpgradeAccountRequest() {
    }

    public UpgradeAccountRequest(User author, byte[] idCardFace1, byte[] idCardFace2) {
        this.author = author;
        this.idCardFace1 = idCardFace1;
        this.idCardFace2 = idCardFace2;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public byte[] getIdCardFace1() {
        return idCardFace1;
    }

    public void setIdCardFace1(byte[] idCardFace1) {
        this.idCardFace1 = idCardFace1;
    }

    public byte[] getIdCardFace2() {
        return idCardFace2;
    }

    public void setIdCardFace2(byte[] idCardFace2) {
        this.idCardFace2 = idCardFace2;
    }
}
