package com.example.brocodedemo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
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
import javafx.stage.Stage;

public class MainController {
    //تعريف المتغيرات
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
        String lawInfo, address, yearOfCreation, price, area, floors = null, rooms = null;
        if (!Objects.equals(txt_floor_number.getText(), "")) {
            floors = txt_floor_number.getText();
        }
        if (!Objects.equals(txt_room_number.getText(), "")) {
            rooms = txt_room_number.getText();
        }

        price = txt_price.getText();
        area = txt_area.getText();
        lawInfo = txt_law_info.getText();
        address = txt_address.getText();
        yearOfCreation = txt_year_of_creation.getText();
        calculateTotalValue();

        try {
            pst = con.prepareStatement("insert into main(price,address,area,lawInfo,floorNumber,roomNumber,yearOfCreation) " +
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
                alert.setContentText("DONE !");

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

                //استقطاع من الكاش بعد الشراء
                Finance.cutFromTotalCash(Float.parseFloat(price));
                //temprroy
                Finance.displayFinance();
            } catch (Exception e) {
                System.out.println(e.getMessage());
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


        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    void Sell(ActionEvent event) {
        String id, price;
        id = txt_id.getText();
        price = txt_price.getText();
        calculateTotalValue();



        // من المحتمل ان المستخدم يعدل على السعر قبل البيع ومحتمل مايضغط على زر التحديث
        //لذلك بداخل دالة البيع راح نخلي يحدث السعر بشكل مباشر مباشرة عند الضغط على زر البيع
        try {
            pst = con.prepareStatement("UPDATE main SET price=?");
            pst.setString(1, price);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Enter right values plz");
            alert.showAndWait();
            txt_price.setText("");

        }
        //

        try {
            pst = con.prepareStatement("DELETE FROM main WHERE id=?");
            pst.setInt(1, Integer.parseInt(id));
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sell");
            alert.setHeaderText("Sell process");
            alert.setContentText("DONE !");
            alert.showAndWait();
            table();
            setTextsEmpty();
            txt_price.requestFocus();
            //استقطاع الفلوس من الكاش
            Finance.AddOnTotalCash(Float.parseFloat(price));
            calculateTotalValue();

            //temprroy
            Finance.displayFinance();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Enter right values plz");
            alert.showAndWait();
            setTextsEmpty();
        }
    }


    @FXML
    void Update(ActionEvent event) {
        String lawInfo, address, yearOfCreation, price, area, floors, rooms, id;
        id = txt_id.getText();
        floors = txt_floor_number.getText();
        rooms = txt_room_number.getText();
        price = txt_price.getText();
        area = txt_area.getText();
        lawInfo = txt_law_info.getText();
        address = txt_address.getText();
        yearOfCreation = txt_year_of_creation.getText();
        calculateTotalValue();


        try {
            pst = con.prepareStatement("UPDATE main SET price=?, address=?, area=?, lawInfo=?, floorNumber=?, roomNumber=?, yearOfCreation=? WHERE id=?");
            pst.setString(1, price);
            pst.setString(2, address);
            pst.setString(3, area);
            pst.setString(4, lawInfo);
            pst.setString(5, floors);
            pst.setString(6, rooms);
            pst.setString(7, yearOfCreation);
            pst.setInt(8, Integer.parseInt(id));
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update");
            alert.setHeaderText("Update process");
            alert.setContentText("DONE !");
            alert.showAndWait();
            table();
            txt_price.requestFocus();
            calculateTotalValue();
            //temprroy
            Finance.displayFinance();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Enter right values plz");
            alert.showAndWait();
            setTextsEmpty();
        }
    }

    public void table() {
        Connect();
        ObservableList<House> houses = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select id,price,address,area,lawInfo,floorNumber,roomNumber,yearOfCreation from main");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    House house = new House();
                    house.setId(rs.getString("id"));
                    house.setPrice(rs.getString("price"));
                    house.setAddress(rs.getString("address"));
                    house.setArea(rs.getString("area"));
                    house.setLawInfo(rs.getString("lawInfo"));
                    house.setFloorNumber(rs.getString("floorNumber"));
                    house.setRoomNumber(rs.getString("roomNumber"));
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


        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table_main.setRowFactory(tv -> {
            TableRow<House> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = table_main.getSelectionModel().getSelectedIndex();
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

    public void calculateTotalValue() {
        // هاي الدالة تاخذ تنطيني مجموع قيم الاسعار مال كل الاملاك
        try {
            String query = "SELECT SUM(price) as total FROM main";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                double total = rs.getDouble("total");
                Finance.setValueOfProperties((float) total);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setTextsEmpty() {
        txt_id.setText("");
        txt_address.setText("");
        txt_price.setText("");
        txt_area.setText("");
        txt_law_info.setText("");
        txt_floor_number.setText("");
        txt_room_number.setText("");
        txt_year_of_creation.setText("");
    }

    //connecting the App with local Database
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;

    public void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/opp", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("The error is here bitch");
        }
    }

    public void initialize() {
        Connect();
        table();
    }

    //Switch between Scenes
    @FXML
    private Label welcomeText;

    @FXML
    public void switchToScene2(ActionEvent event) throws IOException {
         Stage stage;
         Scene scene;
         Parent root;
       // calculateTotalValue();
        root = FXMLLoader.load(getClass().getResource("finance.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

}