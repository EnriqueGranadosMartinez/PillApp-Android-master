package com.pillapp.models;

public class InCharge extends Reminder{
    public String fullName;
    public Patient patient;


    public InCharge(Long reminderTimestamp, Patient patient, String fullName) {
        super(reminderTimestamp);
        this.fullName = fullName;
        this.patient = patient;
    }

    @Override
    public String getDateText() {
        return null;
    }

    @Override
    public String getTitleText() {
        return null;
    }

    @Override
    public String getPatientText() {
        return null;
    }

    @Override
    public String getType() {
        return "inCharge";
    }
}
