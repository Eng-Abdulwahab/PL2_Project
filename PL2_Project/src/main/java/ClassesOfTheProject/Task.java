package ClassesOfTheProject;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Task {
    private SimpleStringProperty taskName;
    private SimpleStringProperty taskID;
    private SimpleStringProperty deadline;
    private SimpleStringProperty project;
    private SimpleStringProperty completed;

    public Task(String taskID, String taskName, String project ,String dueDate, String completed) {
        this.taskName = new SimpleStringProperty(taskName);
        this.taskID = new SimpleStringProperty(taskID);
        this.project = new SimpleStringProperty(project);
        this.deadline = new SimpleStringProperty(dueDate);
        this.completed = new SimpleStringProperty(completed);
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


    public String getProject()
    {
        return project.get();
    }

    public String getCompletedString() {
        return String.valueOf(completed.get());
    }

    public SimpleStringProperty completedProperty() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed.set(completed);
    }
}
