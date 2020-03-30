package com.aldrich_lin.pushbox.entity;

import java.sql.Date;

public class UserLeve {

    private String username;
    private int leve;
    private Date date;

    public UserLeve(String username, int leve, Date date){
        this.username = username;
        this.leve = leve;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLeve() {
        return leve;
    }

    public void setLeve(int leve) {
        this.leve = leve;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
