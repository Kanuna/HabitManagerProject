package com.example.habitmanager.serviceImp;

import com.example.habitmanager.ResourceNotFoundException.ResourceNotFoundException;
import com.example.habitmanager.dto.HabitDTO;
import com.example.habitmanager.dtoCreate.HabitDTOCreate;
import com.example.habitmanager.mapper.ModelMapper;
import com.example.habitmanager.models.Habit;
import com.example.habitmanager.repositories.HabitRepository;
import com.example.habitmanager.services.HabitService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HabitServiceImp implements HabitService {
    private final HabitRepository habitRepository;
    private final ModelMapper modelMapper;

    public HabitServiceImp(HabitRepository habitRepository, ModelMapper modelMapper) {
        this.habitRepository = habitRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public HabitDTOCreate createHabit(HabitDTOCreate habitDTOCreate) {
        Habit habit = modelMapper.toHabit(habitDTOCreate);
        Habit savedHabit = habitRepository.save(habit);
        return modelMapper.toHabitDTOCreate(savedHabit);
    }


    @Override
    public HabitDTO getHabit(int habit_id) {
        Habit habit = habitRepository.findById(habit_id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id: " + habit_id));
        return modelMapper.toHabitDTO(habit);
    }


    @Override
    public HabitDTO updateHabit(int habit_id, HabitDTO habitDTO) {
        Habit habit = habitRepository.findById(habit_id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id: " + habit_id));

        habit.setTitle(habitDTO.getTitle());
        habit.setDescription(habitDTO.getDescription());
        habit.setAmountAWeek(habitDTO.getAmountAWeek());
        habit.setSpecificDays(habitDTO.getSpecificDays());
        habit.setSpecificDates(habitDTO.getSpecificDates());

        Habit updatedHabit = habitRepository.save(habit);
        return modelMapper.toHabitDTO(updatedHabit);
    }


    @Override
    public void deleteHabit(int habit_id) {
        Habit habit = habitRepository.findById(habit_id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id: " + habit_id));
        habitRepository.delete(habit);
    }


    @Override
    public List<HabitDTO> getAllHabitsFromUser(int user_id) {
        List<Habit> habits = habitRepository.findByUserId(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user_id));

        return habits.stream()
                .map(modelMapper::toHabitDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<HabitDTO> getAllHabitsFromUserAndDate(int user_id, LocalDate date) {
        List<Habit> habits = habitRepository.findByUserIdAndDate(user_id, date)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user_id));

        return habits.stream()
                .map(modelMapper::toHabitDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<Habit> getAllHabitsToday(int user_id) {
        LocalDate today = LocalDate.now();
        DayOfWeek todayDayOfWeek = today.getDayOfWeek();

        List<Habit> habits = habitRepository.findByUserId(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user_id));

        return habits.stream()
                .filter(habit -> shouldDisplayHabitToday(habit, today, todayDayOfWeek))
                .collect(Collectors.toList());
    }


    private boolean shouldDisplayHabitToday(Habit habit, LocalDate today, DayOfWeek todayDayOfWeek) {
        return switch (habit.getHabitType()) {
            case WEEKLY -> true;
            case SPECIFIC_DAYS -> habit.getSpecificDays().contains(Habit.daysEnum.valueOf(todayDayOfWeek.name()));
            case SPECIFIC_DATES -> habit.getSpecificDates().contains(today);
            default -> false;
        };
    }


    @Override
    public List<HabitDTO> getAllHabitsFromUserAndCategory(int user_id, String category) {
        List<Habit> habits = habitRepository.findByUserAndCategory(user_id, category)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user_id));

        return habits.stream()
                .map(modelMapper::toHabitDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<HabitDTO> getAllHabitsFromPriority(int user_id, int priority) {
        List<Habit> habits = habitRepository.findByUserAndPriority(user_id, priority)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user_id));

        return habits.stream()
                .map(modelMapper::toHabitDTO)
                .collect(Collectors.toList());
    }
}