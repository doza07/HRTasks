package com.doza.sber;


import java.io.FileNotFoundException;
import java.util.List;

import static com.doza.sber.CityParse.parseCity;


public class Main {


    public static void main( String[] args ) throws FileNotFoundException {

            List<City> cityList = parseCity();
        System.out.println(cityList);


    }
}
