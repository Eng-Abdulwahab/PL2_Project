package ClassesOfTheProject;

import jdk.swing.interop.SwingInterOpUtils;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws FileNotFoundException{
        Random rand = new Random();

        int num = rand.nextInt(1,100);

        System.out.println(num);
    }

}
