package com.cts.cde.workouttracker.util;

import javax.persistence.AttributeConverter;
import java.sql.Time;
import java.time.LocalTime;

public class LocalTimeAttributeConverter implements AttributeConverter<LocalTime, Time>{
    @Override
    public Time convertToDatabaseColumn(LocalTime localTime) {
        return (localTime == null ? null:Time.valueOf(localTime));
    }

    @Override
    public LocalTime convertToEntityAttribute(Time time) {
        return (time == null ? null : LocalTime.of(time.getHours(), time.getMinutes()));
    }
}
