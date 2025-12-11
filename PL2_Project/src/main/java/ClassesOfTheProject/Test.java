package ClassesOfTheProject;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws IOException {
        FileManager fileManager = new FileManager();
        Task task1 = new Task("Task2", "#15", new Employee() , new TeamLeader(), false);


        task1.markAsCompleted(task1);
    }

}
