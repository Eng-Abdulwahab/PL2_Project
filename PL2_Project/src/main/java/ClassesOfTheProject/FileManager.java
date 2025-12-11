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

    public boolean findLine(ArrayList<String> Line, File file) throws FileNotFoundException
    {
        Scanner reader = new Scanner(file);

        String targetLine = String.join(delimiter, Line);

        while(reader.hasNextLine())
        {
            String currentLine = reader.nextLine();

            if(currentLine.equals(targetLine))
            {
                return true;
            }
        }

        return false;
    }

    public ArrayList<String> Splitter(String Line , File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);

        String targetLine = String.join(delimiter, Line);
        while(reader.hasNextLine())
        {
            String CurrentLine = reader.nextLine();

            if(CurrentLine.equals(targetLine))
            {
                String[] parts = CurrentLine.split(Pattern.quote(delimiter));
                ArrayList<String> info = new ArrayList<>();

                for(String part: parts)
                {
                    info.add(part);
                }


                return info;
            }
        }
        reader.close();

        return null;
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
