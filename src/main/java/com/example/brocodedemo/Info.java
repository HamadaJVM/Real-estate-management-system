package com.example.brocodedemo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Scanner;
public class Info extends Finance{

    private final StringProperty id ;
    private final StringProperty area ;
    private final StringProperty lawInfo ;
    private final StringProperty address ;
    private final StringProperty price ;

    public Info() {
        id = new SimpleStringProperty(this,"id");
        area = new SimpleStringProperty(this,"area");
        lawInfo = new SimpleStringProperty(this,"lawInfo");
        address = new SimpleStringProperty(this,"address");
        price = new SimpleStringProperty(this,"price");
    }

    public void displayInfo(){
        System.out.println("--------------");
        System.out.println("ID " + id);
        System.out.println("area " + area);
        System.out.println("lawInfo " + lawInfo);
        System.out.println("Address " + address);
        System.out.println("Price  " + price);

    }
    // Getter & Setter

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getArea() {
        return area.get();
    }

    public StringProperty areaProperty() {
        return area;
    }

    public void setArea(String area) {
        this.area.set(area);
    }

    public String getLawInfo() {
        return lawInfo.get();
    }

    public StringProperty lawInfoProperty() {
        return lawInfo;
    }

    public void setLawInfo(String lawInfo) {
        this.lawInfo.set(lawInfo);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }
}
