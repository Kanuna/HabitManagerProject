package com.example.habitmanager.services;

import com.example.habitmanager.dto.HabitCompletionDTO;
import com.example.habitmanager.dtoCreate.HabitCompletionDTOCreate;

import java.time.LocalDate;
import java.util.List;


public interface HabitCompletionService {
    HabitCompletionDTOCreate createHabitCompletion(int habit_id, HabitCompletionDTOCreate habitCompletionDTOCreate);
    HabitCompletionDTO updateHabitCompletion(int habitCompletion_id, HabitCompletionDTO habitCompletionDTO);
    void deleteHabitCompletion(int habitCompletion_id);
    List<HabitCompletionDTO> getCompletionsByHabitId(int habit_id);
    HabitCompletionDTO findCompletionByDateAndHabitId(LocalDate date, int habit_id);
    HabitCompletionDTO changeState(int habitCompletion_id, HabitCompletionDTO habitCompletionDTO);
}