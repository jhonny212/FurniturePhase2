package com.example.furniture.model;

import javax.persistence.*;

@Entity
@Table(name = "assign_furniture_piece")
public class AssignFurniturePiece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_stock_piece")
    private StockPiece stockPiece;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_furniture")
    private Furniture furniture;

    public AssignFurniturePiece(Integer id, StockPiece stockPiece, Furniture furniture) {
        this.id = id;
        this.stockPiece = stockPiece;
        this.furniture = furniture;
    }

    public AssignFurniturePiece(){}

    @Override
    public String toString() {
        return "AssignFurniturePiece{" +
                "id=" + id +
                ", stockPiece=" + stockPiece +
                ", furniture=" + furniture +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StockPiece getStockPiece() {
        return stockPiece;
    }

    public void setStockPiece(StockPiece stockPiece) {
        this.stockPiece = stockPiece;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }
}