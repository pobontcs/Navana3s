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

public class HelloController implements Initializable{
    @FXML
    private ComboBox<String> Select_user;
    private Stage stage;
    private Scene scene;

    @FXML
    private TextField Sign_in_Input;
    @FXML
    private PasswordField Sign_in_password;

    @FXML
    public Button sign_in_Button;
    @FXML
    public Button enter_Shop_Button;

    private final String[] users={
            "Admin","Inventory Manager","Transaction","Mechanic","Workshop Manager"

    };
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException {
            if(Select_user!=null) {
                Select_user.setItems(FXCollections.observableArrayList(users));
            }
    }

     public void sign_in_click(ActionEvent event) throws IOException,NullPointerException {

         String username = Sign_in_Input.getText();
         String password = Sign_in_password.getText();
         String user_type= Select_user.getValue();
         if (user_type==null){
             Alert alert= new Alert(AlertType.ERROR);
             alert.setTitle("Selection Error");
             alert.setHeaderText("Cant select both Admin and Customer");
             alert.showAndWait();
         }
         assert user_type != null;

         if(user_type.equals("Transaction")){try{
             if(username.equals("finance") && password.equals("1234")){

                 FXMLLoader Loader = new FXMLLoader(HelloController.class.getResource("/com/example/navana3s/transaction.fxml"));
                 Parent root =Loader.load();
                 scene = new Scene(root,1000,1000);
                 stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                 stage.setTitle("Transaction Page");
                 stage.setScene(scene);
                 stage.show();

             }
             else{
                 Alert alert= new Alert(AlertType.ERROR);
                 alert.setTitle("Selection Error");
                 alert.setHeaderText("Incorrect Id or Password");

                 alert.showAndWait();
             }
         }catch (Exception e){
             Alert alert= new Alert(AlertType.ERROR);
             alert.setTitle("Selection Error");
             alert.setHeaderText("Still invalid");

             alert.showAndWait();
         }}


         else if (user_type.equals("Admin")){
             if (username.equals("admin") && password.equals("1234")) {
                 // Load the new scene
                 FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminpage.fxml"));
                 Parent root = fxmlLoader.load();
                 scene = new Scene(root, 800, 800);

                 // Get the stage from the event
                 stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.setTitle("Admin Page");
                 stage.setScene(scene);
                 stage.show();

             }
             else {
                 Alert alert = new Alert(AlertType.ERROR);
                 alert.setTitle("Invalid Password");
                 alert.showAndWait();
             }





         }
     }
     public void On_Shop_Click(ActionEvent event) throws
             IOException

     {
         FXMLLoader fxmlLoader= new FXMLLoader(HelloApplication.class.getResource(
                 "Customer.fxml"
         ));
         Parent
                 root =fxmlLoader.load();
         scene =new Scene(root,800,800);
         stage=(Stage)((Node)event.getSource()).getScene().getWindow();
         stage.setTitle("Navana 3s");
         stage.setScene(scene);
         stage.show();

     }






}