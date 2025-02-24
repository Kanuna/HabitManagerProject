package com.example.habitmanager.repositories;

import com.example.habitmanager.models.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Integer> {
/*    Optional<List<Habit>> findByUserId(int user_id);
    Optional<List<Habit>> findByUserIdAndDate(int user_id, LocalDate date);
    Optional<List<Habit>> findByUserIdAndCategoryId(int user_id, int category_id);
    Optional<List<Habit>> findByUserAndPriority(int user_id, Habit.Priority priority);*/

    Optional<List<Habit>> findByUser_User_id(int user_id);
    Optional<List<Habit>> findByUser_User_idAndDate(int user_id, LocalDate date);
    Optional<List<Habit>> findByUser_User_idAndCategory_Category_id(int user_id, int category_id);
    Optional<List<Habit>> findByUser_User_idAndPriority(int user_id, Habit.Priority priority);
}