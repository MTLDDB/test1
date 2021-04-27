package com.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : huang
 * @date :  2021/1/8
 * @time : 11:34
 * To change this template use File | Settings | File and Code Templates.
 */
@Entity
@Table(name = "t_series", schema = "test", catalog = "")
public class TSeriesEntity {
    private String objectid;
    private String seriesName;
    private String seriesImg;
    private String obliget1;

    @Id
    @Column(name = "objectid", nullable = false, length = 32)
    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    @Basic
    @Column(name = "series_name", nullable = false, length = 255)
    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    @Basic
    @Column(name = "series_img", nullable = true, length = 255)
    public String getSeriesImg() {
        return seriesImg;
    }

    public void setSeriesImg(String seriesImg) {
        this.seriesImg = seriesImg;
    }

    @Basic
    @Column(name = "obliget1", nullable = true, length = 255)
    public String getObliget1() {
        return obliget1;
    }

    public void setObliget1(String obliget1) {
        this.obliget1 = obliget1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TSeriesEntity that = (TSeriesEntity) o;
        return Objects.equals(objectid, that.objectid) &&
                Objects.equals(seriesName, that.seriesName) &&
                Objects.equals(seriesImg, that.seriesImg) &&
                Objects.equals(obliget1, that.obliget1);
    }

//    @Override
//    public String toString(){
//
//    }


    @Override
    public String toString() {
        return "TSeriesEntity{" +
                "objectid='" + objectid + '\'' +
                ", seriesName='" + seriesName + '\'' +
                ", seriesImg='" + seriesImg + '\'' +
                ", obliget1='" + obliget1 + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectid, seriesName, seriesImg, obliget1);
    }
}
