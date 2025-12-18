package ClassesOfTheProject;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Project {
    private SimpleStringProperty projectName;
    private SimpleStringProperty projectID;
    private SimpleBooleanProperty completed;
    private SimpleDoubleProperty completionPercentage;

    public Project(String name, String ID, double completionPercentage, boolean isCompleted) {
        this.projectID = new SimpleStringProperty(ID);
        this.projectName = new SimpleStringProperty(name);
        this.completionPercentage = new SimpleDoubleProperty(completionPercentage);
        this.completed = new SimpleBooleanProperty(isCompleted);
    }


    public String getProjectName() {
        return projectName.get();
    }

    public SimpleStringProperty projectNameProperty() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName.set(projectName);
    }

    public String getProjectID() {
        return projectID.get();
    }

    public SimpleStringProperty projectIDProperty() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID.set(projectID);
    }

    public boolean isCompleted() {
        return completed.get();
    }

    public SimpleBooleanProperty completedProperty() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed.set(completed);
    }

    public double getCompletionPercentage() {
        return completionPercentage.get();
    }

    public SimpleDoubleProperty completionPercentageProperty() {
        return completionPercentage;
    }

    public void setCompletionPercentage(double completionPercentage) {
        this.completionPercentage.set(completionPercentage);
    }
}
