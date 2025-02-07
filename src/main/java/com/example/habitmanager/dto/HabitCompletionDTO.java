package com.example.habitmanager.dto;

import com.example.habitmanager.models.Habit;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class HabitCompletionDTO {
    private LocalDate completionDate;
    private State state;
    private Habit habit;

    public HabitCompletionDTO(Habit habit, LocalDate completionDate) {
        this.habit = habit;
        this.completionDate = completionDate;
    }

    public HabitCompletionDTO() {}


    public enum State {
        Finished,
        Not_Finished
    }

    public void setCompletionDate(LocalDate completionDate) {
        if(completionDate != null) {
            this.completionDate = completionDate;
        }
    }

    public void setHabit(Habit habit) {
        if(habit != null) {
            this.habit = habit;
        }
    }
}