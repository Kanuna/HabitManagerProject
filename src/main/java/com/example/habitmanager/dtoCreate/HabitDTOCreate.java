package com.example.habitmanager.dtoCreate;

import com.example.habitmanager.dto.HabitDTO;
import lombok.Getter;

@Getter
public class HabitDTOCreate extends HabitDTO {
    private int habit_id;

    public void setUser_id(int user_id) {
        if(user_id > 0){
            getUser().setUser_id(user_id);
        }
    }
}