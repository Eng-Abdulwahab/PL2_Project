package ClassesOfTheProject;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Task {
    private SimpleStringProperty taskName;
    private SimpleStringProperty taskID;
    private SimpleStringProperty deadline;
    private SimpleStringProperty project;
    private SimpleBooleanProperty completed;

    public Task(String taskID, String taskName, String project ,String dueDate, boolean completed) {
        this.taskName = new SimpleStringProperty(taskName);
        this.taskID = new SimpleStringProperty(taskID);
        this.project = new SimpleStringProperty(project);
        this.deadline = new SimpleStringProperty(dueDate);
        this.completed = new SimpleBooleanProperty(completed);
    }

    public String getTaskName() {
        return taskName.get();
    }


    public String getTaskID() {
        return taskID.get();
    }

    public String getDeadline() {
        return deadline.get();
    }

    public boolean isCompleted() {
        return completed.get();
    }

    public String getProject()
    {
        return project.get();
    }

    public SimpleBooleanProperty completedProperty() {
        return completed;
    }
}
