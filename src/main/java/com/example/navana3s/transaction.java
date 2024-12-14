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
public class transaction extends database  implements Initializable{
@FXML
    private TextField M_revenue_text;
@FXML
private TableView<stock> stock_table;
@FXML
private TableColumn<stock, String> stock_name;
@FXML
private TableColumn<stock, String> stock_price;
@FXML
    private Label pending_order_text;
@FXML
    private Label pending_salary_text;
@FXML private TextField Clearence_id;
@FXML
private TextField clear_order_no;
@FXML
private TextField clear_order_ammount;@FXML
private TextField salary_ammount;


    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableView<data> mechanic_table;
    @FXML
    private TableColumn<data,String> m_name; @FXML
    private TableColumn<data,String> m_clearence;
    @FXML
    private TableColumn<Order, Integer> order_no;
    @FXML
    private TableColumn<Order, String> date_no;
    @FXML
    private TableColumn<Order, Double> ammount_no;
    private ObservableList<Order> orderData = FXCollections.observableArrayList();
    private double revenue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        test_Button();
        init_stock();
        init_revenue();
        monthly_init();
        mech_init();

    }
    public void monthly_init() {

        LocalDate currentDate = LocalDate.now();
        if (currentDate.getDayOfMonth() == 1) {
            try {
                BufferedReader br = new BufferedReader(new FileReader("salary.txt"));
                List<String> updatedMechanicData = new ArrayList<>();
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(";");
                    if (data.length >= 2) {
                        data[1] = "unclear";
                        updatedMechanicData.add(String.join(";", data));
                    }
                }
                br.close();
                BufferedWriter bw = new BufferedWriter(new FileWriter("salary.txt"));
                for (String updatedLine : updatedMechanicData) {
                    bw.write(updatedLine);
                    bw.newLine();
                }
                bw.close();
                mech_init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void mech_init(){
                try{
                    BufferedReader br=new BufferedReader(new FileReader("salary.txt"));
                    String line;
                    ObservableList<data> mech_data=FXCollections.observableArrayList();
                    mech_data.clear();
                    while ((line = br.readLine()) != null) {
                        String[] patch=line.split(";");
                        data dt=new data(patch[0],patch[1]);
                        mech_data.add(dt);
                    }
                    m_name.setCellValueFactory(new PropertyValueFactory<>("name"));
                    m_clearence.setCellValueFactory(new PropertyValueFactory<>("status"));
                    mechanic_table.setItems(mech_data);

                }
                catch (Exception e){
                    e.printStackTrace();
                }
       }

        public void on_clearence_click() {
            String name = Clearence_id.getText();
            String amount = salary_ammount.getText();

            try {
                BufferedReader br = new BufferedReader(new FileReader("salary.txt"));
                String line;
                List<String> ls = new ArrayList<>();

                while ((line = br.readLine()) != null) {
                    String[] data = line.split(";");
                    if (data[0].equals(name) && data[1].equals("unclear")) {

                        ls.add(data[0] + ";clear");
                    } else {

                        ls.add(data[0] + ";" + data[1]);
                    }
                }
                br.close();

                BufferedWriter bw = new BufferedWriter(new FileWriter("salary.txt"));
                for (String l : ls) {
                    bw.write(l);
                    bw.newLine();
                }
                bw.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try{
                BufferedReader br= new BufferedReader(new FileReader("revenue.txt"));
                String line=br.readLine();
                double curr=Double.parseDouble(line);
                if(Double.parseDouble(amount)>=curr){
                    Alert aler=new Alert(AlertType.ERROR);
                    aler.setContentText("Unsuffcient Balance");
                    aler.showAndWait();
                }
                else{
                BufferedWriter bw=new BufferedWriter(new FileWriter("revenue.txt"));
                curr=curr-Double.parseDouble(amount);
                bw.write(Double.toString(curr));
                bw.close();

                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            mech_init();
            init_revenue();
        }



            public void init_revenue(){
        try{
            BufferedReader br= new BufferedReader(new FileReader("revenue.txt"));
            revenue=Double.parseDouble(br.readLine());
    M_revenue_text.setText(Double.toString(revenue));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void update_revenue(double ammount){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("revenue.txt"));
            bw.write(Double.toString(revenue));
            bw.newLine();
            bw.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean isItemAvailable(String[] array, String item) {
        for (String element : array) {
            if (element.equals(item)) {
                return true; // Item found
            }
        }
        return false; // Item not found
    }
                public void init_stock(){
                                    ObservableList<stock> orderata = FXCollections.observableArrayList();
        try{
            orderata.clear();
                                    BufferedReader br = new BufferedReader(new FileReader("inventory.txt"));

                                    String line;
                                    while ((line=br.readLine()) != null) {
                                           String[] data = line.split(";");

                                           String Name= data[0]+" "+ data[1];


                                           String price=data[data.length-1];
                                           stock stk=new stock(Name,price);
                                           orderata.add(stk);

                                    }
                                    stock_name.setCellValueFactory(new PropertyValueFactory<>("stock_name"));
                                    stock_price.setCellValueFactory(new PropertyValueFactory<>("price"));
                                    stock_table.setItems(orderata);

                                }
        catch (IOException e){
            e.printStackTrace();
        }
                }




    database db = new database();
    public double load_price_order(String order_no){
        double price = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("order.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if(data[0].equals(order_no)){
                    for(int i=1;i<data.length-1;i++){
                        if(data[i].equals("Engine")){
                            price+=40000.00;
                        }else if(data[i].equals("Transmission")){
                            price+=30000.00;
                        }else if(data[i].equals("Electrical")){
                            price+=10000.00;
                        }else if(data[i].equals("Brake")){
                            price+=8000.00;
                        }else if(data[i].equals("Tire")){
                            price+=20000.00;
                        }else if(data[i].equals("Exhaust")){
                            price+=30000.00;
                        }
                        else if(data[i].equals("Fuel System")){
                            price+=9000.00;
                        }
                        else if(data[i].equals("Cooling System")){
                            price+=11000.00;
                        }
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return price;
    }
    public void on_clear_bill() {
        String order_no = clear_order_no.getText();
        String order_ammount = clear_order_ammount.getText();

        if (order_no.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Order Number Missing");
            alert.setContentText("Please fill out the Order Number.");
            alert.showAndWait();
            return;
        }

        try {
            double orderAmount = 0.0;
            if (!order_ammount.isEmpty()) {
                try {
                    orderAmount = Double.parseDouble(order_ammount);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Amount");
                    alert.setContentText("Please enter a valid numeric amount or leave it blank.");
                    alert.showAndWait();
                    return;
                }
            }

            ObservableList<Order> updatedOrderData = FXCollections.observableArrayList();
            List<String> remainingOrders = new ArrayList<>();
            List<String> remainingAssignments = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("order.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(";");
                    if (!data[0].equals(order_no)) {
                        double total = load_price_order(data[0]);
                        revenue += total;
                        Order ord = new Order(data[0], data[data.length - 1], total);
                        updatedOrderData.add(ord);
                        remainingOrders.add(String.join(";", data));
                    }
                }
            }

            try (BufferedReader br = new BufferedReader(new FileReader("assigned.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(";");
                    if (!data[0].equals(order_no)) {
                        remainingAssignments.add(String.join(";", data));
                    }
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("order.txt"))) {
                for (String order : remainingOrders) {
                    bw.write(order);
                    bw.newLine();
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("assigned.txt"))) {
                for (String assignment : remainingAssignments) {
                    bw.write(assignment);
                    bw.newLine();
                }
            }

            // Update UI and show success message
            orderTable.setItems(updatedOrderData);
            Alert successAlert = new Alert(AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText("Order Cleared");
            successAlert.setContentText("Order " + order_no + " has been cleared successfully.");
            successAlert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("File Error");
            alert.setContentText("An error occurred while processing the files.");
            alert.showAndWait();
        }

        update_revenue(revenue);
        init_revenue();
        M_revenue_text.setText(Double.toString(revenue));
        clear_order_no.clear();
        clear_order_ammount.clear();
    }





    public void load_demo_data(){
            orderData.clear();
        try{
            BufferedReader br = new BufferedReader(new FileReader("order.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                double total = load_price_order(data[0]);
                Order ord=new Order(data[0],data[data.length-1],total);
                orderData.add(ord);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        orderTable.setItems(orderData);

    }

 public  void test_Button(){
     order_no.setCellValueFactory(new PropertyValueFactory<>("orderNo"));
     date_no.setCellValueFactory(new PropertyValueFactory<>("date"));
     ammount_no.setCellValueFactory(new PropertyValueFactory<>("amount"));

     // Load demo data
     load_demo_data();

     // Set the data to the TableView
     orderTable.setItems(orderData);

    }
}
