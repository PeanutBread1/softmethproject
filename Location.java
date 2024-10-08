package org.example;

public enum Location {
    BRIDGEWATER("Somerset", "08807"),
    EDISON("Middlesex", "08817"),
    PISCATAWAY("Middlesex", "08854"),
    PRINCETON("Mercer", "08540"),
    MORRISTOWN("Morris", "07960"),
    CLARK("Union", "07066");

    private final String county;
    private final String zip;

    Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
    }

    public String getCounty() {
        return county;
    }

    public String getZip() {
        return zip;
    }
    public String getCity() {
        return this.name();
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", this.name(), county, zip);
    }
}
