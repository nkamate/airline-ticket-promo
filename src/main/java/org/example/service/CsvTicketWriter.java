package org.example.service;

import com.google.common.collect.Lists;
import org.example.exception.TicketWriteException;
import org.example.model.Ticket;
import org.example.utils.DateUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CsvTicketWriter implements TicketWriter {

    public static final String OFFER_CSV_FILE_NAME = "offeredTickets.csv";
    public static final String INVALID_CSV_FILE_NAME = "invalidTickets.csv";
    private static final String HEADER = "First_name,Last_name,PNR,Fare_class,Travel_date,Pax,Ticketing_date,Email,Mobile_phone,Booked_cabin";
    private static final String HEADER_WITH_OFFER = HEADER + ",Discount_code";
    private static final String HEADER_WITH_ERROR = HEADER + ",Error";

    @Override
    public void writeInvalidTickets(List<Ticket> tickets, List<String> errors) throws TicketWriteException {
        writeTickets(tickets, errors, INVALID_CSV_FILE_NAME, HEADER_WITH_ERROR);
    }

    @Override
    public void writeOfferTickets(List<Ticket> tickets, List<String> offerCodes) throws TicketWriteException {
        writeTickets(tickets, offerCodes, OFFER_CSV_FILE_NAME, HEADER_WITH_OFFER);
    }

    private void writeTickets(List<Ticket> tickets, List<String> additionalColumn, String fileName, String header) throws TicketWriteException {
        File csvOutputFile = new File(fileName);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pw.println(header);
            List<String> csvLines = getCsvLines(tickets);

            for (String s : csvLines) {
                String line = s + "," + additionalColumn.get(csvLines.indexOf(s));
                pw.println(line);
            }
        } catch (FileNotFoundException e) {
            throw new TicketWriteException(e);
        }
    }

    private List<String> getCsvLines(List<Ticket> tickets) {
        List<String> list = Lists.newArrayList();
        for (Ticket ticket : tickets) {
            String csvLine = getCsvLine(ticket);
            list.add(csvLine);
        }
        return list;
    }

    private String getCsvLine(Ticket ticket) {
        return ticket.getFirstName() + "," + ticket.getLastName() + "," + ticket.getPnr() + "," + ticket.getFareClass() + "," + DateUtils.dateToString(ticket.getTravelDate()) + "," + ticket.getPax() + "," + DateUtils.dateToString(ticket.getTicketingDate()) + "," + ticket.getEmail() + "," + ticket.getMobile() + "," + ticket.getBookedCabin();
    }

}
