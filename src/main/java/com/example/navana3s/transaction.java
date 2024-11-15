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
public class transaction extends database implements Initializable{
@FXML
    private Label revenue_text;
@FXML
    private Label pending_order_text;
@FXML
    private Label pending_salary_text;

@FXML
    private Label M_revenue_text;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException {

    }
    database db = new database();

 public  void test_Button(ActionEvent event){
            String s1=revenue_text.getText();
            db.writeFile("output.txt",s1);
            String s2=pending_order_text.getText();
            db.writeFile("output.txt",s2);

    }
}
