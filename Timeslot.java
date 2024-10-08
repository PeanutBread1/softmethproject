package org.example;
//@author joshua pae
public enum Timeslot {
    SLOT1(9, 0),
    SLOT2(10, 45),
    SLOT3(11, 15),
    SLOT4(13, 30),
    SLOT5(15, 0),
    SLOT6(16, 15);
    private final int hour;
    private final int minute;

    Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public String toString() {
        String amPm;
        int displayHour;

        //if AM or PM
        if (hour < 12) {
            amPm = "AM";
            displayHour = hour;  //for AM use same hour
        } else {
            amPm = "PM";
            if (hour == 12) {
                displayHour = 12;  //noon
            } else {
                displayHour = hour - 12;  //24 hr to 12 hr
            }
        }

        if (hour == 0) {
            displayHour = 12;
        }

        return formatWithLeadingZero(displayHour) + ":" + formatWithLeadingZero(minute) + " " + amPm;
    }

    //helper method needed to make it double digit for single digit hours
    private String formatWithLeadingZero(int number) {
        if (number < 10) {
            return "0" + number;
        } else {
            return Integer.toString(number);
        }


    }
}
