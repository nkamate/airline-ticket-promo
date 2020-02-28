package org.example.service;

import com.google.common.collect.Lists;
import junitx.framework.FileAssert;
import org.example.exception.TicketWriteException;
import org.example.model.Ticket;
import org.junit.AfterClass;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.DateUtils.stringToDate;

public class CsvTicketWriterTest {

    @AfterClass
    public static void afterClass() throws Exception {
        Files.deleteIfExists(Paths.get(CsvTicketWriter.OFFER_CSV_FILE_NAME));
        Files.deleteIfExists(Paths.get(CsvTicketWriter.INVALID_CSV_FILE_NAME));
    }

    @Test
    public void writeInvalidTickets() throws ParseException, TicketWriteException {
        TicketWriter ticketWriter = new CsvTicketWriter();
        List<Ticket> tickets = getTicketsToWrite();

        ticketWriter.writeInvalidTickets(tickets, Lists.newArrayList("Email Invalid"));

        FileAssert.assertEquals(Paths.get(CsvTicketWriter.INVALID_CSV_FILE_NAME).toFile(), Paths.get("src/test/resources/invalidTickets.csv").toFile());

    }

    @Test
    public void writeTickets() throws ParseException, TicketWriteException {
        TicketWriter ticketWriter = new CsvTicketWriter();
        List<Ticket> tickets = getTicketsToWrite();
        ticketWriter.writeOfferTickets(tickets, Lists.newArrayList(""));
        FileAssert.assertEquals(Paths.get(CsvTicketWriter.OFFER_CSV_FILE_NAME).toFile(), Paths.get("src/test/resources/write-test-offer-tickets.csv").toFile());
    }

    private List<Ticket> getTicketsToWrite() throws ParseException {
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