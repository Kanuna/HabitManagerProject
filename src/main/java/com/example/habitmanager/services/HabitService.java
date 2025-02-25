package com.example.habitmanager.services;

import com.example.habitmanager.dto.HabitDTO;
import com.example.habitmanager.dtoCreate.HabitDTOCreate;
import com.example.habitmanager.models.Habit;

import java.time.LocalDate;
import java.util.List;

public interface HabitService {
    HabitDTOCreate createHabit(int user_id, HabitDTOCreate habitDTOCreate);
    HabitDTO getHabitById(int habit_id);
    HabitDTO updateHabit(int habit_id, HabitDTO habitDTO);
    void deleteHabit(int habit_id);
    List<HabitDTO> getAllHabitsFromUser(int user_id);
    List<HabitDTO> getAllHabitsFromUserAndCategory(int user_id, int category_id);
    List<HabitDTO> getAllHabitsFromUserAndPriority(int user_id, Habit.Priority priority);
}