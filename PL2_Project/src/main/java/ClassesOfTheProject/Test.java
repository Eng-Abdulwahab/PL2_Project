package ClassesOfTheProject;

import java.io.*;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws IOException {
        FileManager file = new FileManager();
        File file1 = new File("D:\\test.txt");
        Scanner reader = new Scanner(file1);
        User u = new User();

        file.addLine("Abdo", "1597000", "#174", "Admin", file1);

        while (reader.hasNextLine())
        {
            String line = reader.nextLine();

            if(line.contains("Abdo"))
            {
                u = file.Splitter(line, file1);
                System.out.println(u.getUsername());
                System.out.println(u.getPassword());
                System.out.println(u.getID());
                System.out.println(u.getRole());
            }
        }


    }

}
