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
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import java.io.IOException;
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
    private TextField m_input;
    @FXML
    private TextField q_input;
    @FXML
    private TextField p_input;
    @FXML
    private ProgressBar capacity;

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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (type_combo != null) {
            System.out.println("Populating ComboBox");
            type_combo.setItems(FXCollections.observableArrayList(carItems));
        } else {
            System.err.println("type_combo is null. Check FXML fx:id binding.");
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





    }
}
