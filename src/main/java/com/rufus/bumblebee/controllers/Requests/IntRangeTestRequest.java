package com.rufus.bumblebee.controllers.Requests;

public class IntRangeTestRequest extends BaseRequest{

    private Long maxIntVal;
    private Long minIntVal;

    public Long getMaxIntVal() {
        return maxIntVal;
    }

    public void setMaxIntVal(Long maxIntVal) {
        this.maxIntVal = maxIntVal;
    }

    public Long getMinIntVal() {
        return minIntVal;
    }

    public void setMinIntVal(Long minIntVal) {
        this.minIntVal = minIntVal;
    }

    @Override
    public String toString() {
        return "IntRangeTestRequest{" +
                "maxIntVal=" + maxIntVal +
                ", minIntVal=" + minIntVal +
                '}';
    }

}
