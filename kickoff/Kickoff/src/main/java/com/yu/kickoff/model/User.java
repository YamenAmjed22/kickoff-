package com.yu.kickoff.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "uzer", indexes = @Index(name = "idx_username", columnList = "username"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "mid_name")
    private String midName;
    @Column(name = "last_name")
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob ;
    private String address ;
    @Column(name = "password")
    private String password;

    @Column(unique = true)
    private String phoneNumber;
    private byte[] idCardOne ;
    private byte[] idCardTwo ;
    private byte[] profileImage ;
    @Enumerated(value = EnumType.STRING)
    private RoleEnum role;
    @Enumerated(value = EnumType.STRING)
    private  Status status ;
    private Timestamp timestamp;

    public User() {
    }

    public User(String username, String firstName, String midName, String lastName, LocalDate dob, String address, String password, String phoneNumber, City city) {
        this.username = username;
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.city = city;
        this.role = RoleEnum.USER;
        this.status = Status.ACTIVE;
        this.profileImage = new byte[0];
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city ;

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getIdCardOne() {
        return idCardOne;
    }

    public void setIdCardOne(byte[] idCardOne) {
        this.idCardOne = idCardOne;
    }

    public byte[] getIdCardTwo() {
        return idCardTwo;
    }

    public void setIdCardTwo(byte[] idCardTwo) {
        this.idCardTwo = idCardTwo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
}
