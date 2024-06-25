package com.yu.kickoff.model;

public class UpgradeAccountRequestDTO {
    private String username;
    private byte[] idCardFace1;
    private byte[] idCardFace2;

    public UpgradeAccountRequestDTO() {
    }

    public UpgradeAccountRequestDTO(String username, byte[] idCardFace1, byte[] idCardFace2) {
        this.username = username;
        this.idCardFace1 = idCardFace1;
        this.idCardFace2 = idCardFace2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
