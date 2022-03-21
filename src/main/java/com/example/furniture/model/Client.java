package com.example.furniture.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "client")
public class Client implements Serializable {
    @Id
    @Column(name = "nit")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nit;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;

    public Client(Integer nit, String name, String address) {
        this.nit = nit;
        this.name = name;
        this.address = address;
    }

    public Client(){}

    @Override
    public String toString() {
        return "Client{" +
                    "nit=" + nit +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                '}';
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}