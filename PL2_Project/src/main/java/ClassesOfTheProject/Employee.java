package ClassesOfTheProject;
import java.io.File;
import java.util.ArrayList;
import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class Employee extends User {

    private String entryTime;
    private String exitTime;

    //CONSTRUCTOR
    public Employee(String entryTime, String exitTime, String username, String password, String ID, String role) {
        super(username, password, ID, role);
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    public Employee()
    {}
    //GETTER & SETTER ENTRY TIME
    public String getEntryTime() {
        return this.entryTime;
    }

    public void setEntryTime( String entryTime) {
        this.entryTime = entryTime;
    }
    //GETTER & SETTER EXIST  TIME
    public String getExitTime() {
        return this.exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }
    private final FileManager FManager = new FileManager();
    private final File tasksFile = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/tasksFile.txt");
    private final File penaltiesFile = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/penaltiesFile.txt");
    public String viewTasks() throws FileNotFoundException {
        String targetLine = FManager.findLine(getID(), tasksFile);

        if (targetLine.equals("not found")) {
            return "No tasks found.";
        }

        ArrayList<String> parts = FManager.Splitter(targetLine);

        for (String p : parts) {
            System.out.println(p);
        }

        return targetLine;
    }

    public String viewpenalities() throws FileNotFoundException {
        String targetLine = FManager.findLine(getID(), penaltiesFile);

        if (targetLine.equals("not found")) {
            return "No penalties found.";
        }

        ArrayList<String> parts = FManager.Splitter(targetLine);

        for (String p : parts) {
            System.out.println(p);
        }

        return targetLine;
    }
    public String requestVacuation(){
       //i am in a bit of hurray can we talk another time وعلشان الناس المحنكه خالي من الai الكود كله
       return " "; //والله عاملها فاضيها علشان لو معملتش الreturn بيدي error
    }
}
