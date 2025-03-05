package com.example.habitmanager.controllers;

import com.example.habitmanager.dto.HabitCompletionDTO;
import com.example.habitmanager.dtoCreate.HabitCompletionDTOCreate;
import com.example.habitmanager.serviceImp.HabitCompletionServiceImp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("completion")
public class HabitCompletionController {
    private final HabitCompletionServiceImp habitCompletionServiceImp;

    public HabitCompletionController(HabitCompletionServiceImp habitCompletionServiceImp) {
        this.habitCompletionServiceImp = habitCompletionServiceImp;
    }

    @PostMapping("/{habit_id}/completions")
    public ResponseEntity<HabitCompletionDTOCreate> createHabit(@PathVariable int habit_id, @RequestBody HabitCompletionDTOCreate habitCompletionDTOCreate) {
        HabitCompletionDTOCreate createdHabitCompletion = habitCompletionServiceImp.createHabitCompletion(habit_id, habitCompletionDTOCreate);

        URI location = URI.create("/completion/" + createdHabitCompletion.getId());
        return ResponseEntity.created(location).body(createdHabitCompletion);
    }


    @PatchMapping("/completions/{completion_id}")
    public ResponseEntity<HabitCompletionDTO> changeCompletionState(
            @PathVariable int completion_id, @RequestBody HabitCompletionDTOCreate habitCompletionDTOCreate) {

        HabitCompletionDTO habitCompletionDTO = habitCompletionServiceImp.changeState(completion_id, habitCompletionDTOCreate);
        return ResponseEntity.ok(habitCompletionDTO);
    }


    @GetMapping("/habits/{habit_id}/completions/{date}")
    public ResponseEntity<HabitCompletionDTO> getHabitCompletion(
            @PathVariable int habit_id,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        HabitCompletionDTO habitCompletion = habitCompletionServiceImp.findCompletionByDateAndHabitId(date, habit_id);
        return ResponseEntity.ok(habitCompletion);
    }

    @GetMapping("/habits/{habit_id}/completions")
    public ResponseEntity<List<HabitCompletionDTO>> getHabitCompletions(@PathVariable int habit_id) {
        List<HabitCompletionDTO> completions = habitCompletionServiceImp.getCompletionsByHabitId(habit_id);
        return ResponseEntity.ok(completions);
    }

    @PutMapping("/{completion_id}")
    public ResponseEntity<HabitCompletionDTO> updateCompletion(
            @PathVariable int completion_id, @RequestBody HabitCompletionDTOCreate habitCompletionDTOCreate) {
        habitCompletionServiceImp.updateHabitCompletion(completion_id, habitCompletionDTOCreate);
        return ResponseEntity.ok(habitCompletionDTOCreate);
    }

    @DeleteMapping("/{completion_id}")
    public ResponseEntity<Void> deleteHabitCompletion(@PathVariable int completion_id) {
        habitCompletionServiceImp.deleteHabitCompletion(completion_id);
        return ResponseEntity.noContent().build();
    }
}