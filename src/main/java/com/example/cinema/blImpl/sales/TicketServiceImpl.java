package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.blImpl.management.hall.HallServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.Hall;
import com.example.cinema.po.ScheduleItem;
import com.example.cinema.po.Ticket;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    ScheduleServiceForBl scheduleService;
    @Autowired
    HallServiceForBl hallService;

    @Override
    @Transactional
    public ResponseVO addTicket(TicketForm ticketForm) {
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket;
        try{
            for (SeatForm seatForm:ticketForm.getSeats()){
                ticket = new Ticket();
                ticket.setUserId(ticketForm.getUserId());
                ticket.setScheduleId(ticketForm.getScheduleId());
                ticket.setColumnIndex(seatForm.getColumnIndex());
                ticket.setRowIndex(seatForm.getRowIndex());
                ticket.setState(0);
                tickets.add(ticket);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("锁座失败");
        }
        ticketMapper.insertTickets(tickets);
        return ResponseVO.buildSuccess();
    }

    @Override
    @Transactional
    public ResponseVO completeTicket(List<Integer> id, int couponId) {
        try{
            for (int idOfOne : id){
                ticketMapper.updateTicketState(idOfOne, 1);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("普通购票失败");
        }
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO getBySchedule(int scheduleId) {
        try {
            List<Ticket> tickets = ticketMapper.selectTicketsBySchedule(scheduleId);
            ScheduleItem schedule=scheduleService.getScheduleItemById(scheduleId);
            Hall hall=hallService.getHallById(schedule.getHallId());
            int[][] seats=new int[hall.getRow()][hall.getColumn()];
            tickets.stream().forEach(ticket -> {
                seats[ticket.getRowIndex()][ticket.getColumnIndex()]=1;
            });
            ScheduleWithSeatVO scheduleWithSeatVO=new ScheduleWithSeatVO();
            scheduleWithSeatVO.setScheduleItem(schedule);
            scheduleWithSeatVO.setSeats(seats);
            return ResponseVO.buildSuccess(scheduleWithSeatVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getTicketByUser(int userId) {
        try{
            List<Ticket> tickets = ticketMapper.selectTicketByUser(userId);
            ResponseVO responseVO = ResponseVO.buildSuccess(tickets);
            responseVO.setMessage("查询成功，该用户拥有以下"+tickets.size()+"张票");
            return responseVO;
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("查询用户已购票失败");
        }

    }

    @Override
    @Transactional
    public ResponseVO completeByVIPCard(List<Integer> id, int couponId) {
        try{
            for (int idOfOne : id){
                ticketMapper.updateTicketState(idOfOne, 1);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("VIP卡购票失败");
        }
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO cancelTicket(List<Integer> id) {
        try{
            for (int idOfOne : id){
                ticketMapper.updateTicketState(idOfOne, 2);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("取消购票失败");
        }
        return ResponseVO.buildSuccess();
    }



}
