package ClassesOfTheProject;

import javafx.beans.property.SimpleStringProperty;

public class Report {
    private SimpleStringProperty report;
    private SimpleStringProperty teamLeader;
    private SimpleStringProperty employee;

    public Report(String report, String teamLeader, String employee)
    {
        this.report = new SimpleStringProperty(report);
        this.teamLeader= new SimpleStringProperty(teamLeader);
        this.employee = new SimpleStringProperty(employee);
    }

    public String getReport() {
        return report.get();
    }

    public SimpleStringProperty reportProperty() {
        return report;
    }

    public void setReport(String report) {
        this.report.set(report);
    }

    public String getTeamLeader() {
        return teamLeader.get();
    }

    public SimpleStringProperty teamLeaderProperty() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader.set(teamLeader);
    }

    public String getEmployee() {
        return employee.get();
    }

    public SimpleStringProperty employeeProperty() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee.set(employee);
    }
}
