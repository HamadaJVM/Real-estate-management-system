module com.example.brocodedemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.java;
    requires java.sql;

    opens com.example.brocodedemo to javafx.fxml;
    exports com.example.brocodedemo;
}