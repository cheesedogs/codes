package com.example.cinema.po;

import lombok.Data;

import java.util.Date;

/**
 * @author zjy
 * @data 2019/6/9
 */

@Data
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

}
