package org.example;
//@author jacob jeong
public class Visit {
    private Appointment appointment;
    private Visit next; //next visit node

    public Visit(Appointment appointment) {
        this.appointment = appointment;
        this.next = null;
    }

    //Getters and Setters
    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Visit getNext() {
        return next;
    }

    public void setNext(Visit next) {
        this.next = next;
    }

    //Method to add a visit to the end of LL
    public void addVisit(Visit newVisit) {
        Visit current = this;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newVisit;
    }
}