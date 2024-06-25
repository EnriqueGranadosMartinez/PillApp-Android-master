package com.pillapp.models;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Drugs extends Reminder{
    public int id;
    public int patientId;
    public String description;
    public String dosePerIntake;
    public int period;
    public int startTimestamp;
    public Long duration;
    public Patient patient;
    public Long date;

    public Drugs(Patient patient, String description, Long reminderTimestamp) {
        super(reminderTimestamp);
        this.patient = patient;
        this.description = description;
        this.date = reminderTimestamp;

    }
    @Override
    public String getPatientText() {
        return patient.fullName;
    }

    @Override
    public String getType() {
        return "drug";
    }
    @Override
    public String getTitleText() {
        return description;
    }

    @Override
    public String getDateText() {
        Instant instant = Instant.ofEpochSecond(date);
        Date myDate = Date.from(instant);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy");
        return simpleDateFormat.format(myDate);
    }
}
