package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Factory class to get sample list of flights.
 */

class Main {

    public static void main(String[] args) {
        FlightBuilder flightBuilder = new FlightBuilder();
        List<Flight> flightList = flightBuilder.createFlights();

        SegmentFilter segmentFilter = new SegmentFilterImpl();
        segmentFilter.getAllSegments(flightList);

        System.out.println("\nФильтр номер 1. Сегменты с датой отправления после текущего времени\n");
        segmentFilter.getDepartureTimeBeforeNow(flightList);

        System.out.println("Фильтр номер 2. Сегменты с датой прибытия раньше времени отправления\n");
        segmentFilter.getArrivalDateLessDepartureDate(flightList);

        System.out.println("Фильтр номер 3. Время трансфера более двух часов\n");
        segmentFilter.getFlightWithTransferMoreThanTwoHours(flightList);

    }
}

class FlightBuilder {
    private Integer flightId = 0;
    private Integer segmentId = 0;

    public List<Flight> createFlights() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        return Arrays.asList(
            //A normal flight with two hour duration
            createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
            //A normal multi segment flight
            createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
            //A flight departing in the past
            createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
            //A flight that departs before it arrives
            createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),
            //A flight with more than two hours ground time
            createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
            //Another flight with more than two hours ground time
            createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));
    }

    protected Flight createFlight(final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException(
                "you must pass an even number of dates");
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            segmentId += 1;
            segments.add(new Segment(dates[i], dates[i + 1], segmentId));
        }
        flightId += 1;
        return new Flight(segments, flightId);
    }
}

/**
 * Bean that represents a flight.
 */
class Flight {
    private final Integer id;
    private final List<Segment> segments;

    Flight(final List<Segment> segs, Integer id) {
        segments = segs;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    List<Segment> getSegments() {
        return segments;
    }

    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }

}

/**
 * Bean that represents a flight segment.
 */
class Segment {
    private final Integer id;
    private final LocalDateTime departureDate;
    private final LocalDateTime arrivalDate;

    Segment(final LocalDateTime departure, final LocalDateTime arrival, Integer id) {
        departureDate = Objects.requireNonNull(departure);
        arrivalDate = Objects.requireNonNull(arrival);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    LocalDateTime getDepartureDate() {
        return departureDate;
    }

    LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd' T 'HH:mm");
        return '[' + departureDate.format(formatter) + '|' + arrivalDate.format(formatter)
                + ']';
    }
}

interface SegmentFilter {


    void getAllSegments(List<Flight> flights);

    Set<Flight> getDepartureTimeBeforeNow(List<Flight> flights);

    Set<Flight> getArrivalDateLessDepartureDate(List<Flight> flights);

    Set<Flight> getFlightWithTransferMoreThanTwoHours(List<Flight> flights);

}

class SegmentFilterImpl implements SegmentFilter {

    public void getAllSegments(List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println("Flight number - " + flight.getId());
            for (int i = 0; i < flight.getSegments().size(); i++) {
                System.out.println(flight.getSegments().get(i)  + " Segment number: "  + flight.getSegments().get(i).getId());
            }
        }
    }

    public Set<Flight> getDepartureTimeBeforeNow(List<Flight> flights) {
        LocalDateTime timeNow = LocalDateTime.now();
        Set<Flight> resultSet = new HashSet<>();
        List<Segment> split = new ArrayList<>();
        for (Flight flight : flights) {
            split.addAll(flight.getSegments());

            while (split.size() > 0) {
                LocalDateTime departureTime = (split.get(0).getDepartureDate());
                LocalDateTime arrivalTime = (split.remove(0).getArrivalDate());
                if (departureTime.isAfter(timeNow)) {
                    showRightFLight(flight, departureTime, arrivalTime);
                    resultSet.add(flight);
                }
            }

        }
        return resultSet;
    }

    public Set<Flight> getArrivalDateLessDepartureDate(List<Flight> flights) {

        Set<Flight> resultSet = new HashSet<>();
        List<Segment> split = new ArrayList<>();
        for (Flight flight : flights) {
            split.addAll(flight.getSegments());

            while (split.size() > 0) {
                LocalDateTime departureTime = split.get(0).getDepartureDate();
                LocalDateTime arrivalTime = split.remove(0).getArrivalDate();
                if (arrivalTime.isBefore(departureTime)) {
                    showRightFLight(flight, departureTime, arrivalTime);
                    resultSet.add(flight);
                }
            }

        }
        return resultSet;
    }


    public Set<Flight> getFlightWithTransferMoreThanTwoHours(List<Flight> flights) {
        Set<Flight> resultSet = new HashSet<>();
        List<Segment> split = new ArrayList<>();
        for (Flight flight : flights) {
            split.addAll(flight.getSegments());
            if (split.size() > 2) {
                while (split.size() > 2) {
                    LocalDateTime arrivalTime = split.remove(1).getArrivalDate();
                    LocalDateTime departureTime = split.remove(1).getDepartureDate();
                    if (departureTime.isAfter(arrivalTime.plusHours(2))) {
                        showTransfer(flight, arrivalTime, departureTime);
                        resultSet.add(flight);
                    }
                }
            }
        }
        return resultSet;
    }


    private void showRightFLight(Flight flight, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        DateTimeFormatter dateTimeFormatter = getDateFormatter();
        System.out.println("Flight number - " + flight.getId() + "\n" +
                "Departure time: " + dateTimeFormatter.format(departureTime) + "\n" + "Arrival time: " + dateTimeFormatter.format(arrivalTime));
        System.out.println("\n.\n");

    }

    private void showTransfer(Flight flight, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        DateTimeFormatter dateTimeFormatter = getDateFormatter();
        System.out.println("Flight number - " + flight.getId() + "\n"
                + "Arrival time: " + dateTimeFormatter.format(arrivalTime) + "\n" + "Departure time: " + dateTimeFormatter.format(departureTime));
        System.out.println("\n.\n");

    }

    private DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd' T 'HH:mm");
    }


}
