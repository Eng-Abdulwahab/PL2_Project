package ClassesOfTheProject;

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
