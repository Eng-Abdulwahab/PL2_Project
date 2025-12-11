package ClassesOfTheProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Task {
    private String taskName;
    private String taskID;
    private Employee assignedTo;
    private TeamLeader assignedBy;
    private boolean isCompleted;


    public Task(String taskName, String taskID, Employee assignedTo, TeamLeader assignedBy, boolean isCompleted)  {
        this.taskName = taskName;
        this.taskID = taskID;
        this.assignedTo = assignedTo;
        this.assignedBy = assignedBy;
        this.isCompleted = isCompleted;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
    }

    public TeamLeader getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(TeamLeader assignedBy) {
        this.assignedBy = assignedBy;
    }

    public void markAsCompleted(Task task) throws IOException {
        this.isCompleted = true;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
