package com.doza.sber;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CityParse {

    public static List<City> parseCity() throws FileNotFoundException {
        List<City> cities = new ArrayList<>();

        Scanner scanner = new Scanner(new File("CityFile.csv"));
        while (scanner.hasNextLine()) {
            cities.add(parse(scanner.nextLine()));
        }
        scanner.close();
        return cities;
    }

    private static City parse(String line) {
        Scanner scanner = new Scanner(line);
        String[] values = scanner.nextLine().split(";", 6);

        for (int i = 0; i <= 5; i++) {
            if (values[i].isEmpty()) {
                values[i] = null;
            }
        }
        scanner.close();

        return new City(values[1], values[2], values[3], Integer.parseInt(values[4]), values[5]);

    }
}