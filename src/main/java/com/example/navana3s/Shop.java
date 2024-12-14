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

public class Shop implements Initializable {
    @FXML
    private ComboBox<String> engine_items;
    @FXML
    private ComboBox<String> tire_items;
    @FXML
    private ComboBox<String> pump_items;
    @FXML
    private ComboBox<String> stock_items;
    @FXML
    private ComboBox<String> brake_items;
    @FXML
    private ComboBox<String> e_brand;
    @FXML
    private ComboBox<String> t_brand;
    @FXML
    private ComboBox<String> p_brand;
    @FXML
    private ComboBox<String> s_brand;
    @FXML
    private ComboBox<String> b_brand;
    @FXML
    private ListView<String> order_list;

    @FXML
    private TextField q_engine;@FXML
    private TextField q_tire;@FXML
    private TextField q_stock;@FXML
    private TextField q_pump;@FXML
    private TextField q_brake;
    /// Typos
    private final String[] engineItems = {
            "Engine Oil Filter", "Spark Plugs", "Fuel Injector",
            "Timing Belt/Chain", "Cylinder Head", "Gearbox",
            "Clutch Plate", "Flywheel", "Transmission Fluid"
    };

    private final String[] tiresItems = {
            "Alloy Rims", "Steel Rims", "All-Season Tires", "Spare"
    };

    private final String[] pumpsItems = {
            "Power Steering Pump"
    };

    private final String[] stocksItems = {
            "Headlights", "Taillights", "Fog Lights",
            "Indicator Bulbs", "Bumpers", "Fenders",
            "Hood", "Side Mirrors"
    };

    private final String[] brakesItems = {
            "Brake Pads", "Brake Rotors", "Brake Calipers",
            "Master Cylinder", "Shock Absorbers",
            "Struts", "Tie Rod Ends"
    };
        private final ObservableList<String>ENGINE_ITEMS = FXCollections.observableArrayList();
        private final ObservableList<String>TIRE_ITEMS = FXCollections.observableArrayList();
        private final ObservableList<String>PUMP_ITEMS = FXCollections.observableArrayList();
        private final ObservableList<String>BRAKE_ITEMS = FXCollections.observableArrayList();
        private final ObservableList<String>STOCK_ITEMS = FXCollections.observableArrayList();

    private double total_price;
    /// BRands
    private final String[] tireBrands = {"Bridgestone", "Pirelli", "Continental", "Yokohama", "Dunlop", "Nexen Tires", "Apollo Tyres"};

    private final String[] brands = {"Mansory", "Brabus", "Novitech", "Hennessy", "Zenvo"};
    private final ObservableList<String> items = FXCollections.observableArrayList();
    private final ObservableList<String> T_items = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        init_types();
    }
    public void alertcall(){
        Alert alert= new Alert(AlertType.ERROR);
        alert.setTitle("Selection Error");
        alert.showAndWait();
    }
    public void init_types()throws NullPointerException {
        items.addAll(brands);
        T_items.addAll(tireBrands);

        try {

            if (e_brand != null) e_brand.setItems(items);
            if (t_brand != null) t_brand.setItems(T_items);
            if (p_brand != null) p_brand.setItems(items);
            if (s_brand != null) s_brand.setItems(items);
            if (b_brand != null) b_brand.setItems(items);
        } catch (Exception e) {
            System.out.println("Error during ComboBox initialization: " + e.getMessage());
        }

                    ENGINE_ITEMS.addAll(engineItems);
                    TIRE_ITEMS.addAll(tiresItems);
                    PUMP_ITEMS.addAll(pumpsItems);
                    STOCK_ITEMS.addAll(stocksItems);
                    BRAKE_ITEMS.addAll(brakesItems);
                    try{
                        engine_items.setItems(ENGINE_ITEMS);
                        tire_items.setItems(TIRE_ITEMS);
                        pump_items.setItems(PUMP_ITEMS);
                        stock_items.setItems(STOCK_ITEMS);
                        brake_items.setItems(BRAKE_ITEMS);
                    }
                    catch (Exception e){
                        e.printStackTrace();
        }
    }
    private void clearComboBoxes() {
        e_brand.getItems().clear();
        t_brand.getItems().clear();
        p_brand.getItems().clear();
        s_brand.getItems().clear();
        b_brand.getItems().clear();

        engine_items.getItems().clear();
        tire_items.getItems().clear();
        pump_items.getItems().clear();
        stock_items.getItems().clear();
        brake_items.getItems().clear();
        init_types();

    }
    public void on_add_button_clicked(ActionEvent event) throws Exception {
        String engine = engine_items.getValue();
        String tire = tire_items.getValue();
        String pump = pump_items.getValue();
        String stock = stock_items.getValue();
        String brakes = brake_items.getValue();
        String engine_brand = e_brand.getValue();
        String tire_brand = t_brand.getValue();
        String pump_brand = p_brand.getValue();
        String brakes_brand = b_brand.getValue();
        String stock_brand = s_brand.getValue();
        String e_quantity = q_engine.getText().trim();
        String s_quantity = q_stock.getText().trim();
        String p_quantity = q_pump.getText().trim();
        String b_quantity = q_brake.getText().trim();
        String t_quantity = q_tire.getText().trim();

        clearComboBoxes();

        List<String> ls = new ArrayList<>();

        if (engine != null && !e_quantity.equals("")) {
            if (engine_brand == null) {
                alertcall();
                return;
            } else {
                ls.add(engine + " | " + engine_brand + " | " + e_quantity);
            }
        }

        if (tire != null && !t_quantity.equals("")) {
            if (tire_brand == null) {
                alertcall();
                return;
            } else {
                ls.add(tire + " | " + tire_brand + " | " + t_quantity);
            }
        }

        if (pump != null && !p_quantity.equals("")) {
            if (pump_brand == null) {
                alertcall();
                return;
            } else {
                ls.add(pump + " | " + pump_brand + " | " + p_quantity);
            }
        }

        if (stock != null && !s_quantity.equals("")) {
            if (stock_brand == null) {
                alertcall();
                return;
            } else {
                ls.add(stock + " | " + stock_brand + " | " + s_quantity);
            }
        }


        if (brakes != null && !b_quantity.equals("")) {
            if (brakes_brand == null) {
                alertcall();
                return;
            } else {
                ls.add(brakes + " | " + brakes_brand + " | " + b_quantity);
            }
        }


        for (String l : ls) {
            order_list.getItems().add(l);
        }
    }
    public void on_cart_button_clicked(ActionEvent event) throws Exception {

            ObservableList<String> ls = order_list.getItems();
            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter("cart.txt"));
                String line ;
                for(String l : ls){
                    bw.write(l + "\n");
                }
                bw.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            utility.changeScene("cart.fxml",event,"Cart",800,700);
    }
    public void on_delete_button_clicked(ActionEvent event) throws Exception {

    }

}
