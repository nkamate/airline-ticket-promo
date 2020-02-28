package org.example.model;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

public class Ticket {

    @Pattern(regexp = "[a-zA-Z0-9\b]{6}$", message = "PNR invalid", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String pnr;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Pattern(regexp = "[A-Z]", message = "Fare Class Invalid")
    private String fareClass;

    private Date travelDate;

    @Min(1)
    private int pax;

    @Past
    private Date ticketingDate;

    @Email(message = "Email invalid")
    private String email;

    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid mobile number")
    private String mobile;

    @Pattern(regexp = "Economy|Premium Economy|Business|First", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Invalid cabin")
    private String bookedCabin;

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFareClass() {
        return fareClass;
    }

    public void setFareClass(String fareClass) {
        this.fareClass = fareClass;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = new Date(travelDate.getTime());
    }

    public Date getTicketingDate() {
        return ticketingDate;
    }

    public void setTicketingDate(Date ticketingDate) {
        this.ticketingDate = new Date(ticketingDate.getTime());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBookedCabin() {
        return bookedCabin;
    }

    public void setBookedCabin(String bookedCabin) {
        this.bookedCabin = bookedCabin;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "pnr='" + pnr + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fareClass='" + fareClass + '\'' +
                ", travelDate=" + travelDate +
                ", pax=" + pax +
                ", ticketingDate=" + ticketingDate +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", bookedCabin='" + bookedCabin + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return getPax() == ticket.getPax() &&
                getPnr().equals(ticket.getPnr()) &&
                getFirstName().equals(ticket.getFirstName()) &&
                getLastName().equals(ticket.getLastName()) &&
                getFareClass().equals(ticket.getFareClass()) &&
                getTravelDate().equals(ticket.getTravelDate()) &&
                getTicketingDate().equals(ticket.getTicketingDate()) &&
                getEmail().equals(ticket.getEmail()) &&
                getMobile().equals(ticket.getMobile()) &&
                getBookedCabin().equals(ticket.getBookedCabin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPnr(), getFirstName(), getLastName(), getFareClass(), getTravelDate(), getPax(), getTicketingDate(), getEmail(), getMobile(), getBookedCabin());
    }
}
