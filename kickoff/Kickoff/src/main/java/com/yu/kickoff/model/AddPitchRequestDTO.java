package com.yu.kickoff.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class AddPitchRequestDTO {

    private String username;
    private String cityName ;
    private String pitchName;
    private Double price;
    private String address;
    private byte[] ownershipDocumentation;

    public AddPitchRequestDTO(String username, String cityName, String pitchName, Double price, String address, byte[] ownershipDocumentation) {
        this.username = username;
        this.cityName = cityName;
        this.pitchName = pitchName;
        this.price = price;
        this.address = address;
        this.ownershipDocumentation = ownershipDocumentation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPitchName() {
        return pitchName;
    }

    public void setPitchName(String pitchName) {
        this.pitchName = pitchName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getOwnershipDocumentation() {
        return ownershipDocumentation;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setOwnershipDocumentation(byte[] ownershipDocumentation) {
        this.ownershipDocumentation = ownershipDocumentation;
    }
}
