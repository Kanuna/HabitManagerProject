package com.example.habitmanager.dtoCreate;

import com.example.habitmanager.dto.HabitDTO;
import lombok.Getter;

@Getter
public class HabitDTOCreate extends HabitDTO {
    private int id;

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }
}