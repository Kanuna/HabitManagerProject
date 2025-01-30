package com.example.habitmanager.mapper;

import com.example.habitmanager.dto.*;
import com.example.habitmanager.models.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);

    HabitDTO habitToHabitDTO(Habit habit);
    Habit habitDTOToHabit(HabitDTO habitDTO);

    StatsDTO statsToStatsDTO(Stats stats);
    Stats statsDTOToStats(StatsDTO statsDTO);

    CategoryDTO categoryToCategoryDTO(Category category);
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
