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
@Table(name = "t_sub_ptwop", schema = "test", catalog = "")
public class TSubPtwopEntity {
    private String objectid;
    private String proId;
    private String subproId;
    private String typeId;
    private int singleAble;

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
    @Column(name = "subpro_id", nullable = false, length = 255)
    public String getSubproId() {
        return subproId;
    }

    public void setSubproId(String subproId) {
        this.subproId = subproId;
    }

    @Basic
    @Column(name = "type_id", nullable = false, length = 0)
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "single_able", nullable = false)
    public int getSingleAble() {
        return singleAble;
    }

    public void setSingleAble(int singleAble) {
        this.singleAble = singleAble;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TSubPtwopEntity that = (TSubPtwopEntity) o;
        return singleAble == that.singleAble &&
                Objects.equals(objectid, that.objectid) &&
                Objects.equals(proId, that.proId) &&
                Objects.equals(subproId, that.subproId) &&
                Objects.equals(typeId, that.typeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectid, proId, subproId, typeId, singleAble);
    }
}
