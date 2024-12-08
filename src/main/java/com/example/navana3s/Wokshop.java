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
public class Wokshop implements Initializable {
@FXML
    private TableView<Order> pending_table;
@FXML
private TableColumn<Order,String> A_order_column;
@FXML
private TableColumn<Order,String> A_name_column;
@FXML
private TableColumn<Order,String> A_deadline_column;
@FXML
private TableView<Order> progress_TAble;
@FXML
private TableColumn<Order,String>order_P_column;
@FXML
private TableColumn<Order,String>date_P_column;
@FXML
private TextField a_mechanic_name;
@FXML
private TextField a_order;
@FXML
private DatePicker a_deadline;
private ObservableList<Order> orderata = FXCollections.observableArrayList();
private ObservableList<Order> ORderata = FXCollections.observableArrayList();
    public void initialize(URL url,ResourceBundle resourceBundle) {
// Bind table columns to Order properties

    }

    public double ammount(List<String>services){
        double price=0.00;

        for(String service: services){
              if(service.equals("Engine")){
                price+=20000.00;
            } else if(service.equals("Transmission")){
                price+=7000.00;
            } else if (service.equals("Brake")) {
                price+=5500.00;
            } else if (service.equals("Tire")) {
                price+=12000.00;
            } else if (service.equals("Exhaust")) {
                price+=4000.00;
            } else if (service.equals("Cooling System")) {
                price+=3000;
            }
              else if (service.equals("Fuel System")) {
                price+=2000.00;
            }

        }

        return price;
    }
    public void on_assign_button_click(ActionEvent actionEvent) throws IOException {

                            String name=a_mechanic_name.getText();
                            LocalDate date=a_deadline.getValue();
                            String dateStr=date.toString();
                            String orderNUm=a_order.getText();

                            try{
                                BufferedWriter bw =new BufferedWriter(new FileWriter("Assigned.txt",true));
                                bw.write(name);
                                bw.write(";");
                                bw.write(orderNUm);
                                bw.write(";");
                                bw.write(dateStr);
                                bw.newLine();
                                bw.close();
                                System.out.println("Crossed\n");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try{
                                BufferedReader br = new BufferedReader(new FileReader("Assigned.txt"));
                                String line;List<Order> A_orders = new ArrayList<>();
                                while((line= br.readLine())!=null){
                                    String[] orders = line.split(";");
                                    String orderNo=orders[1];
                                    String deadlineStr=orders[2];
                                    String m_name=orders[0];
                                    Order ord= new Order(orderNo,m_name,deadlineStr);
                                    A_orders.add(ord);
                                }ORderata.clear();
                                ORderata.addAll(A_orders);
                                A_order_column.setCellValueFactory(new PropertyValueFactory<>("orderNo"));
                                A_deadline_column.setCellValueFactory(new PropertyValueFactory<>("date"));
                                A_name_column.setCellValueFactory(new PropertyValueFactory<>("m_name"));
                                progress_TAble.setItems(ORderata);


                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
    }

    public void on_refresh_button(ActionEvent event) throws IOException{
                try{
                    BufferedReader br= new BufferedReader(new FileReader("order.txt"));
                    List<Order> orders = new ArrayList<>();
                    String line;

                    while((line= br.readLine())!=null){
                                    String[] parts = line.split(";");
                                    String orderNo=parts[0];
                                    List<String>services = new ArrayList<>();

                                    for(int i =1;i< parts.length-1;i++){
                                        services.add(parts[i]);
                                    }
                                    String date = parts[parts.length-1];
                                    double amount=ammount(services);System.out.println(amount);
                                    Order order= new Order(orderNo,date,amount);
                                    orders.add(order);
                    }

                    orderata.clear();
                    orderata.addAll(orders);
                    order_P_column.setCellValueFactory(new PropertyValueFactory<>("orderNo"));
                    date_P_column.setCellValueFactory(new PropertyValueFactory<>("date"));


                    pending_table.setItems(orderata);



                }
                catch(Exception e){
                                        e.printStackTrace();
                }

    }
}
