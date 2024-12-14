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

import java.io.*;
import java.util.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox.*;
import java.time.*;
public class Cart implements Initializable {
    @FXML
    private TableView<stock>bill_table;
    @FXML
    private TableColumn<stock,String>prod_name;
    @FXML
    private TableColumn<stock,String>prod_price;
    @FXML
    private TableColumn<stock,String>prod_quantity;
    @FXML
    private Label total_price;
    @FXML
    private ObservableList<stock> stock_data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
                table_init();
    }
    public void table_init(){
        double prc = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("cart.txt"));
            String line;
            stock_data.clear();
            while((line = br.readLine()) != null){
                String[] patch = line.split("\\s*\\|\\s*");
                double price = founded_price(patch[0], patch[1], patch[2]);
                prc += price;
                if (price != 0) {
                    stock stk = new stock(patch[2], Double.toString(price), patch[0] + patch[1]);
                    stock_data.add(stk);
                }
                else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error "+patch[0]+" "+patch[1]+"Not " +
                            "found");
                   alert.showAndWait();
                }

            }
            br.close();
            prod_name.setCellValueFactory(new PropertyValueFactory<>("stock_name"));
            prod_price.setCellValueFactory(new PropertyValueFactory<>("price"));
            prod_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            bill_table.setItems(stock_data);
            total_price.setText(prc+"");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public double founded_price(String itemName,String itemBrand,String itemQuantitiy){

                    try{
                               BufferedReader br=new BufferedReader(new FileReader("inventory.txt"));
                               String line;
                               while((line=br.readLine())!=null){
                                   String[] items=line.split(";");
                                   if(itemName.equals(items[0])&&itemBrand.equals(items[1])){
                                       return Double.parseDouble(items[3])* (Double.parseDouble(itemQuantitiy));
                                   }
                               }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
    }
    public void on_confirm_click(ActionEvent event) {
        utility.changeScene("hello-view.fxml",event,"Navana3s",1000,600);
    }

}
