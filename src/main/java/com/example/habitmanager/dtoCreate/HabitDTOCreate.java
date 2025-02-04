package com.example.habitmanager.dtoCreate;

import com.example.habitmanager.dto.HabitDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitDTOCreate extends HabitDTO {
    private int habit_id;
}