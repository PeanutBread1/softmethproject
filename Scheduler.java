package org.example;
import java.util.*;
//@Author Joshua
import java.util.Calendar;
import java.util.Scanner;

public class Scheduler {
    private static final String QUIT_COMMAND = "Q";
    private List appointmentList; //create list

    public Scheduler() {
        appointmentList = new List();
    }
    //run the scheduler driver class
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scheduler is running.");

        while (true) {
            String input = scanner.nextLine().trim(); //removes whitespace

            if (input.equals(QUIT_COMMAND)) {
                System.out.println("Scheduler terminated.");
                break;
            }

            if (!input.isEmpty()) {
                inputCommand(input); //takes input
            }
        }
        scanner.close();
    }

    private void inputCommand(String input) {
        String[] tokens = input.split(",");
        String command = tokens[0].toUpperCase();

        switch (command) {
            case "S": //Schedule new appointment
                if (tokens.length != 7) {
                    System.out.println("Invalid command!");
                } else {
                    handleSchedule(tokens);
                }
                break;
            case "C": //Cancel appointment
                if (tokens.length != 6) {
                    System.out.println("Invalid command!");
                } else {
                    handleCancel(tokens);
                }
                break;
            case "R": //Reschedule appointment
                if (tokens.length != 7) {
                    System.out.println("Invalid command!");
                } else {
                    handleReschedule(tokens);
                }
                break;
            case "PA": //Print appointments by appointment details
                appointmentList.printByAppointment();
                break;
            case "PP": //Print appointments by patient details
                appointmentList.printByPatient();
                break;
            case "PL": //Print appointments by location
                appointmentList.printByLocation();
                break;
            case "PS": //Print billing statements
                printBillingStatements();
                break;
            default:
                System.out.println("Invalid command!");
                break;
        }
    }
    //handle proper order of schedule input takes as array
    private void handleSchedule(String[] tokens) {
        if (tokens.length != 7) {
            System.out.println("Invalid command for scheduling. Expecting 7 parameters.");
            return;
        }

        String[] dateTokens = tokens[1].split("/");
        int apptMonth, apptDay, apptYear;
        try {
            apptMonth = Integer.parseInt(dateTokens[0]);
            apptDay = Integer.parseInt(dateTokens[1]);
            apptYear = Integer.parseInt(dateTokens[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid date format for the appointment.");
            return;
        }

        Date appointmentDate = new Date(apptMonth, apptDay, apptYear);
        if (!appointmentDate.isValid()) {
            System.out.println("Appointment date: " + appointmentDate + " is not a valid calendar date.");
            return;
        }
        if (isWeekend(appointmentDate)) {
            System.out.println("Appointment date: " + appointmentDate + " is Saturday or Sunday.");
            return;
        }

        int timeslotIndex;
        try {
            timeslotIndex = Integer.parseInt(tokens[2]);
        } catch (NumberFormatException e) {
            System.out.println(tokens[2] + " is not a valid timeslot.");
            return;
        }

        if (timeslotIndex < 1 || timeslotIndex > 6) {
            System.out.println(tokens[2] + " is not a valid timeslot.");
            return;
        }

        Timeslot timeslot = Timeslot.values()[timeslotIndex - 1];

        String firstName = tokens[3];
        String lastName = tokens[4];

        String[] dobTokens = tokens[5].split("/");
        int dobMonth, dobDay, dobYear;
        try {
            dobMonth = Integer.parseInt(dobTokens[0]);
            dobDay = Integer.parseInt(dobTokens[1]);
            dobYear = Integer.parseInt(dobTokens[2]);
        } catch (NumberFormatException e) {
            System.out.println("Patient dob: " + dobTokens[0] + '/'+ dobTokens[1] + '/' + dobTokens[2] + " is not a valid calendar date.");
            return;
        }

        Date dob = new Date(dobMonth, dobDay, dobYear);
        if (!dob.isValid()) {
            System.out.println("Patient dob: " + dob + " is not a valid calendar date.");
            return;
        }

        Profile patientProfile = new Profile(firstName, lastName, dobMonth, dobDay, dobYear);

        String providerName = tokens[6];
        Provider provider = findProviderByName(providerName);

        if (provider == null) {
            System.out.println(providerName + " - provider does not exist");
            return;
        }

        Appointment newAppointment = new Appointment(appointmentDate, timeslot, patientProfile, provider);

        if (appointmentList.contains(newAppointment)) {
            System.out.println("Duplicate appointment.");
        } else {
            appointmentList.add(newAppointment);
            System.out.println(newAppointment + " booked.");
        }
    }

    private void handleCancel(String[] tokens) {
        if (tokens.length != 6) {
            System.out.println("Invalid command for canceling.");
            return;
        }

        String[] dateTokens = tokens[1].split("/");
        int apptMonth = Integer.parseInt(dateTokens[0]);
        int apptDay = Integer.parseInt(dateTokens[1]);
        int apptYear = Integer.parseInt(dateTokens[2]);
        Date appointmentDate = new Date(apptMonth, apptDay, apptYear);

        Timeslot timeslot = Timeslot.values()[Integer.parseInt(tokens[2]) - 1];

        String firstName = tokens[3];
        String lastName = tokens[4];
        String[] dobTokens = tokens[5].split("/");
        int dobMonth = Integer.parseInt(dobTokens[0]);
        int dobDay = Integer.parseInt(dobTokens[1]);
        int dobYear = Integer.parseInt(dobTokens[2]);

        Profile patientProfile = new Profile(firstName, lastName, dobMonth, dobDay, dobYear);

        Appointment appointmentToRemove = new Appointment(appointmentDate, timeslot, patientProfile, null);

        if (appointmentList.contains(appointmentToRemove)) {
            appointmentList.remove(appointmentToRemove);
            System.out.println("Appointment canceled.");
        } else {
            System.out.println("Appointment does not exist.");
        }
    }

    private void handleReschedule(String[] tokens) {
        if (tokens.length != 7) {
            System.out.println("Invalid command for rescheduling.");
            return;
        }

        String[] dateTokens = tokens[1].split("/");
        int apptMonth = Integer.parseInt(dateTokens[0]);
        int apptDay = Integer.parseInt(dateTokens[1]);
        int apptYear = Integer.parseInt(dateTokens[2]);
        Date appointmentDate = new Date(apptMonth, apptDay, apptYear);




        Timeslot oldTimeslot = Timeslot.values()[Integer.parseInt(tokens[2]) - 1];

        String firstName = tokens[3];
        String lastName = tokens[4];
        String[] dobTokens = tokens[5].split("/");
        int dobMonth = Integer.parseInt(dobTokens[0]);
        int dobDay = Integer.parseInt(dobTokens[1]);
        int dobYear = Integer.parseInt(dobTokens[2]);
        Profile patientProfile = new Profile(firstName, lastName, dobMonth, dobDay, dobYear);

        Timeslot newTimeslot = Timeslot.values()[Integer.parseInt(tokens[6]) - 1];

        Appointment appointmentToReschedule = new Appointment(appointmentDate, oldTimeslot, patientProfile, null);

        if (appointmentList.contains(appointmentToReschedule)) {
            appointmentList.remove(appointmentToReschedule);

            Appointment rescheduledAppointment = new Appointment(appointmentDate, newTimeslot, patientProfile, null);

            appointmentList.add(rescheduledAppointment);
            System.out.println("Appointment rescheduled.");
        } else {
            System.out.println("Appointment does not exist.");
        }
    }
    private boolean isWeekend(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonth() - 1, date.getDay());  //subtract 1 from month because Calendar months are 0-based

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
    }
    private void printBillingStatements() {
        System.out.println("Billing statements for all patients:");

    }

    private Provider findProviderByName(String name) {
        for (Provider provider : Provider.values()) {
            if (provider.getName().equalsIgnoreCase(name)) {
                return provider;
            }
        }
        return null;
    }
}
