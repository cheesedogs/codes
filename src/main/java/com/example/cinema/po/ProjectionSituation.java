package com.example.cinema.po;

/**
 * @author S.W.R
 * @date 2019/5/15 5:02 PM
 */
public class ProjectionSituation {
    private Integer hallNum;
    private Integer rowNum;
    private Integer columnNum;
    private Integer releaseNum;
    private Integer audienceNum;
    private Integer placingRate;

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
        return audienceNum / (releaseNum / (hallNum / (rowNum * columnNum)));
    }

    public void setPlacingRate(Integer placingRate) {
        this.placingRate = placingRate;
    }
}
