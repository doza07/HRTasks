package com.doza.sber.repositories;

import com.doza.sber.model.City;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CityRepository {

    private List<City> cities;

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


    public List<City> getCitiesSortedByName() throws FileNotFoundException {
        List<City> sortedCities = new ArrayList<>(parseCity());
        sortedCities.sort(Comparator.comparing(City::getName));
        return sortedCities;
    }

    public List<City> getCitiesSortedByDistrict() throws FileNotFoundException {
        List<City> sortedCities = new ArrayList<>(parseCity());
        sortedCities.sort(Comparator.comparing(City::getDistrict));

        return sortedCities;
    }

    public void findMaxPopulation() throws FileNotFoundException {
        int maxInd = 0 ;
        int maxPopulation = 0;

        City[] citiesArray = parseCity().toArray(new City[0]);

        for (int i = 0; i < citiesArray.length; i++)
            if (citiesArray[i].getPopulation() > maxPopulation){
                maxPopulation = citiesArray[i].getPopulation();
                maxInd = i;
            }

        System.out.println("City with index" + maxInd + " = " + maxPopulation + " peoples");
    }

    public void printCountCityInRegion() throws FileNotFoundException {
        List<City> cities = getCitiesSortedByDistrict();
        String ourReg = cities.get(0).getRegion();
        int countCity = 0;

        for (City c : cities) {
            if (c.getRegion().equals(ourReg))
                countCity++;
            else {
                System.out.println(ourReg + " - " + countCity);
                ourReg = c.getRegion();
                countCity = 1;
            }
        }
    }
}
