package com.example.furniture.model;

import javax.persistence.*;

@Entity
@Table(name = "piece")
public class Piece {

    @Id
    @Column(name = "id_piece")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double price;
    private Integer stock;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category")
    private Piece piece;

    public Piece(Integer id, String name, double price, Integer stock, Piece piece) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.piece = piece;
    }

    public Piece() {

    }

    @Override
    public String toString() {
        return "Piece{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", piece=" + piece +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}