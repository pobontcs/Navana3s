package com.example.navana3s;

public class stock {
    private  String quantity;
    private  String  stock_name;
    private  String  price;


    stock(String quantity,String
          stock_name,String price
    ){
        this.quantity
                =quantity;
        this.stock_name= stock_name;
        this.price
                =price;
    }
    public String getQuantity(){
        return this.quantity;
    }
    public String getStock_name(){
        return this.stock_name;
    }
    public String getPrice(){
        return this.price;
    }


}
