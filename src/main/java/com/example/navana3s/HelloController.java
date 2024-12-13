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

import java.io.*;
import java.net.*;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.ResourceBundle;

public class HelloController extends database implements Initializable{
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
            "Admin","Inventory","Transaction","Mechanic","Workshop Manager"

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
         if(user_type.equals("Mechanic")){
             boolean flag=false;
            try {
                BufferedReader br = new BufferedReader(new FileReader("mechanic_data.txt"));
                BufferedWriter bw=new BufferedWriter(new FileWriter("chosen.txt"));
                String line;
                while((line= br.readLine())!=null)
                {
                    String[] data=line.split(";");
                    if(password.equals(data[1])){
                        bw.write(data[0]);
                        bw.write(";");
                        bw.write(data[1]);
                        bw.newLine();
                        flag=true;
                        break;
                    }

                }
                br.close();
                bw.close();
                if(flag){
                    FXMLLoader Loader = new FXMLLoader(HelloApplication.class.getResource("mechanic.fxml"));
                    Parent root =Loader.load();
                    scene = new Scene(root,800,800);

                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.close();
                    Stage transactionStage = new Stage();
                    transactionStage.setTitle("Welcome");
                    transactionStage.setScene(scene);
                    transactionStage.show();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
         }

         if(user_type.equals("Transaction")){

             try{
             if(username.equals("finance") && password.equals("1234")){

                 FXMLLoader Loader = new FXMLLoader(HelloApplication.class.getResource("transaction.fxml"));
                 Parent root =Loader.load();
                 scene = new Scene(root,1200,800);

                 Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 currentStage.close();
                 Stage transactionStage = new Stage();
                 transactionStage.setTitle("Transaction Page");
                 transactionStage.setScene(scene);
                 transactionStage.show();
             }

             else{
                 Alert alert= new Alert(AlertType.ERROR);
                 alert.setTitle("Selection Error");
                 alert.setHeaderText("Incorrect Id or Password");
                 alert.showAndWait();
             }

         }
             catch (Exception e){
             Alert alert= new Alert(AlertType.ERROR);
             alert.setTitle("Selection Error");
             alert.setHeaderText("Still invalid");

             alert.showAndWait();
         }}
         else if (user_type.equals("Inventory")) {
             try{
                 if(username.equals("inventory") && password.equals("1234")){

                     FXMLLoader Loader = new FXMLLoader(HelloApplication.class.getResource("inventory.fxml"));
                     Parent root =Loader.load();
                     scene = new Scene(root,1400,1000);

                     Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                     currentStage.close();
                     Stage transactionStage = new Stage();
                     transactionStage.setTitle("Inventory");
                     transactionStage.setScene(scene);
                     transactionStage.show();
                 }

                 else{
                     Alert alert= new Alert(AlertType.ERROR);
                     alert.setTitle("Selection Error");
                     alert.setHeaderText("Incorrect Id or Password");
                     alert.showAndWait();
                 }

             }
             catch (Exception e){
                 Alert alert= new Alert(AlertType.ERROR);
                 alert.setTitle("Selection Error");
                 alert.setHeaderText("Still invalid");

                 alert.showAndWait();
             }

         } else if (user_type.equals("Admin")){
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
else if (user_type.equals("Workshop Manager")){
             if (username.equals("Manager") && password.equals("1234")) {
                 // Load the new scene
                 FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("wokshop.fxml"));
                 Parent root = fxmlLoader.load();
                 scene = new Scene(root, 1300, 1100);

                 // Get the stage from the event
                 stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.setTitle("Manager");
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