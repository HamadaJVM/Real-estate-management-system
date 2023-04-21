package com.example.brocodedemo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.*;
import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        // Group root = new Group();
        Scene scene = new Scene(root, 900, 600, Color.LIGHTSKYBLUE);
        Text text = new Text("hooooo!");
        text.setX(100);
        text.setY(100);
        text.setFont(Font.font("Areal",30));

        stage.setTitle("Stage Demo!");
        Image icon = new Image("C:\\Users\\Hmada_Z\\IdeaProjects\\BroCodeDemo\\src\\icon.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}