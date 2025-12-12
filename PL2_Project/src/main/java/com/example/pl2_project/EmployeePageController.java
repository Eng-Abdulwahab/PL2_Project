package com.example.pl2_project;

import ClassesOfTheProject.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EmployeePageController {
    @FXML
    private Button btnClockIn;

    @FXML
    private Button btnClockOut;

    @FXML
    private Label lblCurrentStatus;

    @FXML
    private TableView<Employee> attendanceTable;

    @FXML
    private TableColumn<Employee, String> colInTime;

    @FXML
    private TableColumn<Employee, String> colOutTime;

//    @FXML
//    private  TableColumn<Employee, String> colDailyHours;


    public ObservableList<Employee> list = FXCollections.observableArrayList(
            new Employee("2025-12-12 10:23", "null", "null" , "null", "null", "null")
    );


    public void initialize()
    {
        colInTime.setCellValueFactory(new PropertyValueFactory<Employee, String>("entryTime"));
        colOutTime.setCellValueFactory(new PropertyValueFactory<Employee, String>("exitTime"));
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

            attendanceTable.getItems().add(employee);



        }
        else if (event.getSource() == btnClockOut)
        {
            lblCurrentStatus.setText("Status: Logged Out.");
        }
    }
}
