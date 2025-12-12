package ClassesOfTheProject;

import javafx.scene.input.DataFormat;
import jdk.swing.interop.SwingInterOpUtils;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dateString = dateTime.format(formatter);

        System.out.println(dateString); // e.g., "12/12/2025 18:30:25"
    }

}
