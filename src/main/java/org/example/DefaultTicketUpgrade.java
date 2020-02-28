package org.example;

import com.google.common.collect.Lists;
import org.example.exception.TicketReadException;
import org.example.exception.TicketWriteException;
import org.example.model.Ticket;
import org.example.service.CsvTicketWriter;
import org.example.service.TicketReader;
import org.example.service.TicketWriter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

public class DefaultTicketUpgrade implements TicketUpgrade {

    private static final Properties OFFERS = new Properties();

    private TicketReader ticketReader;

    public DefaultTicketUpgrade(TicketReader ticketReader) {
        this.ticketReader = ticketReader;
        this.loadOfferCodes();
    }

    private void loadOfferCodes() {
        try (InputStream inputStream = Files.newInputStream(Paths.get("src/main/resources/offerMappings.properties"))) {
            OFFERS.load(inputStream);
        } catch (IOException e) {
            Logger.getGlobal().warning("Offer code loading failed, offers will not be applied to tickets");
        }
    }

    public boolean applyUpgradePromo(String filename) throws TicketReadException, TicketWriteException {
        List<Ticket> tickets = ticketReader.readTickets(filename);
        List<Ticket> validTickets = Lists.newArrayList();
        List<Ticket> invalidTickets = Lists.newArrayList();
        List<String> errors = Lists.newArrayList();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        for (Ticket ticket : tickets) {
            Set<ConstraintViolation<Ticket>> violations = validator.validate(ticket, Default.class);
            if (!violations.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (ConstraintViolation<Ticket> violation : violations) {
                    if (stringBuilder.length() != 0) {
                        stringBuilder.append(":");
                    }
                    stringBuilder.append(violation.getMessage());
                }
                errors.add(stringBuilder.toString());
                invalidTickets.add(ticket);
            } else {
                validTickets.add(ticket);
            }
        }

        writeTicketsWithOffer(validTickets);
        writeTicketsWithErrors(invalidTickets, errors);


        return false;
    }

    private void writeTicketsWithErrors(List<Ticket> invalidTickets, List<String> errorMsgs) throws TicketWriteException {
        TicketWriter ticketWriter = new CsvTicketWriter();
        ticketWriter.writeInvalidTickets(invalidTickets, errorMsgs);
    }

    private void writeTicketsWithOffer(List<Ticket> validTickets) throws TicketWriteException {
        List<String> offerCodes = getPromoCodes(validTickets);
        TicketWriter ticketWriter = new CsvTicketWriter();
        ticketWriter.writeOfferTickets(validTickets, offerCodes);
    }

    private List<String> getPromoCodes(List<Ticket> tickets) {
        List<String> offerCodes = new ArrayList<>();
        for (Ticket ticket : tickets) {
            String promoCode = getPromoCode(ticket);
            offerCodes.add(promoCode);
        }
        return offerCodes;
    }

    private String getPromoCode(Ticket ticket) {
        String code = OFFERS.getProperty(ticket.getFareClass());
        return code == null ? "" : code;
    }
}
