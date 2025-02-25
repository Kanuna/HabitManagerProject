package com.example.habitmanager.controllers;

import com.example.habitmanager.dto.HabitDTO;
import com.example.habitmanager.dtoCreate.HabitDTOCreate;
import com.example.habitmanager.models.Habit;
import com.example.habitmanager.serviceImp.HabitServiceImp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("habit")
public class HabitController {
    private final HabitServiceImp habitServiceImp;

    public HabitController(HabitServiceImp habitServiceImp) {
        this.habitServiceImp = habitServiceImp;
    }


    @PostMapping("/{user_id}/habits")
    public ResponseEntity<HabitDTOCreate> createHabit(@PathVariable int user_id, @RequestBody HabitDTOCreate habitDTOCreate) {
        HabitDTOCreate createdHabit = habitServiceImp.createHabit(user_id, habitDTOCreate);

        URI location = URI.create("/habit/" + createdHabit.getId());
        return ResponseEntity.created(location).body(createdHabit);
    }


    @GetMapping("/{id}")
    public ResponseEntity<HabitDTO> getHabitById(@PathVariable int id) {
        HabitDTO habitDTO = habitServiceImp.getHabitById(id);
        return ResponseEntity.ok(habitDTO);
    }


    @GetMapping("/users/{user_id}/habits")
    public ResponseEntity<List<HabitDTO>> getHabitsByUserId(@PathVariable int user_id) {
        List<HabitDTO> habits = habitServiceImp.getAllHabitsFromUser(user_id);
        return ResponseEntity.ok(habits);
    }


    @GetMapping("/users/{user_id}/habits/{category_id}")
    public ResponseEntity<List<HabitDTO>> getHabitsByCategoryId(@PathVariable int user_id, @PathVariable int category_id) {
        List<HabitDTO> habits = habitServiceImp.getAllHabitsFromUserAndCategory(user_id, category_id);
        return ResponseEntity.ok(habits);
    }


    @GetMapping("/users/{user_id}/habits/{priority}")
    public ResponseEntity<List<HabitDTO>> getHabitsByPriority(@PathVariable int user_id, @PathVariable Habit.Priority priority) {
        List<HabitDTO> habits = habitServiceImp.getAllHabitsFromUserAndPriority(user_id, Habit.Priority.LOW);
        return ResponseEntity.ok(habits);
    }


    @PutMapping("/{id}")
    public ResponseEntity<HabitDTO> updateHabit(@PathVariable int id, @RequestBody HabitDTOCreate habitDTOCreate) {
        habitServiceImp.updateHabit(id, habitDTOCreate);
        return ResponseEntity.ok(habitDTOCreate);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable int id) {
        habitServiceImp.deleteHabit(id);
        return ResponseEntity.noContent().build();
    }
}