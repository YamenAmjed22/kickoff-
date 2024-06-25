package com.yu.kickoff.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Pitch {
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
    private String name ;
    private Double price ;
    private Double rate ;
    private String status ;
    private String address;
    @Column(name ="ownership_document" )
    private byte[] ownershipDocument ;
    private Timestamp timestamp ;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author ;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city ;
    // relation with MatchSchedule //
    // relation with match //
    // relation with SpecialReservation //
    // no para const //
    public Pitch() {
    }
    // all para const //

    public Pitch(String name, Double price, byte[] ownershipDocument, String address, User author, City city) {
        this.name = name;
        this.price = price;
        this.status = "ACTIVE";
        this.ownershipDocument = ownershipDocument;
        this.address = address;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.author = author;
        this.city = city;
    }
    // getter and setter //
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getOwnershipDocument() {
        return ownershipDocument;
    }

    public void setOwnershipDocument(byte[] ownershipDocument) {
        this.ownershipDocument = ownershipDocument;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public User getAuthorId() {
        return author;
    }

    public void setAuthorId(User authorId) {
        this.author = authorId;
    }

    public City getCityId() {
        return city;
    }

    public void setCityId(City cityId) {
        this.city = cityId;
    }
}
