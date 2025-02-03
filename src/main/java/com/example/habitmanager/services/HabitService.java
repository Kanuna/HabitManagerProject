package com.example.habitmanager.services;

import com.example.habitmanager.dto.HabitDTO;

import java.time.LocalDate;
import java.util.List;

public interface HabitService {
    HabitDTO createHabit(HabitDTO habitDTO);
    HabitDTO getHabit(int habit_id);
    HabitDTO updateHabit(HabitDTO habitDTO);
    boolean deleteHabit(int habit_id);
    List<HabitDTO> getAllHabitsFromUser(int user_id);
    List<HabitDTO> getAllHabitsFromUserAndDate(int user_id, LocalDate date);
    List<HabitDTO> getAllHabitsFromUserAndCategory(int user_id, String category);
}