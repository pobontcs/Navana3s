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
private TableView<data> mechanic_status_table;
    @FXML
    private TextField s_m_name;
    @FXML
    private TextField s_m_id;
    @FXML
    private TextField s_role;

@FXML
private TableColumn<Order,String> A_order_column;
@FXML
private TableColumn<Order,String> A_name_column;
@FXML
private TableColumn<Order,String> A_deadline_column;
@FXML
private TableColumn<data,String> mech_name;
@FXML
private TableColumn<data,String> status_on;

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
private ObservableList<data> Data_Table = FXCollections.observableArrayList();
   @Override
    public void initialize(URL url,ResourceBundle resourceBundle) {
// Bind table columns to Order properties
        on_status_button();

    }
    public void linedelete(String filepath, String order_No) throws EOFException{
        String chosen;
        List<String>lst=new ArrayList<String>();
        try{

            BufferedReader br=new BufferedReader(new FileReader(filepath));

            String line;
            while ((line = br.readLine()) != null) {

                String[] patch = line.split(";");

                if (patch.length > 0 && !patch[0].equals(order_No)) {
                    lst.add(line);
                }

        }
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
            for (String line : lst) {

                bw.write(line);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



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
                            mech_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
                            status_on.setCellValueFactory(new PropertyValueFactory<>("Status"));
                            Data_Table.clear();
                            Data_Table.addAll(ls);
                            mechanic_status_table.setItems(Data_Table);

                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
    }
    public void on_save_button(ActionEvent event) throws NullPointerException {
        String Name=s_m_name.getText();
        String ID=s_m_id.getText();
        String role=s_role.getText();
        if(s_m_name==null|| s_m_id==null|| s_role==null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill out all fields");
            alert.showAndWait();
        }
        else{
            try {
                    BufferedWriter bw =new BufferedWriter(new FileWriter("mechanic_data.txt",true));
                    bw.write(Name);
                    bw.write(";");
                    bw.write(ID);
                    bw.write(";");
                    bw.write(role);
                    bw.newLine();
                    bw.close();
                    System.out.println("Crossed\n");
                    s_m_name.clear();
                    s_m_id.clear();
                    s_role.clear();
                    BufferedWriter bwd =new BufferedWriter(new FileWriter("salary.txt",true));
                    bwd.write(ID);
                    bwd.write(";");
                    bwd.write("unclear");
                    bwd.newLine();

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

}
    public void on_assign_button_click(ActionEvent actionEvent) throws IOException {
        String name = a_mechanic_name.getText();
        LocalDate date = a_deadline.getValue();
        String dateStr = date != null ? date.toString() : "N/A";
        String orderNum = a_order.getText();

        // Writing to "Assigned.txt"
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Assigned.txt", true));
            bw.write(name);
            bw.write(";");
            bw.write(orderNum);
            bw.write(";");
            bw.write(dateStr);
            bw.newLine();
            bw.close();
            System.out.println("Entry added to Assigned.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader("Assigned.txt"));
            String line;
            List<Order> A_orders = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] orders = line.split(";");

                if (orders.length == 3) {
                    String orderNo = orders[1];
                    String deadlineStr = orders[2];
                    String m_name = orders[0];

                    Order ord = new Order(orderNo, m_name, deadlineStr);
                    A_orders.add(ord);
                } else {
                    System.err.println("Malformed line: " + line);
                }
            }

            br.close();
            ORderata.clear();
            ORderata.addAll(A_orders);
            A_order_column.setCellValueFactory(new PropertyValueFactory<>("orderNo"));
            A_deadline_column.setCellValueFactory(new PropertyValueFactory<>("date"));
            A_name_column.setCellValueFactory(new PropertyValueFactory<>("m_name"));
            progress_TAble.setItems(ORderata);

        } catch (Exception e) {
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

                                    for(int i =1;i< parts.length-3;i++){
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
