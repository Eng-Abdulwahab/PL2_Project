package com.example.pl2_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.io.File;
import java.util.Scanner;

public class ProjectManagerPageController {

    private final File userFile = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/test.txt");
    private final File reportFile = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/report.txt");

    @FXML
    public TextArea txtReport;
    @FXML
    public ComboBox comboTL;
    @FXML
    public ComboBox comboEmp;
    @FXML
    public Button btnSendReport;
    

    public void handleReportAction(ActionEvent actionEvent) {

    }
}
