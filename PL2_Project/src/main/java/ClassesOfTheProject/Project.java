package ClassesOfTheProject;

public class Project {
    private String projectName;
    private String projectID;
    private boolean isCompleted;
    private double completionPercentage;

    Project(String name, String ID, double completionPercentage, boolean isCompleted) {
        this.projectID = ID;
        this.projectName = name;
        this.completionPercentage = completionPercentage;
        this.isCompleted = isCompleted;
    }



    public void setCompletionPercentage(double completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    public double getCompletionPercentage() {
        return completionPercentage;
    }



    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }


    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
