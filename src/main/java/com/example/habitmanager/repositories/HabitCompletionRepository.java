package com.example.habitmanager.repositories;

import com.example.habitmanager.models.HabitCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Repository
public interface HabitCompletionRepository extends JpaRepository<HabitCompletion, Integer> {
    Optional<HabitCompletion> findByDateAndHabitId(LocalDate date, int habit_id);
    Optional<List<HabitCompletion>> findByHabitId(int habit_id);
}