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
import java.util.*;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox.*;
import java.time.*;
public class transaction extends database  implements Initializable{
@FXML
    private Label revenue_text;
@FXML
    private Label pending_order_text;
@FXML
    private Label pending_salary_text;

@FXML
    private Label M_revenue_text;
    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, Integer> order_no;
    @FXML
    private TableColumn<Order, String> date_no;
    @FXML
    private TableColumn<Order, Double> ammount_no;
    private ObservableList<Order> orderData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
    database db = new database();
    public void load_demo_data(){

        orderData.clear();

        orderData.add(new Order("101", "2024-11-01", 5000.0));
        orderData.add(new Order("102", "2024-11-10", 7000.0));
        orderData.add(new Order("103", "2024-11-15", 12000.0));
        orderData.add(new Order("104", "2024-11-18", 1500.0));
        orderData.add(new Order("105", "2024-11-19", 2500.0));

    }

 public  void test_Button(ActionEvent event){
     order_no.setCellValueFactory(new PropertyValueFactory<>("orderNo"));
     date_no.setCellValueFactory(new PropertyValueFactory<>("date"));
     ammount_no.setCellValueFactory(new PropertyValueFactory<>("amount"));

     // Load demo data
     load_demo_data();

     // Set the data to the TableView
     orderTable.setItems(orderData);

    }
}
