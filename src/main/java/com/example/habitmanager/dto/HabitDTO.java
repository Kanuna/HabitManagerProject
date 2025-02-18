package com.example.habitmanager.dto;

import com.example.habitmanager.models.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class HabitDTO {
    @NotBlank(message = "Title is required")
    private String title;
    private String description;

    @NotNull(message = "Habit type is required")
    private Habit.HabitType habitType;
    private int amountAWeek;
    private List<Habit.daysEnum> specificDays;
    private List<LocalDate> specificDates;

    @NotNull
    private Stats stats;
    private Category category;
    @NotNull
    private User user;


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