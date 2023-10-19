package com.example.ebook.Entity.JSON;

import com.example.ebook.Entity.User;

public class adminConsume {

    private User user;
    private double spend;
    private int rank;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getSpend() {
        return spend;
    }

    public void setSpend(double spend) {
        this.spend = spend;
    }

    public void add(double tmp){
        this.spend=this.spend+tmp;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
