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
import java.util.*;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox.*;

public class Customer implements Initializable {
    @FXML
    private ComboBox<String> Vehicletype;
    @FXML
    private ComboBox<String> IssueType;
    @FXML
    private ComboBox<String> manufacturer;
    @FXML
    private ListView<String>Statement;
    @FXML
    private TextField Customer_Name;
    @FXML
    private TextField Customer_Mail;
    private ArrayList<String> issues = new ArrayList<>();

    private String[] types={"Engine","Transmission",
            "Electrical","Brake","Tire","Exhaust","Cooling System","Fuel System"};
    private String[] Brands = {
            "Toyota",
            "Honda",
            "Nissan",
            "Ford",
            "Chevrolet",
            "BMW",
            "Mercedes-Benz",
            "Audi",
            "Volkswagen",
            "Hyundai",
            "Kia",
            "Mazda",
            "Lexus",
            "Porsche",
            "Jaguar",

            "Tesla",
            "Subaru"
    };



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException {
        IssueType.setItems(FXCollections.observableArrayList(types));
        if(Vehicletype!=null) {
            Vehicletype.setItems(FXCollections.observableArrayList("SUV",
                    "Sedan",
                    "Pickup Truck"));
        }if(manufacturer!=null) {
            manufacturer.setItems(FXCollections.observableArrayList(Brands));
        }


    }
    public void on_add_Button_Click(ActionEvent event){      //Add Button

        // Get the selected issue type
        String selectedIype = IssueType.getValue();


                         // Check if an issue type is selected and not already in the list
        issues.add(selectedIype);
        if (selectedIype == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Selection Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select both Vehicle Type and Issue Type.");
            alert.showAndWait();
        }
    }
    public void on_confirm_click(ActionEvent event){
                    String selectedVtype=Vehicletype.getValue();
                    String selectedMtype=manufacturer.getValue();
                     Statement.getItems().clear();
                    if(Customer_Name.getText().isEmpty() && Customer_Mail.getText().isEmpty()){
                        Alert alert= new Alert(AlertType.ERROR);
                        alert.setTitle("Selection Error");
                        alert.setHeaderText("Your Info Is Needed");

                        alert.showAndWait();
                        return;

                    }
                    String s1=Customer_Name.getText();
                    String s2=Customer_Mail.getText();
                    Statement.getItems().add(s1);
                    Statement.getItems().add(s2);


        if(selectedMtype!= null ){
            Statement.getItems()
                    .add("Manufacturer : "+selectedMtype);


        }

        if(selectedVtype!= null ){
            Statement.getItems()
                    .add("Vehicle Type: "+selectedVtype);

                            for(String is:issues){
                                Statement.getItems().add("Issue: "+is);
                            }

        }
        else {
            // Show an alert if any of the selections is empty
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Selection Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select both Vehicle Type and Issue Type.");
            alert.showAndWait();
        }
    }


}
