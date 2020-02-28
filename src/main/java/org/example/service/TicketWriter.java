package org.example.service;

import org.example.exception.TicketWriteException;
import org.example.model.Ticket;

import java.util.List;

public interface TicketWriter {
    void writeOfferTickets(List<Ticket> tickets, List<String> offerCodes) throws TicketWriteException;

    void writeInvalidTickets(List<Ticket> tickets, List<String> errors) throws TicketWriteException;
}
