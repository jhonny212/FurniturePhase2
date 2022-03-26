package com.example.furniture.util;

import com.example.furniture.model.Furniture;

public class FurnitureM {

    private Furniture furniture;
    private double amount;

    public FurnitureM(Furniture furniture, double amount) {
        this.furniture = furniture;
        this.amount = amount;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
