package org.example;
//@author jacob jeong
public class MedicalRecord {
    private Patient[] patients;
    private int size;

    public MedicalRecord(int capacity) {
        patients = new Patient[capacity];
        size = 0;
    }

    public void add(Patient patient) {

        if (size == patients.length) {
            expandCapacity();
        }
        patients[size++] = patient;
    }

    private void expandCapacity() {
        Patient[] newPatients = new Patient[patients.length * 2];
        System.arraycopy(patients, 0, newPatients, 0, patients.length);
        patients = newPatients;
    }

    public int getSize() {
        return size;
    }
    public Patient getPatient(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return patients[index];
    }
}