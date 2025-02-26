package com.example.habitmanager.controllers;


import com.example.habitmanager.dto.StatsDTO;
import com.example.habitmanager.dtoCreate.StatsDTOCreate;
import com.example.habitmanager.models.Stats;
import com.example.habitmanager.serviceImp.StatsServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("stats")
public class StatsController {
    private final StatsServiceImp statsServiceImp;

    public StatsController(StatsServiceImp statsServiceImp) {
        this.statsServiceImp = statsServiceImp;
    }

    @PostMapping("/{habit_id}/stats")
    public ResponseEntity<StatsDTOCreate> createStats(@PathVariable int habit_id, @RequestBody StatsDTOCreate statsDTOCreate) {
        StatsDTOCreate createdStats = statsServiceImp.createStatsDTO(habit_id, statsDTOCreate);

        URI location = URI.create("/stats/" + createdStats.getId());
        return ResponseEntity.created(location).body(createdStats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatsDTO> getStats(@PathVariable int id) {
        StatsDTO statsDTO = statsServiceImp.getStatsById(id);
        return ResponseEntity.ok(statsDTO);
    }

    @GetMapping("/stats/{habit_id}")
    public ResponseEntity<StatsDTO> getStatsByHabitId(@PathVariable int habit_id) {
        StatsDTO statsDTO = statsServiceImp.getStatsByHabitId(habit_id);
        return ResponseEntity.ok(statsDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatsDTO> updateStats(@PathVariable int id, @RequestBody StatsDTO statsDTO) {
        statsServiceImp.updateStats(id, statsDTO);
        return ResponseEntity.ok(statsDTO);
    }
}