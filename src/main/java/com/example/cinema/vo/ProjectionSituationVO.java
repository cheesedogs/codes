package com.example.cinema.vo;

import com.example.cinema.po.ProjectionSituation;

/**
 * @author S.W.R
 * @date 2019/5/15 5:41 PM
 */
public class ProjectionSituationVO {
    private Integer hallNum;
    private Integer seatNum;
    private Integer releaseNum;
    private Integer audienceNum;
    private Integer placingRate;

    public ProjectionSituationVO(ProjectionSituation projectionSituation){
        this.hallNum = projectionSituation.getHallNum();
        this.seatNum = projectionSituation.getSeatNum();
        this.releaseNum = projectionSituation.getReleaseNum();
        this.audienceNum = projectionSituation.getAudienceNum();
    }

    public Integer getHallNum() {
        return hallNum;
    }

    public void setHallNum(Integer hallNum) {
        this.hallNum = hallNum;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
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

    public void setPlacingRate(Integer hallNum, Integer seatNum, Integer releaseNum, Integer audienceNum) {
        this.placingRate = audienceNum / (releaseNum / (hallNum / seatNum));
    }
}
