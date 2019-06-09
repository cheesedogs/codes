package com.example.cinema.po;

import java.util.Date;

/**
 * @author zjy
 * @data 2019/6/9
 */
public class ChargeRecord {
    private int id;
    private int userId;
    private Date time;
    private double amount;

    public ChargeRecord() {
    }

    public ChargeRecord(int id, int userId, Date time, double amount) {
        this.id = id;
        this.userId = userId;
        this.time = time;
        this.amount = amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public Date getTime() {
        return time;
    }

    public double getAmount() {
        return amount;
    }
}
