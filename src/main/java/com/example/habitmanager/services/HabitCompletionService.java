package com.example.habitmanager.services;

import com.example.habitmanager.models.HabitCompletion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HabitCompletionService {
    void changeHabitCompletionStatus(int habit_id, LocalDate completionDate);
    Optional<List<HabitCompletion>> getHabitCompletionsFromHabit(int habit_id);
}