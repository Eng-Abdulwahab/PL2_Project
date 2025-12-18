package com.example.pl2_project;

import ClassesOfTheProject.FileManager;
import ClassesOfTheProject.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AdminPageController implements Initializable {

    @FXML
    public TextField add_ID_TF;
    @FXML
    public TextField add_Username_TF;
    @FXML
    public PasswordField add_Password_TF;
    @FXML
    public ComboBox<String> add_Role_CB;
    @FXML
    public Button addUserButton;
    @FXML
    public TextField update_ID_TF;
    @FXML
    public TextField update_Username_TF;
    @FXML
    public TextField update_Password_TF;
    @FXML
    public ComboBox<String> update_Role_CB;
    @FXML
    public Button updateUserButton;
    @FXML
    public TextField delete_ID_TF;
    @FXML
    public Button deleteUserButton;
    @FXML
    public TableView<User> usersTable;
    @FXML
    public TableColumn<User, String> columnID;
    @FXML
    public TableColumn<User, String> columnUsername;
    @FXML
    public TableColumn<User, String> columnPassword;
    @FXML
    public TableColumn<User, String> columnRole;
    @FXML
    public TableView projectsTable;
    @FXML
    public TableColumn projectName;
    @FXML
    public TableColumn ProjectID;

    File file = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/test.txt");
    FileManager FM = new FileManager();

    private ObservableList<User> userList = FXCollections.observableArrayList();

    public void viewUsers() {
        userList.clear();
        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                ArrayList<String> list = FM.Splitter(line);
                User user = new User(list.get(0), list.get(1), list.get(2), list.get(3));
                userList.add(user);
            }
        } catch(IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("File Error");
            alert.show();
        }

        usersTable.setItems(userList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        add_Role_CB.getItems().addAll("Admin", "Employee", "TeamLeader", "ProjectManager");
        update_Role_CB.getItems().addAll("Admin", "Employee", "TeamLeader", "ProjectManager");
        columnID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        columnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        columnRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        usersTable.setItems(userList);
        viewUsers();
    }

    public void clickOnAddButton(ActionEvent actionEvent) {
        if (add_ID_TF.getText().isEmpty() ||
                add_Username_TF.getText().isEmpty() ||
                add_Password_TF.getText().isEmpty() ||
                add_Role_CB.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill all fields");
            alert.show();
            return;
        }

        try {
            if(FM.checkUser(add_Username_TF.getText(), add_Password_TF.getText(), add_Role_CB.getValue(), file) || FM.isExist(add_ID_TF.getText(), file)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("The user already exists");
                alert.show();
                return;
            }

            String line = add_ID_TF.getText() + FM.getDelimiter() +
                    add_Username_TF.getText() + FM.getDelimiter() +
                    add_Password_TF.getText() + FM.getDelimiter() +
                    add_Role_CB.getValue();

            FM.addLine(line, file);

            userList.add(new User(add_ID_TF.getText(), add_Username_TF.getText(), add_Password_TF.getText(), add_Role_CB.getValue()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User added successfully");
            alert.show();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("File Error");
            alert.show();
        }
    }

    public void clickOnUpdateButton(ActionEvent actionEvent) {
        if (update_ID_TF.getText().isEmpty() ||
                update_Username_TF.getText().isEmpty() ||
                update_Password_TF.getText().isEmpty() ||
                update_Role_CB.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill all fields");
            alert.show();
            return;
        }

        try {
            if(!FM.isExist(update_ID_TF.getText(), file)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("This user not exists");
                alert.show();
                return;
            }

            String lineToDelete = FM.findLine(update_ID_TF.getText(), file);
            if(lineToDelete.equals("not found")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("This user does not exist");
                alert.show();
                return;
            }

            FM.deleteLine(lineToDelete, file);
            String line = update_ID_TF.getText() + FM.getDelimiter() +
                    update_Username_TF.getText() + FM.getDelimiter() +
                    update_Password_TF.getText() + FM.getDelimiter() +
                    update_Role_CB.getValue();
            FM.addLine(line, file);

            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getID().equals(update_ID_TF.getText())) {
                    userList.set(i, new User(update_ID_TF.getText(), update_Username_TF.getText(), update_Password_TF.getText(), update_Role_CB.getValue()));
                    break;
                }
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User updated successfully");
            alert.show();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("File Error");
            alert.show();
        }
    }

    public void clickOnDeleteButton(ActionEvent actionEvent) {
        if(delete_ID_TF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the ID field");
            alert.show();
            return;
        }

        try {
            if(!FM.isExist(delete_ID_TF.getText(), file)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("This user not exist");
                alert.show();
                return;
            }

            String lineToDelete = FM.findLine(delete_ID_TF.getText(), file);
            if(lineToDelete.equals("not found")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("This user does not exist");
                alert.show();
                return;
            }

            FM.deleteLine(lineToDelete, file);

            userList.removeIf(u -> u.getID().equals(delete_ID_TF.getText()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User deleted successfully");
            alert.show();

        } catch(IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("File Error");
            alert.show();
        }
    }
}
