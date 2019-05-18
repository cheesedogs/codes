package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.blImpl.management.hall.HallServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.blImpl.promotion.ActivityServiceImpl;
import com.example.cinema.data.management.MovieMapper;
import com.example.cinema.data.management.ScheduleMapper;
import com.example.cinema.data.promotion.ActivityMapper;
import com.example.cinema.data.promotion.CouponMapper;
import com.example.cinema.data.promotion.VIPCardMapper;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.*;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@Service
public class TicketServiceImpl implements TicketService ,TicketServiceForBl{

    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    ScheduleServiceForBl scheduleService;
    @Autowired
    HallServiceForBl hallService;

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    ScheduleMapper scheduleMapper;

    @Autowired
    MovieMapper movieMapper;

    @Autowired
    VIPCardMapper vipCardMapper;

    @Override
    @Transactional
    public ResponseVO addTicket(TicketForm ticketForm) {
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket;
        try {
            for (SeatForm seatForm : ticketForm.getSeats()) {
                ticket = new Ticket();
                ticket.setUserId(ticketForm.getUserId());
                ticket.setScheduleId(ticketForm.getScheduleId());
                ticket.setColumnIndex(seatForm.getColumnIndex());
                ticket.setRowIndex(seatForm.getRowIndex());
                ticket.setState(0);
                tickets.add(ticket);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("锁座失败");
        }
        ticketMapper.insertTickets(tickets);
//        int ticketId;
//        List<Integer> ticketsIds = new ArrayList<>();
//        for (Ticket t : tickets) {
//            ticketId = t.getId();
//            ticketsIds.add(ticketId);
//        }
//        ticketMapper.selectTicketById();
        return ResponseVO.buildSuccess("锁座成功");
    }

    @Override
    @Transactional
    public ResponseVO completeTicket(List<Integer> id, int couponId) {
        try {
            Ticket ticket;

            for (int idOfOne : id) {
                ticket = ticketMapper.selectTicketById(idOfOne);

                //检验优惠券
                checkCouponValidated(ticket, couponId);

                //判断是否符合活动
                checkActivities(ticket);

                ticketMapper.updateTicketState(idOfOne, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("普通购票失败" + e.getMessage());
        }
        return ResponseVO.buildSuccess("普通购票成功");
    }

    @Override
    public ResponseVO getBySchedule(int scheduleId) {
        try {
            List<Ticket> tickets = ticketMapper.selectTicketsBySchedule(scheduleId);
            ScheduleItem schedule = scheduleService.getScheduleItemById(scheduleId);
            Hall hall = hallService.getHallById(schedule.getHallId());
            int[][] seats = new int[hall.getRow()][hall.getColumn()];
            tickets.stream().forEach(ticket -> {
                seats[ticket.getRowIndex()][ticket.getColumnIndex()] = 1;
            });
            ScheduleWithSeatVO scheduleWithSeatVO = new ScheduleWithSeatVO();
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
        try {
            List<Ticket> tickets = ticketMapper.selectTicketByUser(userId);
            ResponseVO responseVO = ResponseVO.buildSuccess(tickets);
            responseVO.setMessage("查询成功，该用户拥有以下" + tickets.size() + "张票");
            return responseVO;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("查询用户已购票失败");
        }
    }

    @Override
    @Transactional
    public ResponseVO completeByVIPCard(List<Integer> id, int couponId) {
        Ticket ticket;
        int UserId = ticketMapper.selectTicketById(id.get(0)).getUserId();
        double totalFare = 0;
        double discount = 0;
        try {
            for (int idOfOne : id) {
                ticket = ticketMapper.selectTicketById(idOfOne);

                //检验优惠券
                discount = checkCouponValidated(ticket, couponId);

                //判断是否符合活动
                checkActivities(ticket);

                totalFare += scheduleMapper.selectScheduleById(ticket.getScheduleId()).getFare();
                ticketMapper.updateTicketState(idOfOne, 1);
            }
            totalFare -= discount;
            if(vipCardMapper.selectCardByUserId(UserId).getBalance() < totalFare) {
                throw new Exception("VIP卡余额不足，请充值！");
            }
            vipCardMapper.updateCardBalance(vipCardMapper.selectCardByUserId(UserId).getId(), vipCardMapper.selectCardByUserId(UserId).getBalance() - totalFare);
            return ResponseVO.buildSuccess("VIP卡购票成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("VIP卡购票失败" + e.getMessage());
        }
    }

    @Override
    public ResponseVO cancelTicket(List<Integer> id) {
        try {
            for (int idOfOne : id) {
                if (ticketMapper.selectTicketById(idOfOne).getState() != 2) {
                    ticketMapper.updateTicketState(idOfOne, 2);
                } else {
                    throw new Exception("票未锁座");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("取消购票失败，原因:" + e.getMessage());
        }
        return ResponseVO.buildSuccess("取消购票成功");
    }

    private double checkCouponValidated(Ticket ticket, int couponId) throws Exception {
        if (couponId == 0) {
            return 0;
        }
        Coupon coupon = couponMapper.selectById(couponId);
        double dicount = 0;
        int UserId = ticket.getUserId();
        //判断优惠券是否有效
        Timestamp ticketTimeStamp;
        ticketTimeStamp = ticket.getTime();
        if (ticketTimeStamp.compareTo(coupon.getStartTime()) < 0
                || ticketTimeStamp.compareTo(coupon.getEndTime()) > 0) {
            System.out.println(ticketTimeStamp);
            System.out.println(coupon.getStartTime());
            System.out.println(coupon.getEndTime());
            throw new Exception("优惠券时间失效");
        }
        dicount = couponMapper.selectById(couponId).getDiscountAmount();
        couponMapper.deleteCouponUser(couponId, UserId);
        return dicount;
    }

    private void checkActivities(Ticket ticket) {
        Timestamp ticketTimeStamp = ticket.getTime();
        ScheduleItem schedule = scheduleMapper.selectScheduleById(ticket.getScheduleId());
        List<Activity> activities = activityMapper.selectActivitiesByMovie(schedule.getMovieId());
        for (Activity a : activities) {
            if (ticketTimeStamp.compareTo(a.getStartTime()) > 0
                    && ticketTimeStamp.compareTo(a.getEndTime()) < 0) {
                couponMapper.insertCouponUser(a.getCoupon().getId(), ticket.getUserId());
            }
        }
    }

    @Override
    public List<Ticket> getTicketByDate(Date startDate, Date endDate) {
        return ticketMapper.selectTicketByDate(startDate,endDate);
    }
}
