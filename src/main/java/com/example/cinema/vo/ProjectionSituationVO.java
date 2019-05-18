package com.example.cinema.vo;

import com.example.cinema.po.ProjectionSituation;

/**
 * @author S.W.R
 * @date 2019/5/15 5:41 PM
 */
public class ProjectionSituationVO {
    private Integer hallNum;
    private Integer rowNum;
    private Integer columnNum;
    private Integer releaseNum;
    private Integer audienceNum;
    private Integer placingRate;

    public ProjectionSituationVO(ProjectionSituation projectionSituation){
        this.hallNum = projectionSituation.getHallNum();
        this.rowNum = projectionSituation.getRowNum();
        this.columnNum = projectionSituation.getColumnNum();
        this.releaseNum = projectionSituation.getReleaseNum();
        this.audienceNum = projectionSituation.getAudienceNum();
        this.placingRate = projectionSituation.getPlacingRate();
    }

    public Integer getHallNum() {
        return hallNum;
    }

    public void setHallNum(Integer hallNum) {
        this.hallNum = hallNum;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Integer getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(Integer columnNum) {
        this.columnNum = columnNum;
    }

    public Integer getReleaseNum() {
        return releaseNum;
    }

    public void setReleaseNum(Integer releaseNum) {
        this.releaseNum = releaseNum;
    }

    public Integer getAudienceNum() {
        return audienceNum;
    }

    public void setAudienceNum(Integer audienceNum) {
        this.audienceNum = audienceNum;
    }

    public Integer getPlacingRate() {
        return placingRate;
    }

    public void setPlacingRate(Integer placingRate) {
        this.placingRate = placingRate;
    }
}
