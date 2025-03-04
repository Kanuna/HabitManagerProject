package com.example.habitmanager.serviceImp;

import com.example.habitmanager.dto.StatsDTO;
import com.example.habitmanager.dtoCreate.StatsDTOCreate;
import com.example.habitmanager.mapper.ModelMapperOld;
import com.example.habitmanager.models.Stats;
import com.example.habitmanager.repositories.StatsRepository;
import com.example.habitmanager.services.StatsService;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImp implements StatsService {
    private final StatsRepository statsRepository;
    private final ModelMapperOld modelMapperOld;

    public StatsServiceImp(StatsRepository statsRepository, ModelMapperOld modelMapperOld) {
        this.statsRepository = statsRepository;
        this.modelMapperOld = modelMapperOld;
    }

    @Override
    public StatsDTOCreate createStatsDTO(int habit_id, StatsDTOCreate statsDTOCreate) {
        statsDTOCreate.setHabitId(habit_id);
        Stats stats = modelMapperOld.toStats(statsDTOCreate);
        Stats savedStats = statsRepository.save(stats);
        return modelMapperOld.toStatsDTOCreate(savedStats);
    }

    @Override
    public StatsDTO getStatsById(int stats_id) {
        Stats stats = statsRepository.findById(stats_id)
                .orElseThrow(() -> new IllegalArgumentException("Stats not found with id: " + stats_id));
        return modelMapperOld.toStatsDTO(stats);
    }

    @Override
    public StatsDTO getStatsByHabitId(int habit_id) {
        Stats stats = statsRepository.findByHabit_id(habit_id)
                .orElseThrow(() -> new IllegalArgumentException("Stats not found with id: " + habit_id));
        return modelMapperOld.toStatsDTO(stats);
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

        return modelMapperOld.toStatsDTO(stats);
    }
}