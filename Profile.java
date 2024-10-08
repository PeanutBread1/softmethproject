package org.example;

// @author Jacob Jeong
public class Profile implements Comparable<Profile> {
    private String fName;
    private String lName;
    private Date dob;  // Using the custom Date class provided

    public Profile(String fName, String lName, int month, int day, int year) {
        this.fName = fName;
        this.lName = lName;
        this.dob = new Date(month, day, year);
    }

    public String getFirstName() {
        return fName;
    }

    public String getLastName() {
        return lName;
    }

    public Date getDateOfBirth() {
        return dob;
    }

    @Override
    public String toString() {
        return String.format("%s %s %02d/%02d/%04d",
                this.fName,
                this.lName,
                this.dob.getMonth(),
                this.dob.getDay(),
                this.dob.getYear());    }

    public int compareTo(Profile other) {
        int lastCmp = lName.compareTo(other.lName);
        if (lastCmp != 0) return Integer.signum(lastCmp);
        int firstCmp = fName.compareTo(other.fName);
        if (firstCmp != 0) return Integer.signum(firstCmp);
        return Integer.signum(dob.compareTo(other.dob));
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  //Check for reference equality.
        if (obj == null || !(obj instanceof Profile))
            return false;  //Check for null and ensure the object is an instance of Profile.

        Profile other = (Profile) obj;  //downcast cast after instanceof check.

        //Check first name
        if (fName != null) {
            if (!fName.equals(other.fName)) return false;
        } else if (other.fName != null) {
            return false;
        }

        //Check last name
        if (lName != null) {
            if (!lName.equals(other.lName)) return false;
        } else if (other.lName != null) {
            return false;
        }

        //Check date of birth
        if (dob != null) {
            if (!dob.equals(other.dob)) return false;
        } else if (other.dob != null) {
            return false;
        }

        return true;  //If all fields are equal, return true.
    }




    public static void main(String[] args) {
        /*
        For the Profile class's compareTo() method test cases:
        The first test case returns -1 because it evaluates profiles based on last names,
        with "Doe" coming before "Smith" alphabetically. The second case also returns -1, comparing profiles with
        the same last name; it sorts "Jane" before "John" based on first names.
        The third scenario returns -1 when comparing profiles with identical full names ("John Smith"),
        by ordering them based on their birthdays from earlier to later (12/12/1990 before 12/13/1990).
        The tests proves the method accurately reflects the natural sorting order of profiles,
        taking into account different attributes.
         */
        Profile profileA = new Profile("John", "Doe", 1, 1, 1990);
        Profile profileB = new Profile("Jane", "Smith", 5, 15, 1995);
        Profile profileC = new Profile("John", "Smith", 1, 1, 1990);
        Profile profileD = new Profile("John", "Smith", 1, 2, 1990);

        System.out.println("Test Case 1 (Should be -1): " + profileA.compareTo(profileB));
        System.out.println("Test Case 2 (Should be 1): " + profileB.compareTo(profileA));
        System.out.println("Test Case 3 (Should be 0): " + profileC.compareTo(profileC));
        System.out.println("Test Case 4 (Should be -1): " + profileC.compareTo(profileD));

    }
}