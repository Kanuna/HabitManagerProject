package com.example.habitmanager.dto;

import com.example.habitmanager.models.HabitCompletion;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class HabitCompletionDTO {
    @NotNull
    private LocalDate date;
    @NotNull
    private HabitCompletion.State state;
}