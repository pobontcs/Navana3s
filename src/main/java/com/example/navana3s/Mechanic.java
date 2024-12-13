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
import java.time.*;

import java.io.*;
import java.util.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox.*;
import java.time.*;
public class Mechanic implements Initializable {
    @FXML
    private ListView<String>    Order_list;@FXML
    private ListView<String>    order_details;
    @FXML
    private Label Mechanic_ID;
    @FXML
    private Label  Mechanic_Name;
    private ObservableList<String> Orderata = FXCollections.observableArrayList();
    private ObservableList<String> Orderdata = FXCollections.observableArrayList();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeMechanic();
        initializeorders();
        Order_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            toggle_order();
        });
    }
    public void initializeMechanic() {
        try{
            BufferedReader br= new BufferedReader(new FileReader("chosen.txt"));
            String line=br.readLine();
            String[] split = line.split(";");
            Mechanic_ID.setText(split[1]);
            Mechanic_Name.setText(split[0]);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    public void initializeorders(){
            List<String>tasks=new ArrayList<>();

            try{
                BufferedReader br= new BufferedReader(new FileReader("Assigned.txt"));
                String line;
                while ((line = br.readLine()) != null){
                    String[] split = line.split(";");
                    if(split[0].equals(Mechanic_ID.getText())){
                        tasks.add(split[1]);
                    }
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            Orderata.clear();
            Orderata.addAll(tasks);
            Order_list.setItems(Orderata);

    }
    public void toggle_order(){
        String selectedOrder = Order_list.getSelectionModel().getSelectedItem();
        try{
            BufferedReader br=new BufferedReader(new FileReader("order.txt"));
                String line;
                List<String>tasks=new ArrayList<>();
                while ((line = br.readLine()) != null){
                    String[] split = line.split(";");
                    if(split[0].equals(selectedOrder)){
                        tasks.add(split[split.length-1]);
                        for(int i=1;i<split.length-1;i++){
                            tasks.add(split[i]);
                        }
                    }
                }
                Orderdata.clear();
                Orderdata.addAll(tasks);
                order_details.setItems(Orderdata);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void on_logout_Button_Click(ActionEvent event) {
            utility.changeScene("hello-view.fxml",event,"Navana 3s",1200,800);
    }

}
