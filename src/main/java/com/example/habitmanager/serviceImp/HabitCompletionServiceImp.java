package com.example.habitmanager.serviceImp;

import com.example.habitmanager.ResourceNotFoundException.ResourceNotFoundException;
import com.example.habitmanager.models.Habit;
import com.example.habitmanager.models.HabitCompletion;
import com.example.habitmanager.repositories.HabitCompletionRepository;
import com.example.habitmanager.repositories.HabitRepository;
import com.example.habitmanager.services.HabitCompletionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HabitCompletionServiceImp implements HabitCompletionService {
    private final HabitRepository habitRepository;
    private final HabitCompletionRepository habitCompletionRepository;

    public HabitCompletionServiceImp(HabitRepository habitRepository, HabitCompletionRepository habitCompletionRepository) {
        this.habitRepository = habitRepository;
        this.habitCompletionRepository = habitCompletionRepository;
    }

    @Override
    public void changeHabitCompletionStatus(int habit_id, LocalDate completionDate) {
        Habit habit = habitRepository.findById(habit_id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id: " + habit_id));

        Optional<HabitCompletion> completionOptional = habitCompletionRepository.findByHabitAndCompletiondate(habit, completionDate)
                .flatMap(completions -> completions.stream().findFirst());

        if (completionOptional.isEmpty()) {
            throw new ResourceNotFoundException("No completion record found for the specified date.");
        }

        HabitCompletion completion = completionOptional.get();

        if (completion.getState() == HabitCompletion.State.Finished) {
            completion.setState(HabitCompletion.State.Not_Finished);
            habitCompletionRepository.save(completion);
        } else if (completion.getState() == HabitCompletion.State.Not_Finished) {
            completion.setState(HabitCompletion.State.Finished);
        }
        else {
            throw new IllegalStateException("Habit completion is already marked as NOT_FINISHED.");
        }
        habitCompletionRepository.save(completion);
    }


    @Override
    public Optional<List<HabitCompletion>> getHabitCompletionsFromHabit(int habit_id) {
        Habit habit = habitRepository.findById(habit_id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id: " + habit_id));
        return habitCompletionRepository.findByHabit(habit);
    }
}