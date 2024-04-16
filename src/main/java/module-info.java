module com.example.tproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.tproject to javafx.fxml;
    exports com.example.tproject;
    exports model;
    exports flight;
    exports controllers;
    exports daytrip.controller;
    exports daytrip.dal;
    exports daytrip.model;
    exports daytrip.repository;
    opens controllers to javafx.fxml;
    opens flight to javafx.fxml;
    exports Mock_objects;
    opens Mock_objects to javafx.fxml;
}
