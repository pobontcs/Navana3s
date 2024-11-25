package com.example.navana3s;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox.*;
import java.time.*;
public class Wokshop implements Initializable {
@FXML
    private TableView<Order> pending_table;
@FXML
private TableColumn<Order,String>order_P_column;
@FXML
private TableColumn<Order,String>date_P_column;
private ObservableList<Order> orderata = FXCollections.observableArrayList();
    public void initialize(URL url,ResourceBundle resourceBundle) {
// Bind table columns to Order properties

    }

    public double ammount(List<String>services){
        double price=0.00;

        for(String service: services){
              if(service.equals("Engine")){
                price+=20000.00;
            } else if(service.equals("Transmission")){
                price+=7000.00;
            } else if (service.equals("Brake")) {
                price+=5500.00;
            } else if (service.equals("Tire")) {
                price+=12000.00;
            } else if (service.equals("Exhaust")) {
                price+=4000.00;
            } else if (service.equals("Cooling System")) {
                price+=3000;
            }
              else if (service.equals("Fuel System")) {
                price+=2000.00;
            }

        }

        return price;
    }

    public void on_refresh_button(ActionEvent event) throws IOException{
                try{
                    BufferedReader br= new BufferedReader(new FileReader("order.txt"));
                    List<Order> orders = new ArrayList<>();
                    String line;

                    while((line= br.readLine())!=null){
                                    String[] parts = line.split(";");
                                    String orderNo=parts[0];
                                    List<String>services = new ArrayList<>();

                                    for(int i =1;i< parts.length-1;i++){
                                        services.add(parts[i]);
                                    }
                                    String date = parts[parts.length-1];
                                    double amount=ammount(services);System.out.println(amount);
                                    Order order= new Order(orderNo,date,amount);
                                    orders.add(order);
                    }

                    orderata.clear();
                    orderata.addAll(orders);
                    order_P_column.setCellValueFactory(new PropertyValueFactory<>("orderNo"));
                    date_P_column.setCellValueFactory(new PropertyValueFactory<>("date"));


                    pending_table.setItems(orderata);



                }
                catch(Exception e){
                                        e.printStackTrace();
                }

    }
}
