package com.example.habitmanager.dtoCreate;

import com.example.habitmanager.dto.StatsDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsDTOCreate extends StatsDTO {
    private int id;

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }
}