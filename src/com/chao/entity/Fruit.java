package com.chao.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Mrchao
 * @version 1.0.0
 * @date 2022-10-27
 */
public class Fruit implements Serializable {
    private static final long serialVersionUID = -3010141647501501170L;
    private Integer fId;
    private String fName;
    private Integer fPrice;
    private Integer fCount;
    private String remark;

    public Fruit() {
    }

    public Fruit(Integer fId, String fName, int fPrice, int fCount, String remark) {
        this.fId = fId;
        this.fName = fName;
        this.fPrice = fPrice;
        this.fCount = fCount;
        this.remark = remark;
    }

    public int getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public int getfPrice() {
        return fPrice;
    }

    public void setfPrice(Integer fPrice) {
        this.fPrice = fPrice;
    }

    public int getfCount() {
        return fCount;
    }

    public void setfCount(Integer fCount) {
        this.fCount = fCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "fid=" + fId +
                ", fname='" + fName + '\'' +
                ", fprice=" + fPrice +
                ", fcount=" + fCount +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fruit fruit = (Fruit) o;
        return fId == fruit.fId &&
                fPrice == fruit.fPrice &&
                fCount == fruit.fCount &&
                fName.equals(fruit.fName) &&
                Objects.equals(remark, fruit.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fId, fName, fPrice, fCount, remark);
    }
}
