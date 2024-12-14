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
import java.net.*;
import java.util.*;
import java.util.ResourceBundle;

public class Adminpage implements Initializable {

 private Stage stage;
 private Scene scene;
 @FXML
 private CheckBox tech_support_access;
 @FXML
 private TextField m_id;


  @FXML
 private TableView<data>mechanic_data_table;
  @FXML
  private TableColumn<data,String> mechanic_name_column;
 @FXML
 private TableColumn<data,String> mechanic_id_column;
 private ObservableList<data> Data_Table = FXCollections.observableArrayList();

 @Override
 public void initialize(URL location, ResourceBundle resources) {
  if(tech_support_access.isSelected()){
   tech_support_access.setSelected(true);
  }
  on_status_button();

 }
 public void on_status_button() throws NullPointerException  {
  List<data> ls = new ArrayList<>();
  try{
   BufferedReader br= new BufferedReader(new FileReader("mechanic_data.txt"));
   String line;
   while ((line = br.readLine()) != null) {
    String[] patch = line.split(";");
    data dt= new data(patch[0],patch[1]);
    ls.add(dt);
   }
   mechanic_name_column.setCellValueFactory(new PropertyValueFactory<>("Name"));
   mechanic_id_column.setCellValueFactory(new PropertyValueFactory<>("Status"));
   Data_Table.clear();
   Data_Table.addAll(ls);
   mechanic_data_table.setItems(Data_Table);

  }
  catch (Exception e){
   e.printStackTrace();
  }
 }


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
public void on_trans_click(ActionEvent event) throws IOException {
 utility.changeScene("transaction.fxml",event,"Finance",1100,1000);
}
 public void on_inventory_click(ActionEvent event) throws IOException {
 utility.changeScene("inventory.fxml",event,"Inventory",1100,1000);
 }
 public void on_workshop_click(ActionEvent event) throws IOException {
 utility.changeScene("workshop.fxml",event,"Workshop",1100,1000);
 }

 public void on_confirmation_click(ActionEvent event) throws IOException {
               if(tech_support_access.isSelected()){
                BufferedWriter bw = new BufferedWriter(new FileWriter("T_S_access.txt"));
                bw.write("Yes");
                bw.close();
               }
                if(!tech_support_access.isSelected()){
                 BufferedWriter bw = new BufferedWriter(new FileWriter("T_S_access.txt"));
                 bw.write("");
                 bw.close();
                }
               utility.changeScene("hello-view.fxml",event,"Navana3s",1100,1000);
 }






 public void on_access_click(ActionEvent event) throws IOException {
  String ID = m_id.getText();
  m_id.setText("");

  try  {
   BufferedReader br = new BufferedReader(new FileReader("mechanic_data.txt"));
   BufferedWriter bw = new BufferedWriter(new FileWriter("chosen.txt"));
   String line;
   while ((line = br.readLine()) != null) {
    String[] patch = line.split(";");
    if (patch[1].equals(ID)) {
     bw.write(patch[0] + ";" + patch[1] + "\n");
     break;
    }
   }
   br.close();
   bw.close();

  } catch (Exception e) {
   e.printStackTrace();
   throw new RuntimeException(e);
  }utility.changeScene("mechanic.fxml", event, "Mechanic", 900, 900);
 }


}
