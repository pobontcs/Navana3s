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

public class Adminpage {

 private Stage stage;
 private Scene scene;



@FXML
protected void on_back_button(ActionEvent event)throws IOException {
 FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
 Parent root =fxmlLoader
         .load();
 scene =new Scene(root,800,800);
 stage=(Stage)((Node) event.getSource()).getScene().getWindow();

 stage.setTitle("Login");
 stage.setScene(scene);
 stage.show();
}

}
