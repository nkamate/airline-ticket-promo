package org.example;

import junitx.framework.FileAssert;
import org.example.exception.TicketReadException;
import org.example.exception.TicketWriteException;
import org.example.service.CsvTicketReader;
import org.example.service.CsvTicketWriter;
import org.example.service.TicketReader;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class DefaultTicketUpgradeTest {

    private final String EXPECTED_ERRORS_FILE = "src/test/resources/expectedInvalidTickets.csv";
    private final String EXPECTED_OFFER_FILE = "src/test/resources/expectedOfferedTickets.csv";
    private final String INPUT_TICKETS = "src/test/resources/input.csv";

    @Test
    public void applyUpgradePromo() throws IOException, TicketReadException, TicketWriteException {
        TicketReader ticketReader = new CsvTicketReader();
        TicketUpgrade ticketUpgrade = new DefaultTicketUpgrade(ticketReader);
        ticketUpgrade.applyUpgradePromo(INPUT_TICKETS);

        FileAssert.assertEquals(Paths.get(EXPECTED_ERRORS_FILE).toFile(), Paths.get(CsvTicketWriter.INVALID_CSV_FILE_NAME).toFile());
        FileAssert.assertEquals(Paths.get(EXPECTED_OFFER_FILE).toFile(), Paths.get(CsvTicketWriter.OFFER_CSV_FILE_NAME).toFile());
    }
}