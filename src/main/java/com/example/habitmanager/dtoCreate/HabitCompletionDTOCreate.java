package com.example.habitmanager.dtoCreate;

import com.example.habitmanager.dto.HabitCompletionDTO;
import lombok.Getter;

@Getter
public class HabitCompletionDTOCreate extends HabitCompletionDTO {
    private int habitCompletion_id;

    public void setHabitId(int habit_id) {
        if(habit_id > 0){
            setHabitId(habit_id);
        }
    }
}