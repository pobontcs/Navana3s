package com.example.navana3s;

public class stock {
    private  int quantity;
    private  String  stock_name;


    stock(int quantity,String
          stock_name
    ){
        this.quantity
                =quantity;
        this.stock_name= stock_name;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public String getStock_name(){
        return this.stock_name;
    }


}
