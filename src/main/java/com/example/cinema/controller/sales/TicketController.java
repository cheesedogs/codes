package com.example.cinema.controller.sales;

import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.vo.RefundForm;
import com.example.cinema.vo.RefundStrategyFrom;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.TicketForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping("/vip/buy")
    public ResponseVO buyTicketByVIPCard(@RequestParam List<Integer> ticketId, @RequestParam int couponId){
        return ticketService.completeByVIPCard(ticketId,couponId);
    }

    @PostMapping("/lockSeat")
    public ResponseVO lockSeat(@RequestBody TicketForm ticketForm){
        return ticketService.addTicket(ticketForm);
    }
    @PostMapping("/buy")
    public ResponseVO buyTicket(@RequestParam List<Integer> ticketId,@RequestParam int couponId){
        return ticketService.completeTicket(ticketId,couponId);
    }
    @GetMapping("/get/{userId}")
    public ResponseVO getTicketByUserId(@PathVariable int userId){
        return ticketService.getTicketByUser(userId);
    }

    @GetMapping("/get/occupiedSeats")
    public ResponseVO getOccupiedSeats(@RequestParam int scheduleId){
        return ticketService.getBySchedule(scheduleId);
    }

    @PostMapping("/cancel")
    public ResponseVO cancelTicket(@RequestParam List<Integer> ticketId){
        return ticketService.cancelTicket(ticketId);
    }

    @GetMapping("/getRefundStrategy")
    public ResponseVO getRefundStrategy(){
        //TODO: 获取所有退票策略
        return ResponseVO.buildSuccess();
    }

    @PostMapping("/addRefundStrategy")
    public ResponseVO addRefundStrategy(@RequestBody RefundStrategyFrom refundStrategyFrom){
        //TODO: 新增退票策略
        return ResponseVO.buildSuccess();
    }

    @PostMapping("/updateRefundStrategy")
    public ResponseVO updateRefundStrategy(@RequestBody RefundStrategyFrom refundStrategyFrom){
        //TODO: 修改退票策略
        return ResponseVO.buildSuccess();
    }

    @PostMapping("/refund")
    public ResponseVO refundTicket(@RequestBody RefundForm refundForm){
        //TODO: 退票，和上面cancelTicket(取消锁座)好像有点像
        // 不过不一样，这个是单个位置的票退票，还要退钱之类的
        // 如果有会员卡就退到卡里，没有就。。。。。白嫖
        return ResponseVO.buildSuccess();
    }

}
