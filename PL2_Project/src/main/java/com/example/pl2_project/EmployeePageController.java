package com.example.pl2_project;

import ClassesOfTheProject.Employee;
import ClassesOfTheProject.FileManager;
import ClassesOfTheProject.Penalties;
import ClassesOfTheProject.Task;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;
import java.util.Locale;
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
    public TableColumn<Task, String> colTaskId;

    @FXML
    public TableColumn<Task, String> colCompleted;

    @FXML
    public ComboBox<String> availTasks;

    @FXML
    public Button btnFinishTask;

    @FXML
    public DatePicker dpStartDate;

    @FXML
    public DatePicker dpEndDate;

    @FXML
    public TextArea txtVacationReason;

    @FXML
    public Button btnSubmit;

    @FXML
    public TableView<Penalties> penaltiesTable;

    @FXML
    public TableColumn<Penalties, String> colPenaltyDate;

    @FXML
    public TableColumn<Penalties, String> colPenaltyReason;

    @FXML
    public TableView<Employee> attendanceTable;

    @FXML
    public TableColumn<Employee, String> colInTime;

    @FXML
    public TableColumn colOutTime;

    @FXML
    public TableColumn colDailyHours;

    @FXML
    private Button btnClockIn;

    @FXML
    private Button btnClockOut;

    @FXML
    private Label lblCurrentStatus;


    FileManager fm = new FileManager();

    @FXML
    ObservableList<Employee> rows = FXCollections.observableArrayList();

    private Employee lastClockIn;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void viewPenalties() throws FileNotFoundException {
        File file = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/penaltiesFile.txt");
        ObservableList<Penalties> rows = FXCollections.observableArrayList();

        try (Scanner scn = new Scanner(file))
        {
            while(scn.hasNextLine())
            {
                String line = scn.nextLine();
                ArrayList<String> info= fm.Splitter(line);
                Penalties penalties = new Penalties(info.get(0),info.get(1));

                rows.add(penalties);
            }
        }
        penaltiesTable.setItems(rows);
    }

    public void viewTask() throws FileNotFoundException {
        File file = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/tasksFile.txt");

        ObservableList<Task> rows = FXCollections.observableArrayList();

        try(Scanner scn = new Scanner(file))
        {
            while (scn.hasNextLine())
            {
                String line = scn.nextLine();
                ArrayList<String> info = fm.Splitter(line);
                Task task = new Task(
                        info.get(0),
                        info.get(1),
                        info.get(2),
                        info.get(3),
                        info.get(4)
                );

                rows.add(task);
            }
        }
        tasksTable.setItems(rows);
    }

    public void initialize() throws FileNotFoundException {

        colTaskId.setCellValueFactory(new PropertyValueFactory<>("taskID"));
        colTaskName.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        colProject.setCellValueFactory(new PropertyValueFactory<>("project"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        colCompleted.setCellValueFactory(new PropertyValueFactory<>("completedString"));

        colPenaltyDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colPenaltyReason.setCellValueFactory(new PropertyValueFactory<>("Reason"));

        colInTime.setCellValueFactory(new PropertyValueFactory<>("entryTime"));
        colOutTime.setCellValueFactory(new PropertyValueFactory<>("exitTime"));
        colDailyHours.setCellValueFactory(new PropertyValueFactory<>("hoursWorked"));

        File file = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/tasksFile.txt");

        try(Scanner scanner = new Scanner(file))
        {
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                ArrayList<String> info = fm.Splitter(line);
                String toComboBox = info.get(1);
                availTasks.getItems().add(toComboBox);


            }
        }
        viewTask();
        viewPenalties();
        attendanceTable.setItems(rows);
    }



    public void handleClockAction(javafx.event.ActionEvent event) {
        LocalDateTime now = LocalDateTime.now();
        String formattedNow = now.format(formatter);
        if(event.getSource() == btnClockIn)
        {
            lastClockIn = new Employee(formattedNow, "", "");
            rows.add(lastClockIn);
            lblCurrentStatus.setText("Status: Logged In");
        }
        else if (event.getSource() == btnClockOut)
        {
            if(lastClockIn != null)
            {
                lastClockIn.setExitTime(formattedNow);

                LocalDateTime entryTime= LocalDateTime.parse(lastClockIn.getEntryTime(), formatter);
                Duration duration = Duration.between(entryTime, now);
                long hours= duration.toHours();
                long minutes= duration.toMinutes();
                long seconds= duration.toSeconds();
                String worked= hours + "h " + minutes + "m " + seconds + "s";

                lastClockIn.setHoursWorked(worked);
                attendanceTable.refresh();
                lastClockIn= null;
                lblCurrentStatus.setText("Status: Logged out");
            }
        }
    }

    public void handleFinishTaskAction(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == btnFinishTask)
        {
            File taskFile = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/tasksFile.txt");
            File teamLeadTasks = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/teamLeadTasks.txt");
            String taskName = availTasks.getValue();

            if(taskName == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("You must select a task to complete");
                alert.showAndWait();
                return;
            }

            File tempFile = new File(taskFile.getParent(), "temp.txt");
            String updatedLine = null;

            try (Scanner reader = new Scanner(taskFile);
                 PrintWriter writer = new PrintWriter(tempFile)) {

                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    if (line.contains(taskName)) {
                        ArrayList<String> parts = fm.Splitter(line);
                        parts.set(4, "true");
                        line = String.join("|//|", parts);
                        updatedLine = line;
                    }
                    writer.println(line);
                }
            }

            if (!taskFile.delete()) {
                throw new IOException("Failed to delete original file");
            }
            if (!tempFile.renameTo(taskFile)) {
                throw new IOException("Failed to rename temp file");
            }

            if (updatedLine != null) {
                fm.addLine(updatedLine, teamLeadTasks);
            }

            viewTask();
        }
    }

    public void handleVacationAction(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == btnSubmit)
        {
            File file = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/vacations.txt");
            LocalDate Start = dpStartDate.getValue();
            LocalDate End = dpEndDate.getValue();
            String Reason = txtVacationReason.getText();

            if(Start == null || End == null || Reason.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Fields cannot be empty!");
                alert.showAndWait();
                return;
            }

            String stStart = Start.toString();
            String stEnd = End.toString();

            String vacationLine = stStart + fm.getDelimiter() + stEnd + fm.getDelimiter() + Reason;
            fm.addLine(vacationLine,file);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Vacation Requested Successfully");
            alert.showAndWait();
        }
    }
}
