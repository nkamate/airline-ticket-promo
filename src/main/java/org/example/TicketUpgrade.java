package org.example;

import org.example.exception.TicketReadException;
import org.example.exception.TicketWriteException;

import java.io.FileNotFoundException;

public interface TicketUpgrade {
    boolean applyUpgradePromo(String fileName) throws FileNotFoundException, TicketReadException, TicketWriteException;
}
