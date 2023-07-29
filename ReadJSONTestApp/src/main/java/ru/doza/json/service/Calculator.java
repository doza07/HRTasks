package ru.doza.json.service;

import ru.doza.json.ticekt.TicketOptional;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public static long timeOfFlight(TicketOptional ticketOptional) {

        long time;
        time = ticketOptional.getArrivalDateTime().toEpochSecond(ZoneOffset.of(ticketOptional.getDestination().getTimezone())) -
                ticketOptional.getDepartureDateTime().toEpochSecond(ZoneOffset.of(ticketOptional.getOrigin().getTimezone()));
        return time;
    }


    public static long averageTime(List<TicketOptional> ticketOptionals) {
        long average = 0;
        for (TicketOptional ticketOptional : ticketOptionals) {
            average += timeOfFlight(ticketOptional) / ticketOptionals.size();
        }
        return average;
    }


//    https://investprofit.info/percentile/?ysclid=lfax8xr01l941205172
    public static long percentile(List<TicketOptional> ticketOptionals, double k) {
        List<Long> times = new ArrayList<>();
        for (TicketOptional ticketOptional : ticketOptionals) {
            times.add(timeOfFlight(ticketOptional));
        }
        times.sort(Long::compareTo);
        int countTimes = 0;
        for (long tm : times) {
            double percentile = 1.0 * (++countTimes) / times.size();
            if (k <= percentile) {
                return tm;
            }
        }
        return 0;
    }


}






