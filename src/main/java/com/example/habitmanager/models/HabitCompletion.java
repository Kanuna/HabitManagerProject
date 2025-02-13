package com.example.habitmanager.models;

import com.example.habitmanager.dto.HabitCompletionDTO;
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
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private State state;


    @ManyToOne
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;


    public enum State {
        Finished,
        Not_Finished
    }
}