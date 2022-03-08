package com.example.furniture.model;

import javax.persistence.*;

@Entity
@Table(name = "stock_piece")
public class StockPiece {

    @Id
    @Column(name = "id_sotck_piece")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double cost;
    private char status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_piece")
    private Piece piece;

    @Override
    public String toString() {
        return "StockPiece{" +
                "id=" + id +
                ", cost=" + cost +
                ", status=" + status +
                ", piece=" + piece +
                '}';
    }

    public StockPiece(){}

    public StockPiece(Integer id, double cost, char status, Piece piece) {
        this.id = id;
        this.cost = cost;
        this.status = status;
        this.piece = piece;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}