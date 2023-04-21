package com.example.brocodedemo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Scanner;

public class House extends Info{
    private final StringProperty floorNumber ;
    private final StringProperty roomNumber ;
    private final StringProperty yearOfCreation ;

    House(){
        floorNumber = new SimpleStringProperty(this,"floorNumber");
        roomNumber = new SimpleStringProperty(this,"roomNumber");
        yearOfCreation = new SimpleStringProperty(this,"yearOfCreation");
    }
/* شغل قديم
    public void buyHouse(){
        System.out.println("Enter House Info");
        setInfo();
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter The floor number");
        floorNumber = cin.nextInt();
        System.out.println("Enter The room number");
        roomNumber = cin.nextInt();
        System.out.println("Enter The year Of Creation");
        yearOfCreation = cin.next();
        System.out.println("--------------");

    }*/
    public void display(){
        displayInfo();
        System.out.println("floor Number " + floorNumber);
        System.out.println("room Number " + roomNumber);
        System.out.println("yearOfCreation " + yearOfCreation);
        System.out.println("--------------");

    }
    // Getter & Setter

    public String getFloorNumber() {
        return floorNumber.get();
    }

    public StringProperty floorNumberProperty() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber.set(floorNumber);
    }

    public String getRoomNumber() {
        return roomNumber.get();
    }

    public StringProperty roomNumberProperty() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber.set(roomNumber);
    }

    public String getYearOfCreation() {
        return yearOfCreation.get();
    }

    public StringProperty yearOfCreationProperty() {
        return yearOfCreation;
    }

    public void setYearOfCreation(String yearOfCreation) {
        this.yearOfCreation.set(yearOfCreation);
    }
}
