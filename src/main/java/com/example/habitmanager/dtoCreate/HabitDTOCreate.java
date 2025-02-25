package com.example.habitmanager.dtoCreate;

import com.example.habitmanager.dto.HabitDTO;
import lombok.Getter;

@Getter
public class HabitDTOCreate extends HabitDTO {
    private int id;

    public void setUser_id(int user_id) {
        if(user_id > 0){
            getUser().setId(user_id);
        }
    }
}