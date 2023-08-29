package com.doza.sber;


import com.doza.sber.repositories.CityRepository;
import com.doza.sber.service.CityService;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {


    public static void main( String[] args ) throws FileNotFoundException {

        try {
            CityRepository cityRepository = new CityRepository();
            CityService cityService = new CityService(cityRepository);

            cityService.printCountCityInRegion();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}