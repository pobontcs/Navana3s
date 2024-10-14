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

import java.io.IOException;
import java.net.*;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.ResourceBundle;

public class HelloController  {

    private Stage stage;
    private Scene scene;

    @FXML
    private TextField Sign_in_Input;
    @FXML
    private TextField Sign_in_password;
    @FXML
    private CheckBox Customer_check;
    @FXML
    private CheckBox Admin_check;
    @FXML
    public Button sign_in_Button;
     public void sign_in_click(ActionEvent event) throws IOException {
         String username = Sign_in_Input.getText();
         String password = Sign_in_password.getText();
         if (Admin_check.isSelected() && Customer_check.isSelected()){
             Alert alert= new Alert(AlertType.ERROR);
             alert.setTitle("Selection Error");
             alert.setHeaderText("Cant select both Admin and Customer");

             alert.showAndWait();
         }
         else if (Admin_check.isSelected()){
             if (username.equals("admin") && password.equals("1234")) {
                 // Load the new scene
                 FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminpage.fxml"));
                 Parent root = fxmlLoader.load();
                 scene = new Scene(root, 500, 500);

                 // Get the stage from the event
                 stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.setTitle("Admin Page");
                 stage.setScene(scene);
                 stage.show();

             }
         }
     }




}