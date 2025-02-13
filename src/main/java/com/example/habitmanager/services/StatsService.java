package com.example.habitmanager.services;

import com.example.habitmanager.dto.StatsDTO;
import com.example.habitmanager.dtoCreate.StatsDTOCreate;

public interface StatsService {
    StatsDTOCreate createStatsDTO(int habit_id, StatsDTOCreate statsDTOCreate);
    StatsDTO getStatsFromId(int stats_id);
    StatsDTO getStatsFromHabitId(int habit_id);
    StatsDTO updateStatsDTO(int stats_id, StatsDTO statsDTO);
}