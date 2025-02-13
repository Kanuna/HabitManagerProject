package com.example.habitmanager.serviceImp;

import com.example.habitmanager.ResourceNotFoundException.ResourceNotFoundException;
import com.example.habitmanager.dto.HabitCompletionDTO;
import com.example.habitmanager.dtoCreate.HabitCompletionDTOCreate;
import com.example.habitmanager.mapper.ModelMapper;
import com.example.habitmanager.models.HabitCompletion;
import com.example.habitmanager.repositories.HabitCompletionRepository;
import com.example.habitmanager.services.HabitCompletionService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HabitCompletionServiceImp implements HabitCompletionService {
    private final HabitCompletionRepository habitCompletionRepository;
    private final ModelMapper modelMapper;

    public HabitCompletionServiceImp(HabitCompletionRepository habitCompletionRepository, ModelMapper modelMapper) {
        this.habitCompletionRepository = habitCompletionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public HabitCompletionDTOCreate createHabitCompletion(int habit_id, HabitCompletionDTOCreate habitCompletionDTOCreate) {
        habitCompletionDTOCreate.setHabitId(habit_id);
        HabitCompletion habitCompletion = modelMapper.toHabitCompletion(habitCompletionDTOCreate);
        HabitCompletion habitSaved = habitCompletionRepository.save(habitCompletion);
        return modelMapper.toHabitCompletionDTOCreate(habitSaved);
    }

    @Override
    public HabitCompletionDTO updateHabitCompletion(int habitCompletion_id, HabitCompletionDTO habitCompletionDTO) {
        HabitCompletion habitCompletion = habitCompletionRepository.findById(habitCompletion_id)
                .orElseThrow(() -> new ResourceNotFoundException("HabitCompletion not found with id: " + habitCompletion_id));

        habitCompletion.setDate(habitCompletionDTO.getDate());
        habitCompletion.setState(habitCompletionDTO.getState());

        HabitCompletion habitSaved = habitCompletionRepository.save(habitCompletion);
        return modelMapper.toHabitCompletionDTO(habitSaved);
    }

    @Override
    public void deleteHabitCompletion(int habitCompletion_id) {
        try{
            habitCompletionRepository.deleteById(habitCompletion_id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("HabitCompletion not found with id: " + habitCompletion_id);
        }
    }

    @Override
    public List<HabitCompletionDTO> getCompletionsByHabitId(int habit_id) {
        List<HabitCompletion> habitCompletions = habitCompletionRepository.findByHabitId(habit_id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id" + habit_id));

        return habitCompletions.stream()
                .map(modelMapper::toHabitCompletionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HabitCompletionDTO findCompletionByDateAndHabitId(LocalDate date, int habit_id) {
        HabitCompletion habitCompletion = habitCompletionRepository.findByDateAndHabitId(date, habit_id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id: " + habit_id));

        return modelMapper.toHabitCompletionDTO(habitCompletion);
    }

    @Override
    public HabitCompletionDTO changeState(int habitCompletion_id, HabitCompletionDTO habitCompletionDTO) {
        HabitCompletion habitCompletion = habitCompletionRepository.findById(habitCompletion_id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id: " + habitCompletion_id));

        if(habitCompletion.getState() != habitCompletionDTO.getState()){
            habitCompletion.setState(habitCompletionDTO.getState());
        }
        HabitCompletion habitSaved = habitCompletionRepository.save(habitCompletion);
        return modelMapper.toHabitCompletionDTO(habitSaved);
    }
}
