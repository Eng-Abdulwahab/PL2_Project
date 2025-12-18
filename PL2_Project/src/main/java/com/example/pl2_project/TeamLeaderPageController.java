package com.example.pl2_project;

import ClassesOfTheProject.FileManager;
import ClassesOfTheProject.Report;
import ClassesOfTheProject.Task;
import ClassesOfTheProject.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class TeamLeaderPageController {
    private final FileManager FManager = new FileManager();
    private final File tasksFile = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/tasksFile.txt");
    private final File penaltiesFile = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/penaltiesFile.txt");
    private final File vacationFile = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/vacations.txt");
    private final File projectFile = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/project.txt");
    private final File reportFile = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/report.txt");

    @FXML
    public TableView<Vacation> vacationTable;
    @FXML
    public TableColumn<Vacation, String> vacationStart;
    @FXML
    public TableColumn<Vacation, String> vacationEnd;
    @FXML
    public TableColumn<Vacation, String> vacationReason;
    @FXML
    public ComboBox<String> comboVacation;
    @FXML
    public Button btnAcceptVacation;
    @FXML
    public TableView<Task> taskTable;
    @FXML
    public TableColumn<Task, String> colTaskName;
    @FXML
    public TableColumn<Task, String> colTaskId;
    @FXML
    public ComboBox<String> comboProject;
    @FXML
    public TableView<Report> ReportTable;
    @FXML
    public TableColumn<Report, String> colEmployee;
    @FXML
    public TableColumn<Report, String> colReport;
    @FXML
    private TextField penalatiestext;
    @FXML
    private TextField reasontext,Tasktext;
    @FXML
    private DatePicker DATAPicerdeadline;

    Random rand = new Random();

    @FXML
    ObservableList<Vacation> rows = FXCollections.observableArrayList();

    @FXML
    ObservableList<Task> taskRows= FXCollections.observableArrayList();

    @FXML
    ObservableList<Report> reportRow = FXCollections.observableArrayList();


    public void sendpenalaties(){
        String PENNALATIES=penalatiestext.getText();
        String REASON=reasontext.getText();
        if(penalatiestext.getText().isEmpty()||reasontext.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Data must be entered first");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setHeaderText(null);
            alert.setContentText(" PENALATIES Were successfully sent ");
            alert.showAndWait();
            String line=PENNALATIES+FManager.getDelimiter()+REASON;
            try{
                FManager.addLine(line,penaltiesFile);
            }catch (IOException e){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("File not found");
                alert.showAndWait();
            }
        }
    }
    public void sendtask(){
        String Ttext=Tasktext.getText();
        String Dtask = DATAPicerdeadline.getValue().toString();
        if(Tasktext.getText().isEmpty()||DATAPicerdeadline.getValue().toString().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("  Data must be entered first");
            alert.showAndWait();


        }else{
            int Id = rand.nextInt(200);
            String IdNum = Integer.toString(Id);
            Boolean completed = false;
            String completedSt= Boolean.toString(completed);
            String project = comboProject.getValue();

            if(project == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("You must enter the project name");
                alert.showAndWait();
                return;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setHeaderText(null);
            alert.setContentText(" Tasks Were successfully sent ");
            alert.showAndWait();
            String line= IdNum+FManager.getDelimiter()+Ttext +FManager.getDelimiter()+project+FManager.getDelimiter()+Dtask + FManager.getDelimiter()+ completedSt;
            try{
                FManager.addLine(line,tasksFile);
            }catch (IOException e){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("File not found");
                alert.showAndWait();
            }
        }

    }

    public void viewVacation() throws FileNotFoundException {
        rows.clear();

        try (Scanner scn = new Scanner(vacationFile)) {
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                ArrayList<String> info = FManager.Splitter(line);

                Vacation vacation = new Vacation(
                        info.get(0),
                        info.get(1),
                        info.get(2)
                );
                rows.add(vacation);
            }
        }

        vacationTable.setItems(rows);
    }

    public void viewTask() throws FileNotFoundException {
        taskRows.clear();

        try (Scanner scn = new Scanner(tasksFile)) {
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                ArrayList<String> info = FManager.Splitter(line);

                Task task = new Task(
                        info.get(0),
                        info.get(1),
                        info.get(2),
                        info.get(3),
                        info.get(4)
                );
                String completed= info.get(4);
                if(completed.equals("true"))
                {
                taskRows.add(task);
                }
            }
        }
        taskTable.setItems(taskRows);
    }

    public void viewReport()
    {
        reportRow.clear();
        try(Scanner scn = new Scanner(reportFile))
        {
           while(scn.hasNextLine())
           {
               String line = scn.nextLine();
               ArrayList<String> info = FManager.Splitter(line);
               Report report = new Report(info.get(0), info.get(1), info.get(2));
               reportRow.add(report);
           }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        ReportTable.setItems(reportRow);
    }

    public void initialize() throws FileNotFoundException {
        vacationStart.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        vacationEnd.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        vacationReason.setCellValueFactory(new PropertyValueFactory<>("reason"));

        colTaskName.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        colTaskId.setCellValueFactory(new PropertyValueFactory<>("taskID"));

        colEmployee.setCellValueFactory(new PropertyValueFactory<>("employee"));
        colReport.setCellValueFactory(new PropertyValueFactory<>("report"));


        try(Scanner scn = new Scanner(vacationFile))
        {
            while(scn.hasNextLine())
            {
                String line = scn.nextLine();
                ArrayList<String> info = FManager.Splitter(line);
                String toCombo = info.get(0);
                comboVacation.getItems().add(toCombo);
            }
        }

        try(Scanner scanner = new Scanner(projectFile))
        {
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                ArrayList<String> info = FManager.Splitter(line);
                String toCombo = info.get(0);
                comboProject.getItems().add(toCombo);
            }
        }

        viewVacation();
        viewTask();
        vacationTable.setItems(rows);
        viewReport();
    }

    public void handleVacationAcception(ActionEvent actionEvent) throws FileNotFoundException {
        if(actionEvent.getSource() == btnAcceptVacation)
        {
            String VacationDate = comboVacation.getValue();

            if(VacationDate == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("You must select a Vacation to complete");
                alert.showAndWait();
                return;
            }

            File tempFile = new File(vacationFile.getParent(), "temp.txt");

            try (
                    Scanner reader = new Scanner(vacationFile);
                    PrintWriter writer = new PrintWriter(tempFile)
            ) {
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();

                    ArrayList<String> info = FManager.Splitter(line);
                    String startDate = info.get(0);

                    if (!startDate.equals(VacationDate)) {
                        writer.println(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }



            if (!vacationFile.delete()) {
                System.out.println("Failed to delete original file");
                return;
            }

            if (!tempFile.renameTo(vacationFile)) {
                System.out.println("Failed to rename temp file");
                return;
            }

            comboVacation.getItems().clear();
            viewVacation();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Vacation accepted and removed");
            alert.showAndWait();

        }
    }
}