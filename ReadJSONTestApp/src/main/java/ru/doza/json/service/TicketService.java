package ru.doza.json.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ru.doza.json.ticekt.CityAndTimeZone;
import ru.doza.json.ticekt.TicketOptional;
import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TicketService {

    public static List<TicketOptional> getJSON(String filename) {

        List<TicketOptional> ticketOptionals = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(filename)) {

            StringBuilder buffer = new StringBuilder();
            int c;
            boolean fl = false;
            while ((c = reader.read()) != -1) {
                if (fl) {
                    buffer.append((char) c);
                } else if (c == '{') {
                    fl = true;
                    buffer.append((char) c);
                }
            }
            JSONObject jsonObject = (JSONObject) parser.parse(String.valueOf(buffer));
            JSONArray jsonTickets = (JSONArray) jsonObject.get("tickets");

            for (Object o : jsonTickets) {

                JSONObject jsonTicket = (JSONObject) o;
                TicketOptional ticketOptional = new TicketOptional();

                ticketOptional.setOrigin(CityAndTimeZone.valueOf(jsonTicket.get("origin").toString()));
                ticketOptional.setDestination(CityAndTimeZone.valueOf(jsonTicket.get("destination").toString()));
                ticketOptional.setDepartureDateTime(LocalDateTime.parse(jsonTicket.get("departure_date") + " " + jsonTicket.get("departure_time"), DateTimeFormatter.ofPattern("d.M.y H:m")).plusYears(2000));
                ticketOptional.setArrivalDateTime(LocalDateTime.parse(jsonTicket.get("arrival_date") + " " + jsonTicket.get("arrival_time"), DateTimeFormatter.ofPattern("d.M.y H:m")).plusYears(2000));
                ticketOptional.setCarrier(jsonTicket.get("carrier").toString());
                ticketOptional.setStops(Integer.parseInt(jsonTicket.get("stops").toString()));
                ticketOptional.setPrice(Integer.parseInt(jsonTicket.get("price").toString()));

                ticketOptionals.add(ticketOptional);
            }
            return ticketOptionals;
        } catch (Exception error) {
            error.printStackTrace();

            return null;
        }
    }
}
