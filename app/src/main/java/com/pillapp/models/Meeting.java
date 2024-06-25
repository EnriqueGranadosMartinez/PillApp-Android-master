package com.pillapp.models;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Meeting extends Reminder {
    public int id;
    public Patient patient;
    public String description;
    public Long appointment;
    

    public Meeting(Patient patient, String description, Long appointment, Long reminderTimestamp) {
        super(reminderTimestamp);
        this.patient = patient;
        this.description= description;
        this.appointment = appointment;
    }

    @Override
    public String getPatientText(){
        return patient.fullName;
    }

    @Override
    public String getType() {
        return "meeting";
    }

    @Override
    public String getTitleText(){
        return description;
    }

    @Override
    public String getDateText() {
        Instant instant = Instant.ofEpochSecond(appointment);
        Date myDate = Date.from(instant);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy");
        return simpleDateFormat.format(myDate);
    }
}
