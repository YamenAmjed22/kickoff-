package com.yu.kickoff.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class ContactUs {
    @Id
    @SequenceGenerator(
            name= "contacts_sequence",
            sequenceName = "contacts_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contacts_sequence"
    )
    private Long id ;
    @Column(name = "first_name")
    private String firstName ;
    @Column(name = "mid_name")
    private String midName ;
    @Column(name = "last_name")
    private String lastName ;
    private String address1 ;
    private String address2 ;
    private String message ;
    private Timestamp timestamp ;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city ;
    // no para const //
    public ContactUs() {
    }
    // all para const //

    public ContactUs(String firstName, String midName, String lastName, String address1, String address2, String message, City city) {
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
        this.address1 = address1;
        this.address2 = address2;
        this.message = message;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.city = city;
    }
    // getter and setter //
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address) {
        this.address1 = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address) {
        this.address2 = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
