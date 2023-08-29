package com.doza.sber.service;

import com.doza.sber.model.City;
import com.doza.sber.repositories.CityRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    public void printCities() throws FileNotFoundException {
        cityRepository.parseCity().forEach(System.out::println);
    }

    public void printCitiesSortedByName() throws FileNotFoundException {
        cityRepository.getCitiesSortedByName().forEach(System.out::println);
    }

    public void findMaxPopulation() throws FileNotFoundException {
        cityRepository.findMaxPopulation();
    }

    public void printCountCityInRegion() throws FileNotFoundException {
        cityRepository.printCountCityInRegion();
    }

}
