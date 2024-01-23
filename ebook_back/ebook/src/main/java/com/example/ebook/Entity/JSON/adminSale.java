package com.example.ebook.Entity.JSON;

import com.example.ebook.Entity.Book;

public class adminSale {
    private Book book;
    private int num;

    public int rank;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void add(){
        this.num=this.num+1;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
