package com.example.navana3s;
import java.util.*;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox.*;
import java.time.*;
public class Order {
    protected String orderNo;//this is license plate no
    protected String date;// the date customer pressed confirm
    protected String status;
    private List<String> services;
    protected double ammountNo;


    public Order(String orderNo, String date, double amount) {
        this.orderNo = orderNo;
        this.date = date;
        this.ammountNo = amount;
    }
    public Order(String orderNo, String date){
        this.orderNo = orderNo;
        this.date = date;
    }

    public String getOrderNo() {
        return orderNo;
    }
    public List<String> getServices() {
        return services;
    }
    public String getDate() {
        return date;
    }

    public double getAmount() {// getAmount propertyvaluefactory("amount")*/
        return ammountNo
        ;
    }

    // Setters (if needed)
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.ammountNo
     = amount;
    }



}