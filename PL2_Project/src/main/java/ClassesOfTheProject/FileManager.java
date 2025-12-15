package ClassesOfTheProject;

import javafx.scene.control.Alert;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileManager {
    private final String delimiter = "|//|";

    public String getDelimiter() {
        return delimiter;
    }


    public String findLine(String id, File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        while(reader.hasNextLine()) {
            String currentLine = reader.nextLine();
            if(currentLine.startsWith(id + "|//|")) {
                reader.close();
                return currentLine;
            }
        }
        reader.close();
        return "not found";
    }

    // isExist
    public boolean isExist(String id,File file) throws FileNotFoundException{
        if(findLine(id,file).equals("not found")){
            return false;
        }
        return true;
    }

    public boolean isRoleExist(String role,File file) throws FileNotFoundException{
        if(findLine(role,file).equals("not found")){
            return false;
        }
        return true;
    }

    public boolean checkUser(String username, String password, String role, File file) {
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(Pattern.quote(delimiter));

                if (parts.length == 3) {
                    String usernameFile = parts[0].trim();
                    String passwordFile = parts[1].trim();
                    String roleFile = parts[2].trim();

                    if (usernameFile.equals(username) && passwordFile.equals(password) && roleFile.equals(role)) {

                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File Error");
            alert.setHeaderText(null);
            alert.setContentText("Error reading file: " + e.getMessage());
            alert.showAndWait();
        }
        return false;
    }


    public ArrayList<String> Splitter(String fullLine) {
        String[] parts = fullLine.split(Pattern.quote(delimiter));
        ArrayList<String> result = new ArrayList<>();

        for (String p : parts) {
            result.add(p);
        }

        return result;
    }
    public void addLine(String Line, File file)  throws IOException {
        PrintWriter input = new PrintWriter(new FileWriter(file, true));
        String line = String.join(delimiter, Line);
        input.println(line);
        input.close();
    }


    public void deleteLine(String line, File file) throws IOException
    {
        File tempFile = new File("PL2_Project/src/main/java/ClassesOfTheProject/Files/temp.txt");

        Scanner reader = new Scanner(file);
        PrintWriter writer = new PrintWriter(tempFile);

        while(reader.hasNextLine())
        {
            String currentLine = reader.nextLine();

            if(!currentLine.equals(line))
            {
                writer.println(currentLine);
            }
        }

        reader.close();
        writer.close();

        file.delete();
        tempFile.renameTo(file);
    }
}

