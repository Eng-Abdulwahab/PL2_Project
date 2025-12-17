package ClassesOfTheProject;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.File;
import java.util.ArrayList;
import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class Employee extends User {

    private SimpleStringProperty entryTime;
    private SimpleStringProperty exitTime;
    private SimpleStringProperty hoursWorked;

    //CONSTRUCTOR
    public Employee(String entryTime, String exitTime, String hoursWorked) {
        this.entryTime = new SimpleStringProperty(entryTime);
        this.exitTime = new SimpleStringProperty(exitTime);
        this.hoursWorked = new SimpleStringProperty(hoursWorked);
    }


    public String getEntryTime() {
        return entryTime.get();
    }

    public SimpleStringProperty entryTimeProperty() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime.set(entryTime);
    }

    public String getExitTime() {
        return exitTime.get();
    }

    public SimpleStringProperty exitTimeProperty() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime.set(exitTime);
    }

    public String getHoursWorked() {
        return hoursWorked.get();
    }

    public SimpleStringProperty hoursWorkedProperty() {
        return hoursWorked;
    }

    public void setHoursWorked(String hoursWorked) {
        this.hoursWorked.set(hoursWorked);
    }
}
