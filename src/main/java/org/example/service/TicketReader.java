package org.example.service;

import org.example.exception.TicketReadException;
import org.example.model.Ticket;

import java.util.List;

public interface TicketReader {
    List<Ticket> readTickets(String fileName) throws TicketReadException;
}
