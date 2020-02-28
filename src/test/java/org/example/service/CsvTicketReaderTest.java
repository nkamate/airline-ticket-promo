package org.example.service;

import org.example.exception.TicketReadException;
import org.example.model.Ticket;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.DateUtils.stringToDate;

public class CsvTicketReaderTest {

    @Test
    public void readTickets() throws ParseException, TicketReadException {
        CsvTicketReader ticketReader = new CsvTicketReader();
        List<Ticket> tickets = ticketReader.readTickets("src/test/resources/read-write-test-input.csv");
        Assert.assertEquals("CSV Ticket reading has failed", getExpectedTickets(), tickets);
    }

    private List<Ticket> getExpectedTickets() throws ParseException {
        Ticket ticket = new Ticket();
        ticket.setFirstName("Abhishek");
        ticket.setLastName("Kumar");
        ticket.setFareClass("F");
        ticket.setPnr("ABC123");
        ticket.setTravelDate(stringToDate("2019-07-31"));
        ticket.setTicketingDate(stringToDate("2019-05-21"));
        ticket.setPax(2);
        ticket.setEmail("abhishek@zzz.com");
        ticket.setMobile("9876543210");
        ticket.setBookedCabin("Economy");

        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);

        return tickets;
    }


}