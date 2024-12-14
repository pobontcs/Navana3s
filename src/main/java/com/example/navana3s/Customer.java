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

public class Customer extends database implements Initializable {
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

    @FXML
    private TextField NumberPlate;
    private ArrayList<String> issues = new ArrayList<>();

    @FXML
    private TextField tech_support_field;

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

        String selectedVtype=Vehicletype.getValue();
        String selectedMtype=manufacturer.getValue();

        Statement.getItems().clear();
        if(Customer_Name.getText().isEmpty() || Customer_Mail.getText().isEmpty() || NumberPlate
                .getText().isEmpty()){
            Alert alert= new Alert(AlertType.ERROR);
            alert.setTitle("Selection Error");
            alert.setHeaderText("Your Info Is Needed");
            alert.showAndWait();
            Statement.getItems()
                    .clear();
            return;


        }
        String s1=Customer_Name.getText();///prompt users details customer
        String s2=Customer_Mail.getText();
        String s3=NumberPlate.getText();
        Statement.getItems().add(s1);
        Statement.getItems().add(s2);
        Statement.getItems().add(s3);
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
        if(selectedMtype!= null ){
            Statement.getItems()
                    .add(selectedMtype);


        }

        if(selectedVtype!= null ){
            Statement.getItems()
                    .add(selectedVtype);

            for(String is:issues){
                Statement.getItems().add(is);
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
                /// Abdullah AL Hossain github: pobontcs
    }
    public void on_confirm_click(ActionEvent event) throws NullPointerException{//Saving the data
               if (Statement.getItems().isEmpty()){
                   Alert alert=new Alert(AlertType
                           .ERROR);alert.setTitle("No Data");
                   alert.setHeaderText(null);
                   alert.setContentText("No data to confirm. Please add details first.");
                   alert.showAndWait();
                   return;

               }
                    String numberplate=NumberPlate.getText();
                    List<String>issue_list=issues;
                    LocalDate date=LocalDate.now();
                    String st=date.toString();
                    String vehicleType=Vehicletype.getValue();
                    String Manufacturer=manufacturer.getValue();


               try{
                   BufferedWriter bw=new BufferedWriter(new FileWriter("order.txt",true));

                   bw.write(numberplate);
                   bw.write(';');
                   for(String issue:issue_list){
                       bw.write(issue);
                       bw.write(';');
                   }
                   bw.write(vehicleType);
                   bw.write(';');
                   bw.write(Manufacturer);
                   bw.write(';');
                   bw.write(st);
                   bw.newLine();
                   bw.close();
                   Alert alert = new Alert(AlertType.CONFIRMATION);
                   alert.setTitle("Saved");
                   alert.setHeaderText(null);
                   alert.setContentText("Data has been successfully saved to order.txt!");
                   alert.showAndWait();


               }
               catch(Exception e){
                   System.out.println(e.getMessage());
               }
    }
    public void on_tech_support_click(ActionEvent event) throws NullPointerException{
        if(tech_support_field.getText().isEmpty() || NumberPlate.getText().isEmpty()){
            Alert alert=new Alert(AlertType.ERROR);alert.setTitle("No Data or No NumberPlate");
            alert.showAndWait();
            return;
        }
        String numberplate=NumberPlate.getText();
                try{
                    BufferedWriter bw=new BufferedWriter(new FileWriter("techsupport.txt",true));
                    bw.write(numberplate);
                    bw.write(';');
                    bw.write(tech_support_field.getText());
                    bw.newLine();
                    bw.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                tech_support_field.clear();
                NumberPlate.clear();
    }


}
