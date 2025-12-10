package ClassesOfTheProject;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileManager {
    private final String delimiter = "|//|";

    FileManager() {}

    public String getDelimiter() {
        return delimiter;
    }

    public User Splitter(String Line ,File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);

        while(reader.hasNextLine())
        {
            String CurrentLine = reader.nextLine();

            if(CurrentLine.equals(Line))
            {
                String[] Info = CurrentLine.split(Pattern.quote(delimiter));

                String userName = Info[0];
                String password = Info[1];
                String ID = Info[2];
                String role = Info[3];


                return new User(userName, password, ID, role);
            }
        }
        reader.close();

        return null;
    }

    public void addLine(String name, String password, String id, String role, File file)  throws IOException {
        PrintWriter input = new PrintWriter(new FileWriter(file, true));
        String line = String.join(delimiter, name, password, id, role);
        input.println(line);
        input.close();
    }

    public void deleteLine(String line, File file) throws IOException
    {
        File tempFile = new File("D:\\tempFile.txt");

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
