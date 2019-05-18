package com.example.cinema.blImpl.sales;

import com.example.cinema.po.Ticket;

import java.util.Date;
import java.util.List;

public interface TicketServiceForBl {

    public List<Ticket> getTicketByDate(Date startDate, Date endDate);


}
