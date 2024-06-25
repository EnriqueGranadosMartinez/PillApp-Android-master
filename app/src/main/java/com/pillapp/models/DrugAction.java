package com.pillapp.models;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class DrugAction extends Reminder{
    public Long date;
    public Patient patient;
    public String description;

    public DrugAction(Long reminderTimestamp, Long date, Patient patient, String description) {
        super(reminderTimestamp);
        this.date = date;
        this.patient = patient;
        this.description = description;
    }

    @Override
    public String getDateText() {
        Instant instant = Instant.ofEpochSecond(date);
        Date myDate = Date.from(instant);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy");
        return simpleDateFormat.format(myDate);    }

    @Override
    public String getTitleText() {
        return description;
    }

    @Override
    public String getPatientText() {
        return patient.fullName;
    }

    @Override
    public String getType() {
        return "drugAction";
    }
}
