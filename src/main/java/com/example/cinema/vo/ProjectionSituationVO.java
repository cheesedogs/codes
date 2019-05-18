package com.example.cinema.vo;

import com.example.cinema.po.ProjectionSituation;

/**
 * @author S.W.R
 * @date 2019/5/15 5:41 PM
 */
public class ProjectionSituationVO {
    private Integer placingRate;

    public ProjectionSituationVO(ProjectionSituation projectionSituation){
        this.placingRate = projectionSituation.getPlacingRate();
    }

    public Integer getPlacingRate() {
        return placingRate;
    }

    public void setPlacingRate(Integer placingRate) {
        this.placingRate = placingRate;
    }
}
