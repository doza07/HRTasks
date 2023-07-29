package ru.doza.json.ticekt;
import java.time.LocalDateTime;

public class TicketOptional {

    private CityAndTimeZone Origin;
    private CityAndTimeZone Destination;
    private LocalDateTime DepartureDateTime;
    private LocalDateTime ArrivalDateTime;
    private String Carrier;
    private Integer Stops;
    private Integer Price;

    public CityAndTimeZone getOrigin() {
        return Origin;
    }

    public CityAndTimeZone getDestination() {
        return Destination;
    }

    public void setDestination(CityAndTimeZone destination) {
        Destination = destination;
    }

    public LocalDateTime getDepartureDateTime() {
        return DepartureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        DepartureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return ArrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        ArrivalDateTime = arrivalDateTime;
    }

    public void setCarrier(String carrier) {
        Carrier = carrier;
    }


    public void setStops(Integer stops) {
        Stops = stops;
    }


    public void setPrice(Integer price) {
        Price = price;
    }

    public void setOrigin(CityAndTimeZone origin) {
        Origin = origin;
    }
}
