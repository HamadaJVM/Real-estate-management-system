package com.example.brocodedemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class FinanceController {

    @FXML
    private TextField txt_add;

    @FXML
    private TextField txt_cash;

    @FXML
    private TextField txt_cut;

    @FXML
    private TextField txt_property;

    @FXML
    private TextField txt_net_worth;

    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void updateButton(ActionEvent event){
        String add = null, cut =null;
        add = txt_add.getText();
        cut = txt_cut.getText();
        Finance.AddOnTotalCash(add);
        Finance.cutFromTotalCash(cut);
        updateValues();

    }
    public  void updateValues(){
        //calculateTotalValue();
        Finance.updateAllNetWorth();
        String p = Finance.getValueOfProperties()+"";
        String c = Finance.getTotalCash()+"";
        String n = Finance.getAllNetWorth()+"";
        txt_cash.setText(c);
        txt_property.setText(p);
        txt_net_worth.setText(n);
    }
}
