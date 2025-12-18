package com.example.pl2_project;

import ClassesOfTheProject.FileManager;
import ClassesOfTheProject.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProjectManagerPageController {

    private final File userFile = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/test.txt");
    private final File reportFile = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/report.txt");
    private final File taskFile = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/tasksFile.txt");
    FileManager fm = new FileManager();
    ObservableList<User> userRec = FXCollections.observableArrayList();

    @FXML
    public TextArea txtReport;
    @FXML
    public ComboBox<String> comboTL;
    @FXML
    public ComboBox<String> comboEmp;
    @FXML
    public Button btnSendReport;
    @FXML
    public ComboBox<String> comboProjectName;
    @FXML
    public ProgressIndicator projectProgress;

    public void updateProgress()
    {
        String selectedProject = comboProjectName.getValue();
        if(selectedProject == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Project Name can't be empty!");
            alert.show();
            return;
        }
        int totalTasks = 0 ;
        int completedTasks = 0;

        try(Scanner scn = new Scanner(taskFile))
        {
            while(scn.hasNextLine())
            {
                String line = scn.nextLine();
                ArrayList<String> info = fm.Splitter(line);
                String projectName = info.get(2);
                String Completed = info.get(4);

                if(projectName.equals(selectedProject))
                {
                    totalTasks++;
                }
                if(Completed.equals("true"))
                {
                    completedTasks++;
                }
            }
        }
        catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("File not found!");
            alert.show();
        }

        if(totalTasks == 0)
        {
            projectProgress.setProgress(0);
        }else
        {
            double Progress = (double) completedTasks / totalTasks;
            projectProgress.setProgress(Progress);
        }


    }

    public void initialize()
    {
        comboProjectName.setOnAction(e -> updateProgress());
        try(Scanner scn = new Scanner(userFile))
        {
            while(scn.hasNextLine())
            {
                String line = scn.nextLine();
                ArrayList<String> info = fm.Splitter(line);
                User user = new User(info.get(0),info.get(1),info.get(2),info.get(3));
                if(info.get(3).equals("TeamLeader"))
                {
                    comboTL.getItems().add(info.get(1));
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try(Scanner scn = new Scanner(userFile))
        {
            while(scn.hasNextLine())
            {
                String line = scn.nextLine();
                ArrayList<String> info = fm.Splitter(line);
                User user = new User(info.get(0),info.get(1),info.get(2),info.get(3));
                if(info.get(3).equals("Employee"))
                {
                    comboEmp.getItems().add(info.get(1));
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try(Scanner scanner = new Scanner(taskFile))
        {
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                ArrayList<String> info = fm.Splitter(line);
                String toCombo = info.get(2);
                if (!comboProjectName.getItems().contains(toCombo)) {
                    comboProjectName.getItems().add(toCombo);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleReportAction(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnSendReport)
        {
            String reportTxt= txtReport.getText();
            String selectedTL= comboTL.getValue();
            String selectEmp = comboEmp.getValue();
            String line = reportTxt + fm.getDelimiter()+ selectedTL + fm.getDelimiter() + selectEmp;
            try
            {
                fm.addLine(line, reportFile);
            }catch(IOException e)
            {

            }
            if (reportTxt.equals(null) || reportTxt.isEmpty() || selectedTL == null || selectEmp == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Fields Cannot Be Empty!");
                alert.show();

            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Report sent successfully!");
            alert.show();

        }
    }
}
