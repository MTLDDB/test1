package com.entity;

import java.sql.Timestamp;

public class StockDifference {

  private String objectid;
  private String oldStock;
  private String newStock;
  private String difference;
  private String oldBacthno;
  private String newBacthno;
  private String createtime;
  private String updatetime;
  private String oldStoragetime;
  private String newStoragetime;
  private String mpn;
  private String proId;
  private String domain;


  public String getDomain() {

    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public String getObjectid() {
    return objectid;
  }

  public void setObjectid(String objectid) {
    this.objectid = objectid;
  }


  public String getOldStock() {
    return oldStock;
  }

  public void setOldStock(String oldStock) {
    this.oldStock = oldStock;
  }


  public String getNewStock() {
    return newStock;
  }

  public void setNewStock(String newStock) {
    this.newStock = newStock;
  }


  public String getDifference() {
    return difference;
  }

  public void setDifference(String difference) {
    this.difference = difference;
  }


  public String getOldBacthno() {
    return oldBacthno;
  }

  public void setOldBacthno(String oldBacthno) {
    this.oldBacthno = oldBacthno;
  }


  public String getNewBacthno() {
    return newBacthno;
  }

  public void setNewBacthno(String newBacthno) {
    this.newBacthno = newBacthno;
  }


  public String getCreatetime() {
    return createtime;
  }

  public void setCreatetime(String createtime) {
    this.createtime = createtime;
  }

  public String getUpdatetime() {
    return updatetime;
  }

  public void setUpdatetime(String updatetime) {
    this.updatetime = updatetime;
  }

  public String getNewStoragetime() {
    return newStoragetime;
  }

  public String getOldStoragetime() {
    return oldStoragetime;
  }

  public void setOldStoragetime(String oldStoragetime) {
    this.oldStoragetime = oldStoragetime;
  }

  public void setNewStoragetime(String newStoragetime) {
    this.newStoragetime = newStoragetime;

  }

  public String getMpn() {
    return mpn;
  }

  public void setMpn(String mpn) {
    this.mpn = mpn;
  }


  public String getProId() {
    return proId;
  }

  public void setProId(String proId) {
    this.proId = proId;
  }
}
