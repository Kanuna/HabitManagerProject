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

    @PostMapping("/createStats")
    public ResponseEntity<StatsDTOCreate> createStats(@RequestBody StatsDTOCreate statsDTOCreate) {
        StatsDTOCreate createdStats = statsServiceImp.createStatsDTO(statsDTOCreate);

        URI location = URI.create("/stats/" + createdStats.getStats_id());
        return ResponseEntity.created(location).body(createdStats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatsDTO> getStats(@PathVariable int id) {
        StatsDTO statsDTO = statsServiceImp.getStatsFromId(id);
        return ResponseEntity.ok(statsDTO);
    }

    @GetMapping("/stats/{habit_id}")
    public ResponseEntity<StatsDTO> getStatsByHabitId(@PathVariable int habit_id) {
        StatsDTO statsDTO = statsServiceImp.getStatsFromHabitId(habit_id);
        return ResponseEntity.ok(statsDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatsDTO> updateStats(@PathVariable int id, @RequestBody StatsDTO statsDTO) {
        statsServiceImp.updateStatsDTO(id, statsDTO);
        return ResponseEntity.ok(statsDTO);
    }
}