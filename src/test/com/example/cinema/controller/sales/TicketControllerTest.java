package com.example.cinema.controller.sales;

import com.example.cinema.CinemaApplicationTest;
import com.example.cinema.po.ChargeRecord;
import com.example.cinema.po.RefundStrategy;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

public class TicketControllerTest extends CinemaApplicationTest {

    @Autowired
    private TicketController ticketController;

//    @Test
//    @Transactional
//    public void getRefundStrategy() {
//        try {
//            List<RefundStrategy> records = (List<RefundStrategy>) vipCardController.getChargeRecord(3).getContent();
//            StringBuilder sb = new StringBuilder();
//            for (ChargeRecord c : records) {
//                sb.append(c.getAmount()).append(":").append(c.getBalance()).append(System.lineSeparator());
//            }
//            Assert.assertEquals("300.0:330.0\r\n100.0:430.0\r\n", sb.toString());
//        }catch (Exception e){
//            fail();
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    @Transactional
//    public void addRefundStrategy() {
//        try {
//            List<ChargeRecord> records = (List<ChargeRecord>) vipCardController.getChargeRecord(3).getContent();
//            StringBuilder sb = new StringBuilder();
//            for (ChargeRecord c : records) {
//                sb.append(c.getAmount()).append(":").append(c.getBalance()).append(System.lineSeparator());
//            }
//            Assert.assertEquals("300.0:330.0\r\n100.0:430.0\r\n", sb.toString());
//        }catch (Exception e){
//            fail();
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    @Transactional
//    public void updateRefundStrategy() {
//        try {
//            List<ChargeRecord> records = (List<ChargeRecord>) vipCardController.getChargeRecord(3).getContent();
//            StringBuilder sb = new StringBuilder();
//            for (ChargeRecord c : records) {
//                sb.append(c.getAmount()).append(":").append(c.getBalance()).append(System.lineSeparator());
//            }
//            Assert.assertEquals("300.0:330.0\r\n100.0:430.0\r\n", sb.toString());
//        }catch (Exception e){
//            fail();
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    @Transactional
//    public void refundTicket() {
//        try {
//            List<ChargeRecord> records = (List<ChargeRecord>) vipCardController.getChargeRecord(3).getContent();
//            StringBuilder sb = new StringBuilder();
//            for (ChargeRecord c : records) {
//                sb.append(c.getAmount()).append(":").append(c.getBalance()).append(System.lineSeparator());
//            }
//            Assert.assertEquals("300.0:330.0\r\n100.0:430.0\r\n", sb.toString());
//        }catch (Exception e){
//            fail();
//            e.printStackTrace();
//        }
//    }
}