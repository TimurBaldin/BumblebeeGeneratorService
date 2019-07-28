package com.rufus.bumblebee.Main.Controllers.Requests;

public class BoundaryTestRequest {

    private Integer len;
    private Integer increaseQuantity;
    private Boolean low;
    private Boolean cap;
    private Boolean nullValue;

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }

    public Integer getIncreaseQuantity() {
        return increaseQuantity;
    }

    public void setIncreaseQuantity(Integer increaseQuantity) {
        this.increaseQuantity = increaseQuantity;
    }

    public Boolean getLow() {
        return low;
    }

    public void setLow(Boolean low) {
        this.low = low;
    }

    public Boolean getCap() {
        return cap;
    }

    public void setCap(Boolean cap) {
        this.cap = cap;
    }

    public Boolean getNullValue() {
        return nullValue;
    }

    public void setNullValue(Boolean nullValue) {
        this.nullValue = nullValue;
    }

    @Override
    public String toString() {
        return "BoundaryTestRequest{" +
                "len=" + len +
                ", increaseQuantity=" + increaseQuantity +
                ", low=" + low +
                ", cap=" + cap +
                ", nullValue=" + nullValue +
                '}';
    }

}
