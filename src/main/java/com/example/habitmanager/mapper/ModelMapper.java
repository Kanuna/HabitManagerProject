package com.example.habitmanager.mapper;

import com.example.habitmanager.dto.*;
import com.example.habitmanager.dtoCreate.*;
import com.example.habitmanager.models.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    default UserDTO toUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        userDTO.setAge(user.getAge());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
    default User toUser(UserDTOCreate userDTOCreate){
        User user = new User();
        user.setFirstname(userDTOCreate.getFirstname());
        user.setLastname(userDTOCreate.getLastname());
        user.setAge(userDTOCreate.getAge());
        user.setEmail(userDTOCreate.getEmail());
        user.setPassword(userDTOCreate.getPassword());
        user.setRole(userDTOCreate.getRole());
        return user;
    }
    default UserDTOCreate toUserDTOCreate(User user){
        UserDTOCreate userDTOCreate = new UserDTOCreate();
        userDTOCreate.setId(user.getId());
        userDTOCreate.setFirstname(user.getFirstname());
        userDTOCreate.setLastname(user.getLastname());
        userDTOCreate.setAge(user.getAge());
        userDTOCreate.setEmail(user.getEmail());
        userDTOCreate.setPassword(user.getPassword());
        userDTOCreate.setRole(user.getRole());
        return userDTOCreate;
    }

    default HabitDTO toHabitDTO(Habit habit){
        HabitDTO habitDTO = new HabitDTO();
        habitDTO.setTitle(habit.getTitle());
        habitDTO.setDescription(habit.getDescription());
        habitDTO.setAmountAWeek(habit.getAmountAWeek());
        habitDTO.setHabitType(habit.getHabitType());
        habitDTO.setPriority(habit.getPriority());
        habitDTO.setStats(habit.getStats());
        habitDTO.setCategory(habit.getCategory());
        habitDTO.setUser(habit.getUser());
        return habitDTO;
    }
    default Habit toHabit(HabitDTO habitDTO){
        Habit habit = new Habit();
        habit.setTitle(habitDTO.getTitle());
        habit.setDescription(habitDTO.getDescription());
        habit.setAmountAWeek(habitDTO.getAmountAWeek());
        habit.setHabitType(habitDTO.getHabitType());
        habit.setPriority(habitDTO.getPriority());
        habit.setStats(habitDTO.getStats());
        habit.setCategory(habitDTO.getCategory());
        habit.setUser(habitDTO.getUser());
        return habit;
    }
    default HabitDTOCreate toHabitDTOCreate(Habit habit){
        HabitDTOCreate habitDTOCreate = new HabitDTOCreate();
        habitDTOCreate.setId(habit.getId());
        habitDTOCreate.setTitle(habit.getTitle());
        habitDTOCreate.setDescription(habit.getDescription());
        habitDTOCreate.setAmountAWeek(habit.getAmountAWeek());
        habitDTOCreate.setHabitType(habit.getHabitType());
        habitDTOCreate.setPriority(habit.getPriority());
        habitDTOCreate.setStats(habit.getStats());
        habitDTOCreate.setCategory(habit.getCategory());
        habitDTOCreate.setUser(habit.getUser());
        return habitDTOCreate;
    }

    default HabitCompletionDTO toHabitCompletionDTO(HabitCompletion habitCompletion){
        HabitCompletionDTO habitCompletionDTO = new HabitCompletionDTO();
        habitCompletionDTO.setDate(habitCompletion.getDate());
        habitCompletionDTO.setState(habitCompletion.getState());
        habitCompletionDTO.setHabit(habitCompletion.getHabit());
        return habitCompletionDTO;
    }
    default HabitCompletion toHabitCompletion(HabitCompletionDTO habitCompletionDTO){
        HabitCompletion habitCompletion = new HabitCompletion();
        habitCompletion.setDate(habitCompletionDTO.getDate());
        habitCompletion.setState(habitCompletionDTO.getState());
        habitCompletion.setHabit(habitCompletionDTO.getHabit());
        return habitCompletion;
    }
    default HabitCompletionDTOCreate toHabitCompletionDTOCreate(HabitCompletion habitCompletion){
        HabitCompletionDTOCreate habitCompletionDTOCreate = new HabitCompletionDTOCreate();
        habitCompletionDTOCreate.setId(habitCompletion.getId());
        habitCompletionDTOCreate.setHabit(habitCompletion.getHabit());
        habitCompletionDTOCreate.setState(habitCompletion.getState());
        habitCompletionDTOCreate.setDate(habitCompletion.getDate());
        return habitCompletionDTOCreate;
    }

    default StatsDTO toStatsDTO(Stats stats){
        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setHabit(stats.getHabit());
        statsDTO.setFinishedTotalTimesWeek(stats.getFinishedTotalTimesWeek());
        statsDTO.setFinishedTotalTimesMonth(stats.getFinishedTotalTimesMonth());
        statsDTO.setFinishedTotalTimesYear(stats.getFinishedTotalTimesYear());
        statsDTO.setNotFinishedTotalTimesWeek(stats.getNotFinishedTotalTimesWeek());
        statsDTO.setNotFinishedTotalTimesMonth(stats.getNotFinishedTotalTimesMonth());
        statsDTO.setNotFinishedTotalTimesYear(stats.getNotFinishedTotalTimesYear());
        return statsDTO;
    }
    default Stats toStats(StatsDTO statsDTO){
        Stats stats = new Stats();
        stats.setHabit(statsDTO.getHabit());
        stats.setFinishedTotalTimesWeek(statsDTO.getFinishedTotalTimesWeek());
        stats.setFinishedTotalTimesMonth(statsDTO.getFinishedTotalTimesMonth());
        stats.setFinishedTotalTimesYear(statsDTO.getFinishedTotalTimesYear());
        stats.setNotFinishedTotalTimesWeek(statsDTO.getNotFinishedTotalTimesWeek());
        stats.setNotFinishedTotalTimesMonth(statsDTO.getNotFinishedTotalTimesMonth());
        stats.setNotFinishedTotalTimesYear(statsDTO.getNotFinishedTotalTimesYear());
        return stats;
    }
    default StatsDTOCreate toStatsDTOCreate(Stats stats){
        StatsDTOCreate statsDTOCreate = new StatsDTOCreate();
        statsDTOCreate.setHabit(stats.getHabit());
        statsDTOCreate.setFinishedTotalTimesWeek(stats.getFinishedTotalTimesWeek());
        statsDTOCreate.setFinishedTotalTimesMonth(stats.getFinishedTotalTimesMonth());
        statsDTOCreate.setFinishedTotalTimesYear(stats.getFinishedTotalTimesYear());
        statsDTOCreate.setNotFinishedTotalTimesWeek(stats.getNotFinishedTotalTimesWeek());
        statsDTOCreate.setNotFinishedTotalTimesMonth(stats.getNotFinishedTotalTimesMonth());
        statsDTOCreate.setNotFinishedTotalTimesYear(stats.getNotFinishedTotalTimesYear());
        return statsDTOCreate;
    }

    default CategoryDTO toCategoryDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setColorCode(category.getColorCode());
        categoryDTO.setHabits(category.getHabits());
        categoryDTO.setUser(category.getUser());
        return categoryDTO;
    }
    default Category toCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setColorCode(categoryDTO.getColorCode());
        category.setHabits(categoryDTO.getHabits());
        category.setUser(categoryDTO.getUser());
        return category;
    }
    default CategoryDTOCreate toCategoryDTOCreate(Category category){
        CategoryDTOCreate categoryDTOCreate = new CategoryDTOCreate();
        categoryDTOCreate.setId(category.getId());
        categoryDTOCreate.setName(category.getName());
        categoryDTOCreate.setColorCode(category.getColorCode());
        categoryDTOCreate.setHabits(category.getHabits());
        categoryDTOCreate.setUser(category.getUser());
        return categoryDTOCreate;
    }
}
