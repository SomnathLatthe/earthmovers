package com.jaybhavani.earthmovers.entity;

public class TotalPriceDTO {
    private String customerName;
    private Double totalPrice;

    private String id;

    public TotalPriceDTO() {
    }

    public TotalPriceDTO(String customerName, Double totalPrice,String id) {
        this.customerName = customerName;
        this.totalPrice = totalPrice;
        this.id=id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
