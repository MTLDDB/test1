package com.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : huang
 * @date :  2021/1/8
 * @time : 11:53
 * To change this template use File | Settings | File and Code Templates.
 */
@Entity
@Table(name = "t_pro_series", schema = "test", catalog = "")
public class TProSeriesEntity {
    private String objectid;
    private String proId;
    private String seriesId;
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
    @Column(name = "pro_id", nullable = false, length = 32)
    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    @Basic
    @Column(name = "series_id", nullable = false, length = 32)
    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
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
        TProSeriesEntity that = (TProSeriesEntity) o;
        return Objects.equals(objectid, that.objectid) &&
                Objects.equals(proId, that.proId) &&
                Objects.equals(seriesId, that.seriesId) &&
                Objects.equals(obliget1, that.obliget1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectid, proId, seriesId, obliget1);
    }
}
