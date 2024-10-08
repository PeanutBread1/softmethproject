package org.example;
//@author Joshua Pae

public class Date implements Comparable<Date> {
    public static final int JANUARY = 1, MARCH = 3, MAY = 5, JULY = 7, AUGUST = 8, OCTOBER = 10, DECEMBER = 12;
    public static final int APRIL = 4, JUNE = 6, SEPTEMBER = 9, NOVEMBER = 11;
    public static final int FEBRUARY = 2;

    public static final int DAYS_31 = 31;
    public static final int DAYS_30 = 30;
    public static final int FEB_NON_LEAP = 28;
    public static final int FEB_LEAP = 29;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    private int year;
    private int month;
    private int day;

    public Date(int month, int day, int year) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public boolean isValid() {
        if (month < 1 || month > 12 || day < 1 || day > 31) {
            return false;
        }
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return day <= 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return day <= 30;
        }
        if (month == 2) {

            if (isLeapYear()) {
                return day <= 29;
            } else {
                return day <= 28;
            }
        }
        return false;
    }

    public boolean isLeapYear() {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                return year % QUATERCENTENNIAL == 0;
            }
            return true;
        }
        return false;
    }


    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isValid(Date date){
        return true;
    } //check if the date is a valid calendar date

    @Override
    public int compareTo(Date o) {
        if(this.year < o.year){
            return -1;
        } else if(this.year > o.year){
            return 1;

        }
        if(this.month < o.month){
            return -1;
        }
        else if(this.month > o.month) {
            return 1;
        }
        if(this.day < o.day){
            return -1;
        }else if(this.day > o.day){
            return 1;
        }
        return 0;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Date)){
            return false;
        }
        Date other = (Date) obj;  //downcast cast after instanceof check.

        return this.year == other.year &&
                this.month == other.month &&
                this.day == other.day;

       //If all fields are equal, return true.
    }
    @Override
    public String toString(){
        return String.format("%02d/%02d/%d", month, day, year);
    }
    public static void main(String[] args) {
        Date invalidDate1 = new Date(13, 20, 2024);
        Date invalidDate2 = new Date(2, 30, 2024);
        Date invalidDate3 = new Date(1, 1, 2000);
        Date invalidDate4 = new Date(9, 25, 2021);  //Assuming it's a Saturday

        Date validDate1 = new Date(10, 4, 2024);
        Date validDate2 = new Date(5, 7, 2022);

        System.out.println("Invalid Date 1: " + invalidDate1.isValid()); //false
        System.out.println("Invalid Date 2: " + invalidDate2.isValid()); //false
        System.out.println("Invalid Date 3: " + invalidDate3.isValid()); //false
        System.out.println("Invalid Date 4: " + invalidDate4.isValid()); //false
        System.out.println("Valid Date 1: " + validDate1.isValid());   //true
        System.out.println("Valid Date 2: " + validDate2.isValid());   //true
    }
}