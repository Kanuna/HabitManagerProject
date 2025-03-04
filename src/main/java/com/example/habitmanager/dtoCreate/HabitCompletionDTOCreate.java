package com.example.habitmanager.dtoCreate;

import com.example.habitmanager.dto.HabitCompletionDTO;
import lombok.Getter;

@Getter
public class HabitCompletionDTOCreate extends HabitCompletionDTO {
    private int habitId;

    public void setHabitId(int habitId) {
        if(habitId > 0){
            this.habitId = habitId;
        }
    }
}