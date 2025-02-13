package com.example.habitmanager.dto;

import com.example.habitmanager.models.*;
import lombok.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class HabitDTO {
    private String title;
    private String description;

    private Habit.HabitType habitType;
    private int amountAWeek;
    private List<Habit.daysEnum> specificDays;
    private List<LocalDate> specificDates;

    private Stats stats;
    private Category category;
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