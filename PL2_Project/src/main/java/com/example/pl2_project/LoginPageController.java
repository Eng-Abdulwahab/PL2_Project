package com.example.pl2_project;


import ClassesOfTheProject.FileManager;
import ClassesOfTheProject.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LoginPageController implements Initializable {

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPass;

    @FXML
    private Button btnLogin;

    @FXML
    FileManager fm = new FileManager();

    @FXML
    File file = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/test.txt");




    public void handleUserRole(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleComboBox.getItems().addAll("Admin", "Employee", "Team Leader", "Project Manager");
    }

    public void handleButtonAction(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == btnLogin)
        {
            String selectedRole = roleComboBox.getValue();
            String FXMLfile = "";
            String username = txtUser.getText();
            String password = txtPass.getText();

            if (username.isEmpty() || password.isEmpty() )
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Username and password cannot be empty.");
                alert.showAndWait();
                return;
            }

            if(selectedRole == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Role Error");
                alert.setHeaderText(null);
                alert.setContentText("You must select a role.");
                alert.showAndWait();
                return;
            }

            if(!fm.checkUser(username, password, selectedRole, file))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Credentials!");
                alert.showAndWait();
                return;
            }

            switch (selectedRole)
            {
                case "Admin" :
                    FXMLfile = "AdminPage.fxml";
                    break;
                case "Employee":
                    FXMLfile = "EmployeePage.fxml";
                    break;
                case "ProjectManager":
                    FXMLfile = "GeneralManagerPage.fxml";
                    break;
                case "TeamLeader":
                    FXMLfile = "TeamLeaderPage.fxml";
                    break;
            }

            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(FXMLfile));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Login Page");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();



        }
    }
}
