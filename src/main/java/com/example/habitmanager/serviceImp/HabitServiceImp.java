package com.example.habitmanager.serviceImp;

import com.example.habitmanager.ResourceNotFoundException.ResourceNotFoundException;
import com.example.habitmanager.dto.HabitDTO;
import com.example.habitmanager.dtoCreate.HabitDTOCreate;
import com.example.habitmanager.mapper.ModelMapperOld;
import com.example.habitmanager.models.Habit;
import com.example.habitmanager.repositories.HabitRepository;
import com.example.habitmanager.services.HabitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HabitServiceImp implements HabitService {
    private final HabitRepository habitRepository;
    private final ModelMapperOld modelMapperOld;

    public HabitServiceImp(HabitRepository habitRepository, ModelMapperOld modelMapperOld) {
        this.habitRepository = habitRepository;
        this.modelMapperOld = modelMapperOld;
    }

    @Override
    public HabitDTOCreate createHabit(int user_id, HabitDTOCreate habitDTOCreate) {
        habitDTOCreate.setUser_id(user_id);
        Habit habit = modelMapperOld.toHabit(habitDTOCreate);
        Habit savedHabit = habitRepository.save(habit);
        return modelMapperOld.toHabitDTOCreate(savedHabit);
    }

    @Override
    public HabitDTO getHabitById(int habit_id) {
        Habit habit = habitRepository.findById(habit_id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id: " + habit_id));
        return modelMapperOld.toHabitDTO(habit);
    }

    @Override
    public HabitDTO updateHabit(int habit_id, HabitDTO habitDTO) {
        Habit habit = habitRepository.findById(habit_id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id: " + habit_id));

        habit.setTitle(habitDTO.getTitle());
        habit.setDescription(habitDTO.getDescription());
        habit.setHabitType(habitDTO.getHabitType());
        habit.setAmountAWeek(habitDTO.getAmountAWeek());
        habit.setSpecificDays(habitDTO.getSpecificDays());
        habit.setSpecificDates(habitDTO.getSpecificDates());

        Habit updatedHabit = habitRepository.save(habit);
        return modelMapperOld.toHabitDTO(updatedHabit);
    }

    @Override
    public void deleteHabit(int habit_id) {
        Habit habit = habitRepository.findById(habit_id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id: " + habit_id));
        habitRepository.delete(habit);
    }

    @Override
    public List<HabitDTO> getAllHabitsFromUser(int user_id) {
        List<Habit> habits = habitRepository.findByUser_id(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user_id));

        return habits.stream()
                .map(modelMapperOld::toHabitDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<HabitDTO> getAllHabitsFromUserAndCategory(int user_id, int category_id) {
        List<Habit> habits = habitRepository.findByUser_idAndCategory_id(user_id, category_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user_id));

        return habits.stream()
                .map(modelMapperOld::toHabitDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<HabitDTO> getAllHabitsFromUserAndPriority(int user_id, Habit.Priority priority) {
        List<Habit> habits = habitRepository.findByUser_idAndPriority(user_id, priority)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user_id));

        return habits.stream()
                .map(modelMapperOld::toHabitDTO)
                .collect(Collectors.toList());
    }
}