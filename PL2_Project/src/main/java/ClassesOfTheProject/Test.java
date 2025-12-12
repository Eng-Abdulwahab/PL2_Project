package ClassesOfTheProject;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws FileNotFoundException{
        Employee C1=new Employee("2024-5-9","2025-4-9","hoba mohammed","hoba123","id_20241","employee");
        System.out.println(C1.viewTasks());
    }

}
