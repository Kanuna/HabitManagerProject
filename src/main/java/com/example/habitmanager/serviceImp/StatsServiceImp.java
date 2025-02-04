package com.example.habitmanager.serviceImp;

import com.example.habitmanager.dto.StatsDTO;
import com.example.habitmanager.dtoCreate.StatsDTOCreate;
import com.example.habitmanager.mapper.ModelMapper;
import com.example.habitmanager.models.Stats;
import com.example.habitmanager.repositories.StatsRepository;
import com.example.habitmanager.services.StatsService;
import jakarta.persistence.Column;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class StatsServiceImp implements StatsService {
    private final StatsRepository statsRepository;
    private final ModelMapper modelMapper;

    public StatsServiceImp(StatsRepository statsRepository, ModelMapper modelMapper) {
        this.statsRepository = statsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StatsDTOCreate createStatsDTO(StatsDTOCreate statsDTOCreate) {
        Stats stats = modelMapper.toStats(statsDTOCreate);
        Stats savedStats = statsRepository.save(stats);
        return modelMapper.toStatsDTOCreate(savedStats);
    }

    @Override
    public StatsDTO getStatsFromId(int stats_id) {
        Stats stats = statsRepository.findById(stats_id)
                .orElseThrow(() -> new IllegalArgumentException("Stats not found with id: " + stats_id));
        return modelMapper.toStatsDTO(stats);
    }

    @Override
    public StatsDTO getStatsFromHabitId(int habit_id) {
        Stats stats = statsRepository.findByHabitId(habit_id)
                .orElseThrow(() -> new IllegalArgumentException("Stats not found with id: " + habit_id));
        return modelMapper.toStatsDTO(stats);
    }

    @Override
    public StatsDTO updateStatsDTO(int stats_id, StatsDTO statsDTO) {
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