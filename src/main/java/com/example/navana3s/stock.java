package com.example.navana3s;

public class stock {
    private  String quantity;
    private String price;
    private String stock_name;

    public stock(String quantity, String price, String stock_name) {
        this.quantity = quantity;
        this.price = price;
        this.stock_name = stock_name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public String getStock_name() {
        return stock_name;
    }
}
