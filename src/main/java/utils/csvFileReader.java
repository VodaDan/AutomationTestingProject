package utils;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class csvFileReader {

    public static void readFile(String file) {
        try (Scanner scanner = new Scanner(Paths.get("../../test/resources/"+file+".csv"))) {
            ArrayList<String> arguments = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
