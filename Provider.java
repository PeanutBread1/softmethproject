package org.example;
//@Joshua Pae
public enum Provider {
    PROVIDER_1("Patel", Location.BRIDGEWATER, Specialty.FAMILY),
    PROVIDER_2("Lim", Location.BRIDGEWATER, Specialty.PEDIATRICIAN),
    PROVIDER_3("Zimnes", Location.CLARK, Specialty.FAMILY),
    PROVIDER_4("Harper", Location.CLARK, Specialty.FAMILY),
    PROVIDER_5("Kaur", Location.PRINCETON, Specialty.ALLERGIST),
    PROVIDER_6("Taylor", Location.PISCATAWAY, Specialty.PEDIATRICIAN),
    PROVIDER_7("Ramesh", Location.MORRISTOWN, Specialty.ALLERGIST),
    PROVIDER_8("Ceravolo", Location.EDISON, Specialty.PEDIATRICIAN);

    private final String name;
    private final Location location;
    private final Specialty specialty;

    Provider(String name, Location location, Specialty specialty) {
        this.name = name;
        this.location = location;
        this.specialty = specialty;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    @Override
    public String toString() {
        return name + " (" + specialty + ") - " + location;
    }
}
