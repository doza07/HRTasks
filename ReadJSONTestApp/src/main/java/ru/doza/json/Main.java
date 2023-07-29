package ru.doza.json;


import ru.doza.json.service.Calculator;
import ru.doza.json.service.TicketService;
import ru.doza.json.ticekt.TicketOptional;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<TicketOptional> ticketOptionals = new ArrayList<>(TicketService.getJSON("tickets.json"));

        long averagTimeFlight = Calculator.averageTime(ticketOptionals);
        System.out.println("Average flight time: " + averagTimeFlight / 60 / 60 + " h. " + (averagTimeFlight / 60) % 60 + " m.");

        long percentile = Calculator.percentile(ticketOptionals, 0.90);
        System.out.println( "90 percentile of flight time between city = "
                + percentile / 60 / 60 + " h. " + (percentile / 60) % 60 + " m.");
    }

}