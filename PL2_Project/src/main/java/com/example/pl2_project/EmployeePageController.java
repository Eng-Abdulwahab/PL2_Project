package com.example.pl2_project;

import ClassesOfTheProject.Employee;
import ClassesOfTheProject.FileManager;
import ClassesOfTheProject.Task;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;
import java.util.Scanner;

public class EmployeePageController {
    @FXML
    public TableView<Task> tasksTable;

    @FXML
    public TableColumn<Task, String> colTaskName;

    @FXML
    public TableColumn<Task, String> colProject;

    @FXML
    public TableColumn<Task, String> colDueDate;

    @FXML
    public TableColumn<Task , Boolean> colTaskCompleted;
    public TableColumn<Task, String> colTaskId;

    @FXML
    private Button btnClockIn;

    @FXML
    private Button btnClockOut;

    @FXML
    private Label lblCurrentStatus;

    FileManager fm = new FileManager();

    public void viewTask() throws FileNotFoundException {
        File file = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/tasksFile.txt");
        Scanner scn = new Scanner(file);
        ObservableList<Task> rows = FXCollections.observableArrayList();
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            ArrayList<String> info = fm.Splitter(line);
            Task task = new Task(
                    info.get(0),
                    info.get(1),
                    info.get(2),
                    info.get(3),
                    Boolean.parseBoolean(info.get(4))
            );

            rows.add(task);
        }

        tasksTable.setItems(rows);


    }

    public void initialize() throws FileNotFoundException {
        tasksTable.setEditable(true);

        colTaskId.setCellValueFactory(new PropertyValueFactory<>("taskID"));
        colTaskName.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        colProject.setCellValueFactory(new PropertyValueFactory<>("project"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("deadline"));

        colTaskCompleted.setCellValueFactory(cellData -> cellData.getValue().completedProperty().asObject());
        colTaskCompleted.setCellFactory(CheckBoxTableCell.forTableColumn(colTaskCompleted));

        viewTask();
    }



    public void handleClockAction(javafx.event.ActionEvent event) {
        if(event.getSource() == btnClockIn)
        {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            Employee employee = new Employee();
            String TableTime = dateTime.format(formatter);
            lblCurrentStatus.setText("Status: Logged In.");
            employee.setEntryTime(TableTime);

        }
        else if (event.getSource() == btnClockOut)
        {
            lblCurrentStatus.setText("Status: Logged Out.");
        }
    }
}
