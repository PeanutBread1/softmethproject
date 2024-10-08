package org.example;
//@Author Joshua
public class Appointment implements Comparable <Appointment> {
    private Date date;
    private Timeslot timeslot;
    private Profile patient;
    private Provider provider;

    public Appointment(Date date, Timeslot timeslot, Profile patient, Provider provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    public Date getDate() {
        return date;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public Profile getPatient() {
        return patient;
    }

    public Provider getProvider() {
        return provider;
    }

    @Override
    public int compareTo(Appointment o) {
        int dateComparison = this.date.compareTo(o.date);
        if (dateComparison != 0) {
            return dateComparison;
        }

        int timeslotComparison = this.timeslot.compareTo(o.timeslot); //if same date compare timeslot
        if (timeslotComparison != 0) {
            return timeslotComparison;
        }
        return 0;
    }
    @Override
    public String toString() {
        return date.toString() + " " + timeslot.toString() + " " + patient.toString() + " [" +
                provider.getName() + ", " + provider.getLocation().getCity() + ", " +
                provider.getLocation().getCounty() + " " + provider.getLocation().getZip() + ", " +
                 "]";
    }




}
