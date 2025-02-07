package com.example.habitmanager.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class HabitCompletion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int habitCompletion_id;

    @Column
    private LocalDate completionDate;

    @ManyToOne
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;


    public enum State {
        Finished,
        Not_Finished
    }

    public HabitCompletion(Habit habit, LocalDate completionDate) {
        this.habit = habit;
        this.completionDate = completionDate;
    }

    public HabitCompletion() {}

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