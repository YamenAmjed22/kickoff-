package com.yu.kickoff.model;

import jakarta.persistence.*;

@Entity
public class AddPitchRequest {
    @Id
    @SequenceGenerator(
            name= "add_pitch_request_sequence",
            sequenceName = "add_pitch_request_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "add_pitch_request_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    private String pitchName;
    private Double price;
    private String address;

    @Lob
    @Column(name = "ownership_documentation")
    private byte[] ownershipDocumentation;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city ;

    public AddPitchRequest() {
    }

    public AddPitchRequest(User author, String pitchName, Double price, String address, byte[] ownershipDocumentation, City city) {
        this.author = author;
        this.pitchName = pitchName;
        this.price = price;
        this.address = address;
        this.ownershipDocumentation = ownershipDocumentation;
        this.city = city;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public byte[] getOwnershipDocumentation() {
        return ownershipDocumentation;
    }

    public void setOwnershipDocumentation(byte[] ownershipDocumentation) {
        this.ownershipDocumentation = ownershipDocumentation;
    }
}
