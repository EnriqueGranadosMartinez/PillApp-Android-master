package com.pillapp.models;

public abstract class Reminder {
   public Long reminderTimestamp;

   public Reminder(Long reminderTimestamp) {
      this.reminderTimestamp = reminderTimestamp;
   }

   public abstract String getDateText();
   public abstract String getTitleText();
   public abstract String getPatientText();
   public abstract String getType();
}
