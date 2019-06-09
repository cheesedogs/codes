package com.example.cinema.bl.sales;

import com.example.cinema.vo.RefundForm;
import com.example.cinema.vo.RefundStrategyFrom;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.TicketForm;

import java.util.List;


/**
 * Created by liying on 2019/4/16.
 */
public interface TicketService {
    /**
     * 【完成TODO，金额在支付时加上】:锁座【增加票但状态为未付款】，要加上票的实际应付金额
     *
     * @param ticketForm
     * @return
     */
    ResponseVO addTicket(TicketForm ticketForm);

    /**
     * TODO:完成购票【不使用会员卡】流程包括校验优惠券和根据优惠活动赠送优惠券，现在要修改下金额问题
     *
     * @param id
     * @param couponId
     * @return
     */
    ResponseVO completeTicket(List<Integer> id, int couponId);

    /**
     * 获得该场次的被锁座位和场次信息
     *
     * @param scheduleId
     * @return
     */
    ResponseVO getBySchedule(int scheduleId);

    /**
     * 【完成TODO】:获得用户买过的票
     *
     * @param userId
     * @return
     */
    ResponseVO getTicketByUser(int userId);

    /**
     * TODO:完成购票【使用会员卡】流程包括会员卡扣费、校验优惠券和根据优惠活动赠送优惠券，现在要修改下金额问题，以及对BL层和Data层的混用问题
     *
     * @param id
     * @param couponId
     * @return
     */
    ResponseVO completeByVIPCard(List<Integer> id, int couponId);

    /**
     * 【完成TODO，不需要做这个功能】:取消锁座（只有状态是"锁定中"的可以取消），调整下message
     *
     * @param id
     * @return
     */
    ResponseVO cancelTicket(List<Integer> id);

    /**
     * 获取所有退票策略
     * */
    ResponseVO getAllRefundStrategy();

    /**
     * 增加退票策略
     *
     * @param refundStrategyFrom*/
    ResponseVO addRefundStrategy(RefundStrategyFrom refundStrategyFrom);

    /**
     * 修改退票策略
     *
     * @param refundStrategyFrom*/
    ResponseVO updateRefundStrategy(RefundStrategyFrom refundStrategyFrom);

    /**
     * 退票
     * */
    ResponseVO refundTicket(RefundForm refundForm);

}
