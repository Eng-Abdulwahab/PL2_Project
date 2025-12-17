package ClassesOfTheProject;

import javafx.beans.property.SimpleStringProperty;

public class Vacation {
    private SimpleStringProperty startDate;
    private SimpleStringProperty endDate;
    private SimpleStringProperty reason;

    public Vacation(String StartDate, String EndDate, String Reason)
    {
        this.startDate = new SimpleStringProperty(StartDate);
        this.endDate = new SimpleStringProperty(EndDate);
        this.reason = new SimpleStringProperty(Reason);
    }

    public String getStartDate() {
        return startDate.get();
    }

    public SimpleStringProperty startDateProperty() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }

    public String getEndDate() {
        return endDate.get();
    }

    public SimpleStringProperty endDateProperty() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate.set(endDate);
    }

    public String getReason() {
        return reason.get();
    }

    public SimpleStringProperty reasonProperty() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason.set(reason);
    }
}
