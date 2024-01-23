package com.example.ebook.Entity.JSON;

import java.util.List;

public class consume {
    private List<consumeEntry> entrys;
    private int sum_book;
    private double sum_price;

    public consume() {

    }

    public List<consumeEntry> getEntrys() {
        return entrys;
    }

    public void setEntrys(List<consumeEntry> entrys) {
        this.entrys = entrys;
    }

    public int getSum_book() {
        return sum_book;
    }

    public void setSum_book(int sum_book) {
        this.sum_book = sum_book;
    }

    public double getSum_price() {
        return sum_price;
    }

    public void setSum_price(double sum_price) {
        this.sum_price = sum_price;
    }
}
