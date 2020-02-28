package org.example.validation;

import org.example.model.Ticket;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TravelDateValidator implements ConstraintValidator<TravelDate, Ticket> {
    @Override
    public boolean isValid(Ticket ticket, ConstraintValidatorContext context) {
        return ticket.getTravelDate().after(ticket.getTicketingDate());
    }
}
