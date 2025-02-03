package com.example.habitmanager.repositories;

import com.example.habitmanager.models.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<Habit, Integer> {
}
