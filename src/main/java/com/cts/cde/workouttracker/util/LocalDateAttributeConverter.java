package com.cts.cde.workouttracker.util;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.time.LocalDate;
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date>{
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return (localDate == null ? null: Date.valueOf(localDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return (date == null ? null : LocalDate.of(date.getYear(), date.getMonth(), date.getDay()));
    }
}
