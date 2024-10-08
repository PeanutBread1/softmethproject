package org.example;
//@author joshua pae
public class List {
    private Appointment[] appointments;
    private int size; //number of appointments in the array
    private static final int NOT_FOUND = -1;

    public List() {
        this.appointments = new Appointment [4];
        this.size = 0;
    }

    private int find(Appointment appointment) {
        for (int i = 0; i < size; i++) {
            if (appointments[i].compareTo(appointment) == 0) {
                return i;
            }
        }
        return NOT_FOUND;
    } //helper method

    private void grow() {
        int x = appointments.length;
        Appointment[] newApp = new Appointment[x+4];
        for(int i = 0; i< appointments.length; i++){
            newApp[i] = appointments[i];
        }
        appointments= newApp;

        //helper method to increase the capacity by 4
    }

    public boolean contains(Appointment appointment) {
       return find(appointment) != NOT_FOUND;
    }

    public void add(Appointment appointment) {
        int length = appointments.length;

        if(size == length){
            grow();

        }
        appointments[size] = appointment;
        size++;


    }

    public void remove(Appointment appointment) { //edgecase find if its not in list;\
        if (!contains(appointment)) {
            return; //edgecase not in list
        }
        int index = find(appointment);

        for(int i = index; i < size-1; i++){
            appointments[i] = appointments[i+1];
        }
        size--;
    }

    public void printByPatient() {
        if (size == 0) {
            System.out.println("The schedule calendar is empty.");
            return;
        }

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (appointments[j].getPatient().compareTo(appointments[j + 1].getPatient()) > 0) {
                    // Swap appointments[j] and appointments[j + 1]
                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }

        System.out.println("** Appointments ordered by patient/date/time **");
        for (int i = 0; i < size; i++) {
            System.out.println(appointments[i].toString());
        }
        System.out.println("** end of list **");
    }


    public void printByLocation() {
        if (size == 0) {
            System.out.println("The schedule calendar is empty.");
            return;
        }

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                String loc1 = appointments[j].getProvider().getLocation().getCity();
                String loc2 = appointments[j + 1].getProvider().getLocation().getCity();

                if (loc1.compareTo(loc2) > 0) {
                    // Swap appointments[j] and appointments[j + 1]
                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }

        System.out.println("** Appointments ordered by county/date/time **");
        for (int i = 0; i < size; i++) {
            System.out.println(appointments[i].toString());
        }
        System.out.println("** end of list **");
    }



    public void printByAppointment() {
        if (size == 0) {
            System.out.println("The schedule calendar is empty.");
            return;
        }

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                int comparisonResult = appointments[j].compareTo(appointments[j + 1]);

                if (comparisonResult == 0) {
                    String provider1 = appointments[j].getProvider().getName();
                    String provider2 = appointments[j + 1].getProvider().getName();
                    comparisonResult = provider1.compareTo(provider2);
                }

                if (comparisonResult > 0) {
                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }

        System.out.println("** Appointments ordered by date/time/provider **");
        for (int i = 0; i < size; i++) {
            System.out.println(appointments[i].toString());
        }
        System.out.println("** end of list **");
    }


    public Appointment[] getAppointments() {
        return appointments;
    }

    public int getSize() {
        return size;
    }



}

