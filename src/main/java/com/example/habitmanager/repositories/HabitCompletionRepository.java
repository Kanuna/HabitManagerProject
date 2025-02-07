package com.example.habitmanager.repositories;

import com.example.habitmanager.models.Habit;
import com.example.habitmanager.models.HabitCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HabitCompletionRepository extends JpaRepository<HabitCompletion, Integer> {
    Optional<List<HabitCompletion>> findByHabitAndCompletiondate(Habit habit, LocalDate completionDate);
    Optional<List<HabitCompletion>> findByHabit(Habit habit);
}