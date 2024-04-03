package com.example.tproject;

import controllers.PackageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Cart;
import model.User;

/******************************************************************************
 *  Lýsing  :
 *
 *
 *
 *
 *****************************************************************************/
public class PaymentView {
    @FXML
    private Button fxPay;
    @FXML
    private Button fxGoBack;
    @FXML
    private Button fxEmptyCart;
    @FXML
    private Button fxRemove;
    @FXML
    private ListView fxCart;
    @FXML
    private Label fxTotalPrice;

    private PackageController packageController = DateSelectorView.getPackageController();
    private Cart cart = packageController.getCart();
    private User user = packageController.getUser();


    @FXML
    public void fxGoBackHandler(ActionEvent ActionEvent) {
        ViewSwitcher.switchTo(View.BOOKINGSELECTOR); // hvert viljum við skipta hér?
    }

    @FXML
    public void fxEmptyCartHandler(ActionEvent ActionEvent) {
        cart.emptyCart();
    }

    @FXML
    public void fxPayHandler(ActionEvent ActionEvent) {
        if (user == null) {
            ViewSwitcher.switchTo(View.LOGIN);
        }
        // ViewSwitcher.switchTo(View.BOOKINGSELECTOR);
    }

    @FXML
    public void fxRemoveHandler(ActionEvent ActionEvent) { // þarf þetta nokkuð?
    }

    public void initialize() {
        fxTotalPrice.setText(String.valueOf(cart.getTotalAmount()));
    }

    private void newUser() {
        user = new User("", "", null, null);
        UserDialog userDialog = new UserDialog(user);
        // Optional<User> o = userDialog.showAndWait();
    }
}
