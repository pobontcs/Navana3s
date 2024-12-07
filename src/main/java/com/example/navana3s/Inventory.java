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


public class Inventory implements Initializable {
    @FXML
    private ComboBox<String> type_combo;
    final int capacit=100;
    @FXML
    TableView<stock> stock_table;
    @FXML
    private TextField m_input;
    @FXML
    private TableColumn<stock,String> t1_quantity;
    @FXML
    private TableColumn<stock,String> t1_price;
    @FXML
    private TableColumn<stock,String> t1_stock_name;
    @FXML
    private TextField q_input;
    @FXML
    private TextField p_input;
    @FXML
    private ProgressBar capacity;
    @FXML
    private ObservableList<stock> stock_data = FXCollections.observableArrayList();

    public final String[] carItems = {
            "Engine Oil Filter",
            "Spark Plugs",
            "Fuel Injector",
            "Timing Belt/Chain",
            "Cylinder Head",
            "Gearbox",
            "Clutch Plate",
            "Flywheel",
            "Transmission Fluid",
            "Brake Pads",
            "Brake Rotors",
            "Brake Calipers",
            "Master Cylinder",
            "Shock Absorbers",
            "Struts",
            "Tie Rod Ends",
            "Power Steering Pump",
            "Headlights",
            "Taillights",
            "Fog Lights",
            "Indicator Bulbs",
            "Bumpers",
            "Fenders",
            "Hood",
            "Side Mirrors",
            "Alloy Rims",
            "Steel Rims",
            "All-Season Tires",
            "Spare"
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException{
        if (type_combo != null) {
            System.out.println("Populating ComboBox");
            type_combo.setItems(FXCollections.observableArrayList(carItems));
        } else {
            System.err.println("type_combo is null. Check FXML fx:id binding.");
        }

    }
    public void t1_refresh_click() {
        t1_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        t1_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        t1_stock_name.setCellValueFactory(new PropertyValueFactory<>("stock_name"));

        stock_data.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("inventory.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] stocks = line.split(";");
                if (stocks.length >= 3) {
                    stock stk = new stock(stocks[2], stocks[1], stocks[0]);
                    stock_data.add(stk);
                }
            }
            stock_table.setItems(stock_data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void on_input_click(ActionEvent event){
        String Brand=m_input.getText();
        String quantity_s=q_input.getText();
        String price=p_input.getText();

       /* double display=Integer.parseInt(quantity_s);
        double display_2=display/100;
        double curr_prog=capacity.getProgress();
        capacity.setProgress(curr_prog+display_2);*/
        if(Brand==null||quantity_s==null||price==null){
            Alert alert=new Alert(AlertType.WARNING);
            alert.setTitle("invalid input");
            alert.showAndWait();
            return;
        }

        try{
            BufferedWriter bw= new BufferedWriter(new FileWriter("inventory.txt",true));
            String item_type=type_combo.getValue();
            bw.write(item_type);
            bw.write(";");
            bw.write(Brand);
            bw.write(";");
            bw.write(quantity_s);
            bw.write(";");
            bw.write(price);
            bw.newLine();
            bw.close();
            System.out.println("Success\n");


        }
        catch(Exception e)
        {
            Alert alert=new Alert(AlertType.WARNING);
            alert.setTitle("Technical Error");
            alert.setContentText("Inform TechSuppport immedietly !!!");
            alert.showAndWait();
        }

            t1_refresh_click();



    }
}
