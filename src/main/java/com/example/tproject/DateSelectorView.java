package com.example.tproject;

import controllers.PackageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DateSelectorView {
    @FXML
    private Label welcomeText;
    @FXML
    private DatePicker fxArrival;
    @FXML
    private DatePicker fxDeparture;
    @FXML
    private MenuButton fxPeople;
    @FXML
    private MenuButton fxDestination;
    @FXML
    private MenuButton fxOrigin;
    @FXML
    private Button fxConfirm;

    private static PackageController packageController;

    public static PackageController getPackageController() {
        return packageController;
    }

    /*@FXML
    public void fxConfirmHandler(ActionEvent ActionEvent) {

        String origin = getMenuButtonValue(fxOrigin);
        String destination = getMenuButtonValue(fxDestination);
        int persons = Integer.parseInt(getMenuButtonValue(fxPeople));

        packageController = new PackageController(null, origin, destination, fxArrival.getValue(), fxDeparture.getValue(), persons);
        ViewSwitcher.switchTo(View.BOOKINGSELECTOR);
    } */

    @FXML
    public void fxConfirmHandler(ActionEvent event) {
        try {
            String origin = getMenuButtonValue(fxOrigin);
            String destination = getMenuButtonValue(fxDestination);
            int persons = Integer.parseInt(getMenuButtonValue(fxPeople));

            if (origin == null || destination == null || fxArrival.getValue() == null || fxDeparture.getValue() == null) {
                // Show an error message or log it
                System.out.println("Please complete all fields.");
                return;
            }

            packageController = new PackageController(null, origin, destination, fxArrival.getValue(), fxDeparture.getValue(), persons);
            ViewSwitcher.switchTo(View.BOOKINGSELECTOR);
        } catch (NumberFormatException e) {
            // Handle error in case of number format issue
            System.out.println("Error in number of persons: " + e.getMessage());
        } catch (Exception e) {
            // General error handling
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private String getMenuButtonValue(MenuButton menuButton) {
        for (MenuItem item : menuButton.getItems()) {
            if (item instanceof RadioMenuItem && ((RadioMenuItem) item).isSelected()) {
                return item.getText();
            } else if (item instanceof CheckMenuItem && ((CheckMenuItem) item).isSelected()) {
                return item.getText();
            }
        }
        return null;
    }


}
