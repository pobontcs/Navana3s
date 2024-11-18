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
    protected int orderNo;
    protected String date;
    protected double ammountNo;


    public Order(int orderNo, String date, double amount) {
        this.orderNo = orderNo;
        this.date = date;
        this.ammountNo = amount;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {// getAmount propertyvaluefactory("amount")*/
        return ammountNo
        ;
    }

    // Setters (if needed)
    public void setOrderNo(int orderNo) {
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