package com.example.tproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

public class UserAreaDialog extends Stage {

    public UserAreaDialog(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tproject/UserArea-view.fxml"));
            Parent root = loader.load();
            UserAreaController controller = loader.getController();
            controller.initData(user);

            Scene scene = new Scene(root, 400,400);
            this.setScene(scene);
            this.setTitle("User Area");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load the User Area window.", e);
        }
    }
}
