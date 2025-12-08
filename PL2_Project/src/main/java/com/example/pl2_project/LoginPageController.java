package com.example.pl2_project;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    public ComboBox<String> roleComboBox;



    public void handleUserRole(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleComboBox.getItems().addAll("Admin", "Employee", "Team Leader", "General Manager");
    }

}
