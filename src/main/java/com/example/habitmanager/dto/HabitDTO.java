package com.example.habitmanager.dto;

import com.example.habitmanager.models.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class HabitDTO {
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    private Habit.Priority priority;

    @NotNull(message = "Habit type is required")
    private Habit.HabitType habitType;
    private int amountAWeek;
    private List<Habit.daysEnum> specificDays;
    private List<LocalDate> specificDates;

    @Nullable
    private int statsId;
    @Nullable
    private int categoryId;
    @NotNull
    private int userId;


    public void setTitle(String title) {
        if (!title.isEmpty()) {
            this.title = title;
        }
    }

    public void setAmountAWeek(int amountAWeek) {
        if (amountAWeek > 0) {
            this.amountAWeek = amountAWeek;
        }
    }
}