package com.example.habitmanager.dto;

import com.example.habitmanager.models.*;
import java.time.LocalDate;
import java.util.List;

public class HabitDTO {
    private String title;
    private String description;
    private Habit.stateEnum state;

    private int amountAWeek;

    private List<Habit.daysEnum> specificDays;
    private List<LocalDate> specificDates;

    private Stats stats;
    private Category category;
    private User user;


    public enum stateEnum {
        Finished,
        Not_Finished
    }

    public enum daysEnum {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        if(!title.isEmpty()){
            this.title = title;
        }
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Habit.stateEnum getState() {
        return state;
    }
    public void setState(Habit.stateEnum state) {
        this.state = state;
    }

    public int getAmountAWeek() {
        return amountAWeek;
    }
    public void setAmountAWeek(int amountAWeek) {
        if(amountAWeek > 0){
            this.amountAWeek = amountAWeek;
        }
    }

    public List<Habit.daysEnum> getSpecificDays() {
        return specificDays;
    }
    public void setSpecificDays(List<Habit.daysEnum> specificDays) {
        this.specificDays = specificDays;
    }

    public List<LocalDate> getSpecificDates() {
        return specificDates;
    }
    public void setSpecificDates(List<LocalDate> specificDates) {
        this.specificDates = specificDates;
    }
}