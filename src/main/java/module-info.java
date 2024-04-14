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
    opens controllers to javafx.fxml;
}
