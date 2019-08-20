package com.rufus.bumblebee.controllers.Requests;

public class SpecialLinesTestRequest extends BaseRequest{

    private Integer specialLen;
    private Integer increaseQuantity;
    private Boolean escSpecial;
    private Boolean special;

    public Integer getSpecialLen() {
        return specialLen;
    }

    public void setSpecialLen(Integer specialLen) {
        this.specialLen = specialLen;
    }

    public Integer getIncreaseQuantity() {
        return increaseQuantity;
    }

    public void setIncreaseQuantity(Integer increaseQuantity) {
        this.increaseQuantity = increaseQuantity;
    }

    public Boolean getEscSpecial() {
        return escSpecial;
    }

    public void setEscSpecial(Boolean escSpecial) {
        this.escSpecial = escSpecial;
    }

    public Boolean getSpecial() {
        return special;
    }

    public void setSpecial(Boolean special) {
        this.special = special;
    }

    @Override
    public String toString() {
        return "SpecialLinesTestRequest{" +
                "specialLen=" + specialLen +
                ", increaseQuantity=" + increaseQuantity +
                ", escSpecial=" + escSpecial +
                ", special=" + special +
                '}';
    }

}
