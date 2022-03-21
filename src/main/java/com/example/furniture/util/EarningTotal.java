package com.example.furniture.util;

public class EarningTotal {

    private int sales, lost, earnings;

    public EarningTotal() {
    }

    public EarningTotal(int sales, int lost, int earnings) {
        this.sales = sales;
        this.lost = lost;
        this.earnings = earnings;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getEarnings() {
        return earnings;
    }

    public void setEarnings(int earnings) {
        this.earnings = earnings;
    }
}
