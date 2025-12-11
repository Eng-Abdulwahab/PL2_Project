package ClassesOfTheProject;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws IOException {
        FileManager fileManager = new FileManager();
        File file1 = new File("D:\\test1.txt");
        File file2 = new File("D:\\test2.txt");

        ArrayList<String> Line = new ArrayList<>();
        Line.add("Abdelwahab");
        Line.add("15948");
        Line.add("#159");
        Line.add("TeamLeader");

        fileManager.addLine(Line, file1);
        fileManager.addLine(Line, file2);

        ArrayList<String> data = new ArrayList<>();
        data = fileManager.Splitter(Line, file1);

        System.out.println(data);
    }

}
