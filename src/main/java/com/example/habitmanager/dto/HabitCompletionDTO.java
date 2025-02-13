package com.example.habitmanager.dto;

import com.example.habitmanager.models.HabitCompletion;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class HabitCompletionDTO {
    private LocalDate date;
    @Setter
    @Getter
    private HabitCompletion.State state;
}