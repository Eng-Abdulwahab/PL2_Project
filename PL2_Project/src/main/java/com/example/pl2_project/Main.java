package com.example.pl2_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EmployeePage.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
