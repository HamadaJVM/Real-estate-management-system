package com.example.brocodedemo;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableRow;
public class HelloController {
    @FXML
    private Button btn_buy;

    @FXML
    private Button btn_sell;

    @FXML
    private Button btn_update;

    @FXML
    private TableColumn<Info, String> colomn_address;

    @FXML
    private TableColumn<Info, String> colomn_area;

    @FXML
    private TableColumn<House, String> colomn_floors;

    @FXML
    private TableColumn<Info, String> colomn_id;

    @FXML
    private TableColumn<Info, String> colomn_law_info;

    @FXML
    private TableColumn<Info, String> colomn_price;

    @FXML
    private TableColumn<House, String> colomn_rooms;

    @FXML
    private TableColumn<House, String> colomn_year_of_creation;

    @FXML
    private TableView<House> table_main;

    @FXML
    private TextField txt_address;

    @FXML
    private TextField txt_area;

    @FXML
    private TextField txt_floor_number;

    @FXML
    private Label txt_id;

    @FXML
    private TextField txt_law_info;

    @FXML
    private TextField txt_price;

    @FXML
    private TextField txt_room_number;

    @FXML
    private TextField txt_year_of_creation;

    @FXML
    void Buy(ActionEvent event) {

        String lawInfo , address,yearOfCreation,price, area,floors,rooms;


        floors = txt_floor_number.getText();
        rooms = txt_room_number.getText();
        price = txt_price.getText();
        area = txt_area.getText();
        lawInfo = txt_law_info.getText();
        address = txt_address.getText();
        yearOfCreation = txt_year_of_creation.getText();
        try
        {
            pst = con.prepareStatement("insert into main(price,address,area,lawInfo,floorNumber,roomNubmer,yearOfCreation) " +
                    "values(?,?,?,?,?,?,?)");
            try {
                pst.setString(1, price);
                pst.setString(2, address);
                pst.setString(3, area);
                pst.setString(4, lawInfo);
                pst.setString(5, floors);
                pst.setString(6, rooms);
                pst.setString(7, yearOfCreation);
                pst.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Buying");

                alert.setHeaderText("Buying process");
                alert.setContentText("Record Addedddd!");

                alert.showAndWait();
                table();

                txt_id.setText("");
                txt_price.setText("");
                txt_address.setText("");
                txt_area.setText("");
                txt_law_info.setText("");
                txt_floor_number.setText("");
                txt_room_number.setText("");
                txt_year_of_creation.setText("");

                txt_id.requestFocus();
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Enter right values plz");
                txt_price.setText("");
                txt_area.setText("");
                txt_floor_number.setText("");
                txt_room_number.setText("");
                txt_year_of_creation.setText("");


                alert.showAndWait();
            }


        }
        catch (SQLException ex)
        {
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void table()
    {
        Connect();
        ObservableList<House> houses = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select id,price,address,area,lawInfo,floorNumber,roomNubmer,yearOfCreation from main");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    House house = new House();
                    house.setId(rs.getString("id"));
                    house.setPrice(rs.getString("price"));
                    house.setAddress(rs.getString("address"));
                    house.setArea(rs.getString("area"));
                    house.setLawInfo(rs.getString("lawInfo"));
                    house.setFloorNumber(rs.getString("floorNumber"));
                    house.setRoomNumber(rs.getString("roomNubmer"));
                    house.setYearOfCreation(rs.getString("yearOfCreation"));
                    houses.add(house);
                }
            }
            table_main.setItems(houses);
            colomn_id.setCellValueFactory(f -> f.getValue().idProperty());
            colomn_price.setCellValueFactory(f -> f.getValue().priceProperty());
            colomn_address.setCellValueFactory(f -> f.getValue().addressProperty());
            colomn_area.setCellValueFactory(f -> f.getValue().areaProperty());
            colomn_law_info.setCellValueFactory(f -> f.getValue().lawInfoProperty());
            colomn_floors.setCellValueFactory(f -> f.getValue().floorNumberProperty());
            colomn_rooms.setCellValueFactory(f -> f.getValue().roomNumberProperty());
            colomn_year_of_creation.setCellValueFactory(f -> f.getValue().yearOfCreationProperty());



        }

        catch (SQLException ex)
        {
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table_main.setRowFactory( tv -> {
            TableRow<House> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  table_main.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(table_main.getItems().get(myIndex).getId()));

                    txt_id.setText(table_main.getItems().get(myIndex).getId());
                    txt_price.setText(table_main.getItems().get(myIndex).getPrice());
                    txt_address.setText(table_main.getItems().get(myIndex).getAddress());
                    txt_area.setText(table_main.getItems().get(myIndex).getArea());
                    txt_law_info.setText(table_main.getItems().get(myIndex).getLawInfo());
                    txt_room_number.setText(table_main.getItems().get(myIndex).getRoomNumber());
                    txt_floor_number.setText(table_main.getItems().get(myIndex).getFloorNumber());
                    txt_year_of_creation.setText(table_main.getItems().get(myIndex).getYearOfCreation());




                }
            });
            return myRow;
        });


    }

    @FXML
    void Sell(ActionEvent event) {

    }

    @FXML
    void Update(ActionEvent event) {
    }

    // plug in the database
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;

    public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/opp","root","");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("The error is here bitch");
        }
    }
    public void initialize(){
    Connect();
    table();
    }
}