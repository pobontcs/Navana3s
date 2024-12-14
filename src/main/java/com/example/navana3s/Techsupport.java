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

public class Techsupport implements Initializable {
    @FXML
    private ListView<String> issueList;
    @FXML
    private TextArea description;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadOrderNumbers();
        issueList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    populateDescription(newValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadOrderNumbers() {
        try (BufferedReader br = new BufferedReader(new FileReader("techsupport.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length > 0) {
                    issueList.getItems().add(data[0]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void goto_admin_click(ActionEvent event) {
        try{
            BufferedReader br= new BufferedReader(new FileReader("T_S_access.txt"));
            String line=br.readLine();


            if(line.equals("Yes")){
                utility.changeScene("adminpage.fxml",event,"Admin",1000,900);
                return;
            }




        }
        catch(Exception e){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Get access from Admin");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    private void populateDescription(String selectedOrder) {
        try (BufferedReader br = new BufferedReader(new FileReader("techsupport.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length > 1 && data[0].equals(selectedOrder)) {
                    description.setText(data[1]);
                    return;
                }
            }

            description.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
