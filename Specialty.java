package org.example;
//@author jacob jeong
public enum Specialty {
    FAMILY(250),
    PEDIATRICIAN(300),
    ALLERGIST(350);

    private final int charge;

    Specialty(int charge) {
        this.charge = charge;
    }

    public int getCharge() {
        return charge;
    }

    @Override
    public String toString() {
        return String.format("%s (Charge per visit: $%d)", this.name(), charge);
    }
}