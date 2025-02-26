package com.example.habitmanager.dtoCreate;

import com.example.habitmanager.dto.StatsDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsDTOCreate extends StatsDTO {
    private int id;

    public void setHabitId(int habit_id) {
        if(habit_id > 0){
            getHabit().setId(habit_id);
        }
    }
}