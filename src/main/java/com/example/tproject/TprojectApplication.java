package com.example.tproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TprojectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TprojectApplication.class.getResource("DateSelector-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("T-project");
        ViewSwitcher.setScene(scene);
        ViewSwitcher.pushToHistory(View.DATESELECTOR);   //bætt við
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
