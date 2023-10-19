package com.example.ebook.Entity.JSON;

import com.example.ebook.Entity.Book;

public class consumeEntry {

    private Book book;
    private int num;

    public consumeEntry() {

    }

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

    public void add(int n) {
        this.num = this.num + n;
    }
}
