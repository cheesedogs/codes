package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.SendCouponForm;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    @GetMapping("{userId}/get")
    public ResponseVO getCoupons(@PathVariable int userId){
        return couponService.getCouponsByUser(userId);
    }

    @GetMapping("/getVIPList")
    public ResponseVO sendCoupon(@RequestParam("target_amount")double target_amount){
        //TODO: 获取历史消费超过指定金额的会员列表，等待前端
        return couponService.getVIPListByAmount(target_amount);
    }

    @GetMapping("/getAllCoupon")
    public ResponseVO getAllCoupon(){
        //TODO: 获取所有种类的优惠券，等待前端
        return couponService.getCoupons();
    }

    @PostMapping("/send")
    public ResponseVO sendCoupon(@RequestBody List<SendCouponForm> sendCouponFormList){
        //TODO: 根据传入的(用户id：优惠券id)赠送优惠券，等待前端
        return couponService.sendCoupon(sendCouponFormList);
    }

}
