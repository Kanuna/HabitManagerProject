package com.example.habitmanager.services;

import com.example.habitmanager.dto.HabitDTO;
import com.example.habitmanager.dtoCreate.HabitDTOCreate;
import com.example.habitmanager.models.Habit;

import java.time.LocalDate;
import java.util.List;

public interface HabitService {
    HabitDTOCreate createHabit(HabitDTOCreate habitDTOCreate);
    HabitDTO getHabit(int habit_id);
    HabitDTO updateHabit(int habit_id, HabitDTO habitDTO);
    void deleteHabit(int habit_id);
    List<HabitDTO> getAllHabitsFromUser(int user_id);
    List<HabitDTO> getAllHabitsFromUserAndDate(int user_id, LocalDate date);
    List<Habit> getAllHabitsToday(int user_id);
    List<HabitDTO> getAllHabitsFromUserAndCategory(int user_id, String category);
    List<HabitDTO> getAllHabitsFromPriority(int user_id, int priority);


}