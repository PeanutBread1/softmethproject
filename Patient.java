package org.example;
//@author jacob jeong
public class Patient implements Comparable<Patient> {
    private Profile profile;
    private Visit visitsHead; // Head of the linked list of visits

    public Patient(Profile profile) {
        this.profile = profile;
        this.visitsHead = null;
    }

    public void addVisit(Appointment appointment) {
        Visit newVisit = new Visit(appointment);
        if (visitsHead == null) {
            visitsHead = newVisit;
        } else {
            visitsHead.addVisit(newVisit);
        }
    }

    public int charge() {
        int totalCharge = 0;
        Visit current = visitsHead;
        while (current != null) {
            totalCharge += current.getAppointment().getProvider().getSpecialty().getCharge();
            current = current.getNext();
        }
        return totalCharge;
    }

    @Override
    public int compareTo(Patient other) {
        return this.profile.compareTo(other.profile);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Patient) {
            Patient other = (Patient) obj;
            return this.profile.equals(other.profile);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Patient: %s", profile);
    }
}