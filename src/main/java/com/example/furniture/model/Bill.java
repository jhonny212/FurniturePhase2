package com.example.furniture.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @Column(name = "id_bill")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private java.util.Date dateTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private Profile profile;
    private Integer nit;
    private double total;

    public Bill(){}

    public Bill(Integer id, Date date_time, Profile profile, Integer nit, double total) {
        this.id = id;
        this.dateTime = date_time;
        this.profile = profile;
        this.nit = nit;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", date_time=" + dateTime+
                ", profile=" + profile +
                ", nit=" + nit +
                ", total=" + total +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate_time() {
        return dateTime;
    }

    public void setDate_time(Date date_time) {
        this.dateTime = date_time;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}