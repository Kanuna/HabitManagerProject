package com.example.habitmanager.mapper;

import com.example.habitmanager.dto.*;
import com.example.habitmanager.models.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelMapper {
    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);

    HabitDTO toHabitDTO(Habit habit);
    Habit toHabit(HabitDTO habitDTO);

    StatsDTO toStatsDTO(Stats stats);
    Stats toStats(StatsDTO statsDTO);

    CategoryDTO toCategoryDTO(Category category);
    Category toCategory(CategoryDTO categoryDTO);
}
