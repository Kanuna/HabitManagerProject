package com.example.habitmanager.dtoCreate;

import com.example.habitmanager.dto.HabitCompletionDTO;
import lombok.Getter;

@Getter
public class HabitCompletionDTOCreate extends HabitCompletionDTO {
    private int id;

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }
}