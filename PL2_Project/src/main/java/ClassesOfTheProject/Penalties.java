package ClassesOfTheProject;

import javafx.beans.property.SimpleStringProperty;

public class Penalties {
    private SimpleStringProperty date;
    private SimpleStringProperty reason;

    public Penalties(String Date, String Reason)
    {
        this.date = new SimpleStringProperty(Date);
        this.reason = new SimpleStringProperty(Reason);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
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
