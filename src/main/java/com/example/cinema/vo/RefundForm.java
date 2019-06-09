package com.example.cinema.vo;

import java.util.Date;

public class RefundForm {
    private int userId;
    private int ticketId;

    public RefundForm() {
    }

    public RefundForm(int userId, int ticketId) {
        this.userId = userId;
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

}
