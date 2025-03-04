package com.example.habitmanager.mapper;

import com.example.habitmanager.dto.*;
import com.example.habitmanager.dtoCreate.*;
import com.example.habitmanager.models.*;

public interface ModelMapperOld {
    UserDTO toUserDTO(User user);
    User toUser(UserDTOCreate userDTOCreate);
    UserDTOCreate toUserDTOCreate(User user);

    HabitDTO toHabitDTO(Habit habit);
    Habit toHabit(HabitDTO habitDTO);
    HabitDTOCreate toHabitDTOCreate(Habit habit);

    HabitCompletionDTO toHabitCompletionDTO(HabitCompletion habitCompletion);
    HabitCompletion toHabitCompletion(HabitCompletionDTO HabitCompletionDTO);
    HabitCompletionDTOCreate toHabitCompletionDTOCreate(HabitCompletion habitCompletion);

    StatsDTO toStatsDTO(Stats stats);
    Stats toStats(StatsDTO statsDTO);
    StatsDTOCreate toStatsDTOCreate(Stats stats);

    CategoryDTO toCategoryDTO(Category category);
    Category toCategory(CategoryDTO categoryDTO);
    CategoryDTOCreate toCategoryDTOCreate(Category category);
}
