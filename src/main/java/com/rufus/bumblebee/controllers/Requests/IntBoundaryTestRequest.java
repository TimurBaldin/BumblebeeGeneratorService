package com.rufus.bumblebee.controllers.Requests;

public class IntBoundaryTestRequest {

    private Long boundaryIntEnd;
    private Long boundaryIntStart;
    private Integer quantity;

    public Long getBoundaryIntEnd() {
        return boundaryIntEnd;
    }

    public void setBoundaryIntEnd(Long boundaryIntEnd) {
        this.boundaryIntEnd = boundaryIntEnd;
    }

    public Long getBoundaryIntStart() {
        return boundaryIntStart;
    }

    public void setBoundaryIntStart(Long boundaryIntStart) {
        this.boundaryIntStart = boundaryIntStart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "IntBoundaryTestRequest{" +
                "boundaryIntEnd=" + boundaryIntEnd +
                ", boundaryIntStart=" + boundaryIntStart +
                ", quantity=" + quantity +
                '}';
    }

}
