package com.example.furniture.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "profile")
public class Profile implements Serializable{
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(nullable = false,name="first_name")
    private String firstName;
    @Column(nullable = false,name="last_name")
    private String lastName;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;
    @Column(nullable = false,name="user_type")
    private Integer userType;
    @Column(nullable = true)
    private boolean status=true;

    public Profile(Integer id, String username, String firstName, String lastName, String password, Integer userType) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userType = userType;
        this.status=true;
    }

    public Profile(){
        this.status=true;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}