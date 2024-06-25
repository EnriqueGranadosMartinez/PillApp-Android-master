package com.pillapp.models;

import java.io.Serializable;

public class Patient implements Serializable {
    public int id;
    public String fullName;
    public String notes;
    public int kg;

    public Patient(int id, String fullName, String notes, int kg) {
        this.id = id;
        this.fullName = fullName;
        this.notes = notes;
        this.kg = kg;
    }

    public Patient(String fullName, String notes, int kg) {
        this.fullName = fullName;
        this.notes = notes;
        this.kg = kg;
    }
}
