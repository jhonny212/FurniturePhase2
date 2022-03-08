package com.example.furniture.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bill_details")
public class BillDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bill",nullable = false)
    private Bill bill;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_furniture",nullable = false)
    private Furniture furniture;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false,name="date_return")
    private java.util.Date dateReturn;
    @Column(nullable = false,name="price_sale")
    private double priceSale;

    public BillDetails(Integer id, Bill bill, Furniture furniture, Date dateReturn, double priceSale) {
        this.id = id;
        this.bill = bill;
        this.furniture = furniture;
        this.dateReturn = dateReturn;
        this.priceSale = priceSale;
    }

    public BillDetails(){}

    @Override
    public String toString() {
        return "BillDetails{" +
                "id=" + id +
                ", bill=" + bill +
                ", furniture=" + furniture +
                ", dateReturn=" + dateReturn +
                ", priceSale=" + priceSale +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public Date getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    public double getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(double priceSale) {
        this.priceSale = priceSale;
    }
}