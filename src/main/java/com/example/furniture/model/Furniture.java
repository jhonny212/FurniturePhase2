package com.example.furniture.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "furniture")
public class Furniture {
    @Id
    private Integer code;
    private String name;
    private double price;
    private double cost;
    @Temporal(TemporalType.DATE)
    private java.util.Date creationDate;
    @Lob
    private String description;
    @Lob
    private String path;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private Profile profile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan")
    private Plan plan;
    private Integer status;

    public Furniture(Integer code, String name, double price, double cost, Date creationDate, String description, String path, Profile profile, Plan plan, Integer status) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.cost = cost;
        this.creationDate = creationDate;
        this.description = description;
        this.path = path;
        this.profile = profile;
        this.plan = plan;
        this.status = status;
    }

    public Furniture(){}

    @Override
    public String toString() {
        return "Furniture{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", cost=" + cost +
                ", creationDate=" + creationDate +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", profile=" + profile +
                ", plan=" + plan +
                ", status=" + status +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}