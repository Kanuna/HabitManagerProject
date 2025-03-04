package com.example.habitmanager.serviceImp;

import com.example.habitmanager.ResourceNotFoundException.ResourceNotFoundException;
import com.example.habitmanager.dto.StatsDTO;
import com.example.habitmanager.dtoCreate.StatsDTOCreate;
import com.example.habitmanager.mapper.ModelMapper;
import com.example.habitmanager.models.Habit;
import com.example.habitmanager.models.Stats;
import com.example.habitmanager.repositories.HabitRepository;
import com.example.habitmanager.repositories.StatsRepository;
import com.example.habitmanager.services.StatsService;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImp implements StatsService {
    private final StatsRepository statsRepository;
    private final HabitRepository habitRepository;
    private final ModelMapper modelMapper;

    public StatsServiceImp(StatsRepository statsRepository, HabitRepository habitRepository, ModelMapper modelMapper) {
        this.statsRepository = statsRepository;
        this.habitRepository = habitRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StatsDTOCreate createStats(int habit_id, StatsDTOCreate statsDTOCreate) {
        Habit habit = habitRepository.findById(habit_id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id: " + habit_id + " for Stats creation."));
        Stats stats = modelMapper.toStats(statsDTOCreate);
        stats.setHabit(habit);
        Stats savedStats = statsRepository.save(stats);
        return modelMapper.toStatsDTOCreate(savedStats);
    }

    @Override
    public StatsDTO getStatsById(int stats_id) {
        Stats stats = statsRepository.findById(stats_id)
                .orElseThrow(() -> new IllegalArgumentException("Stats not found with id: " + stats_id));
        return modelMapper.toStatsDTO(stats);
    }

    @Override
    public StatsDTO getStatsByHabitId(int habit_id) {
        Stats stats = statsRepository.findByHabit_id(habit_id)
                .orElseThrow(() -> new IllegalArgumentException("Stats not found with id: " + habit_id));
        return modelMapper.toStatsDTO(stats);
    }

    @Override
    public StatsDTO updateStats(int stats_id, StatsDTO statsDTO) {
        Stats stats = statsRepository.findById(stats_id)
                .orElseThrow(() -> new IllegalArgumentException("Stats not found with id: " + stats_id));

        stats.setFinishedTotalTimesWeek(statsDTO.getFinishedTotalTimesWeek());
        stats.setFinishedTotalTimesMonth(statsDTO.getFinishedTotalTimesMonth());
        stats.setFinishedTotalTimesYear(statsDTO.getFinishedTotalTimesYear());

        stats.setNotFinishedTotalTimesWeek(statsDTO.getNotFinishedTotalTimesWeek());
        stats.setNotFinishedTotalTimesMonth(statsDTO.getNotFinishedTotalTimesMonth());
        stats.setNotFinishedTotalTimesYear(statsDTO.getNotFinishedTotalTimesYear());

        return modelMapper.toStatsDTO(stats);
    }
}