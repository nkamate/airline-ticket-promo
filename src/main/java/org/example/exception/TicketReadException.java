package org.example.exception;

public class TicketReadException extends Exception {
    public TicketReadException(Exception e) {
        super(e);
    }
}
