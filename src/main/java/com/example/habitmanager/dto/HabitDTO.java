package com.example.habitmanager.dto;

import com.example.habitmanager.models.*;
import lombok.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class HabitDTO {
    @NonNull
    private String title;
    private String description;

    @NonNull
    private String habitType;

    private Integer amountAWeek;
    private List<Habit.daysEnum> specificDays;
    private List<LocalDate> specificDates;

    private Stats stats;
    private Category category;
    private User user;
    private String completionStatus;

    public enum daysEnum {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

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