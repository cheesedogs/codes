package com.example.cinema.vo;

import com.example.cinema.po.VIPPromotion;
import lombok.Data;

@Data
public class VIPPromotionVO {

    /**这是需要修改的策略的id，若不是修改，则为null*/
    private int id;

    /**这是需要满足的金额*/
    private double standard;

    /**这是附赠的金额*/
    private double minus;

    public VIPPromotionVO(){}

    public VIPPromotionVO(VIPPromotion vipPromotion){
        this.id = vipPromotion.getId();
        this.standard = vipPromotion.getStandard();
        this.minus = vipPromotion.getMinus();
    }
}
