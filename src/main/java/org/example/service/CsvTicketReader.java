package org.example.service;

import org.example.exception.TicketReadException;
import org.example.model.Ticket;
import org.example.utils.DateUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class CsvTicketReader implements TicketReader {

    public static final String CSV_SEPARATOR = ",";

    private static Ticket getTicketInstance(String firstName, String lastName, String email, String pnr,
                                            String bookedCabin, String fareClass, String mobile, String pax,
                                            String ticketingDate, String travelDate) throws ParseException {
        Ticket ticket = new Ticket();
        ticket.setMobile(mobile);
        ticket.setEmail(email);
        ticket.setPax(Integer.parseInt(pax));
        ticket.setTicketingDate(DateUtils.stringToDate(ticketingDate));
        ticket.setTravelDate(DateUtils.stringToDate(travelDate));
        ticket.setPnr(pnr);
        ticket.setFareClass(fareClass);
        ticket.setBookedCabin(bookedCabin);
        ticket.setLastName(lastName);
        ticket.setFirstName(firstName);
        return ticket;
    }

    public List<Ticket> readTickets(String fileName) throws TicketReadException {
        try {
            List<Ticket> tickets = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line = br.readLine(); // Read header and ignore
                while ((line = br.readLine()) != null && !line.isEmpty()) {
                    Ticket ticket = getTicketFromLine(line);
                    tickets.add(ticket);
                }
            }
            return tickets;
        } catch (IOException e) {
            throw new TicketReadException(e);
        }
    }

    private Ticket getTicketFromLine(String line) throws TicketReadException {
        String[] fields = line.split(CSV_SEPARATOR);
        String firstName = fields[Columns.FIRST_NAME.ordinal()];
        String lastName = fields[Columns.LAST_NAME.ordinal()];
        String pnr = fields[Columns.PNR.ordinal()];
        String fareClass = fields[Columns.FARE_CLASS.ordinal()];
        String travelDate = fields[Columns.TRAVEL_DATE.ordinal()];
        String pax = fields[Columns.PAX.ordinal()];
        String ticketingDate = fields[Columns.TICKETING_DATE.ordinal()];
        String email = fields[Columns.EMAIL.ordinal()];
        String mobile = fields[Columns.MOBILE_PHONE.ordinal()];
        String bookedCabin = fields[Columns.BOOKED_CABIN.ordinal()];
        try {
            return getTicketInstance(firstName, lastName, email, pnr, bookedCabin, fareClass, mobile, pax, ticketingDate, travelDate);
        } catch (ParseException e) {
            throw new TicketReadException(e);
        }
    }

    private enum Columns {
        FIRST_NAME("First_name"),
        LAST_NAME("Last_name"),
        PNR("PNR"),
        FARE_CLASS("Fare_class"),
        TRAVEL_DATE("Travel_date"),
        PAX("Pax"),
        TICKETING_DATE("Ticketing_date"),
        EMAIL("Email"),
        MOBILE_PHONE("Mobile_phone"),
        BOOKED_CABIN("Booked_cabin");

        private String columnName;

        Columns(String columnName) {
            this.columnName = columnName;
        }
    }
}
