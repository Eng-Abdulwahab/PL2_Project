package ClassesOfTheProject;

import java.io.File;
import java.io.IOException;// التعامل مع الاخطاء
import java.util.Scanner;
import java.util.regex.Pattern;

public class Employee extends User {

    // ============ Attributes ============
    private String entryTime;
    private String exitTime;

    private final FileManager fileManager = new FileManager();

    private final File vacationFile = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/vacations.txt");
    private final File penaltiesFile = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/penalties.txt");
    private final File tasksFile = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/tasks.txt");

    // ============ Constructors ============

    public Employee(String entryTime, String exitTime, String username, String password, String ID, String role) {
        super(username, password, ID, role);
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    public Employee() {
        super();
    }

    // ============ Getters ============

    public String getEntryTime() {
        return entryTime == null ? "N/A" : entryTime;
    }

    public String getExitTime() {
        return exitTime == null ? "N/A" : exitTime;
    }

    // ============ Setters ============

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    // ============ viewPenalties() ============
    public String viewPenalties() {
        StringBuilder result = new StringBuilder("Penalties for Employee ID: " + getID() + "\n");
        String delimiter = fileManager.getDelimiter();// هاتها من file manager

        try (Scanner reader = new Scanner(penaltiesFile)) {

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(Pattern.quote(delimiter));

                // Expected: EmployeeID |//| Reason |//| Date
                if (parts.length >= 2 && parts[0].equals(getID())) {
                    result.append("- ").append(parts[1]).append("\n");
                }
            }

        } catch (IOException e) {
            return "Error reading penalties file: " + e.getMessage();
        }

        return result.toString().trim().equals("Penalties for Employee ID: " + getID())
                ? "No penalties found for this employee."
                : result.toString();
    }

    // ============ viewTasks() ============
    public String viewTasks() {
        StringBuilder result = new StringBuilder("Tasks for Employee ID: " + getID() + "\n");
        String delimiter = fileManager.getDelimiter();

        try (Scanner reader = new Scanner(tasksFile)) {

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(Pattern.quote(delimiter));

                // Expected: EmployeeID |//| Task |//| Status
                if (parts.length >= 3 && parts[0].equals(getID())) {
                    result.append("- ").append(parts[1])
                            .append(" (Status: ").append(parts[2]).append(")\n");
                }
            }

        } catch (IOException e) {
            return "Error reading tasks file: " + e.getMessage();
        }

        return result.toString().trim().equals("Tasks for Employee ID: " + getID())
                ? "No tasks found for this employee."
                : result.toString();
    }

    // ============ requestVacation() ============
    public void requestVacation() {
        Scanner input = new Scanner(System.in);

        System.out.println("\n--- Vacation Request for Employee " + getID() + " ---");
        System.out.print("Start date (yyyy-MM-dd): ");
        String start = input.nextLine();

        System.out.print("End date (yyyy-MM-dd): ");
        String end = input.nextLine();

        System.out.print("Reason: ");
        String reason = input.nextLine();

        String line = getID() + fileManager.getDelimiter()
                + start + fileManager.getDelimiter()
                + end + fileManager.getDelimiter()
                + reason + fileManager.getDelimiter()
                + "Pending";

        try {
            fileManager.addLine(line, vacationFile);
            System.out.println("Vacation request submitted successfully.");
        } catch (IOException e) {
            System.out.println("Failed to submit vacation request: " + e.getMessage());
        }
    }

    // ============ toString() ============
    @Override
    public String toString() {
        return "Employee{" +
                "ID='" + getID() + '\'' +
                ", Username='" + getUsername() + '\'' +
                ", Role='" + getRole() + '\'' +
                ", EntryTime='" + entryTime + '\'' +
                ", ExitTime='" + exitTime + '\'' +
                '}';
    }
}