package com.example.tproject;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import model.User;

/******************************************************************************
 *  Nafn    : Lilja Kolbrún Schopka
 *  T-póstur: lks17@hi.is
 *
 *  Lýsing  :
 *
 *
 *
 *
 *****************************************************************************/
public class UserDialog extends Dialog<User> {

    @FXML
    private TextField fxName;
    @FXML
    private TextField fxEmail;
    @FXML
    private ButtonType fxILagi;
    @FXML
    private ButtonType fxHaettaVid;
    
    private User user;

}
