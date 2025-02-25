package com.example.habitmanager.repositories;

import com.example.habitmanager.models.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Integer> {
    Optional<List<Habit>> findByUser_id(int user_id);
    Optional<List<Habit>> findByUser_idAndCategory_id(int user_id, int category_id);
    Optional<List<Habit>> findByUser_idAndPriority(int user_id, Habit.Priority priority);
}