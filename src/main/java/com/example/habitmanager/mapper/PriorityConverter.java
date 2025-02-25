package com.example.habitmanager.mapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.example.habitmanager.models.Habit.Priority;

@Converter(autoApply = true)
public class PriorityConverter implements AttributeConverter<Priority, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Priority priority) {
        return (priority == null) ? null : priority.getValue();
    }

    @Override
    public Priority convertToEntityAttribute(Integer dbData) {
        return (dbData == null) ? null : Priority.fromValue(dbData);
    }
}
