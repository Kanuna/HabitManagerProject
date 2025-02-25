package com.example.habitmanager.dtoCreate;

import com.example.habitmanager.dto.StatsDTO;
import lombok.Getter;

@Getter
public class StatsDTOCreate extends StatsDTO {
    private int id;

    public void setHabitId(int habit_id) {
        if(habit_id > 0){
            setHabitId(habit_id);
        }
    }
}