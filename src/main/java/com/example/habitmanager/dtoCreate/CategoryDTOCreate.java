package com.example.habitmanager.dtoCreate;

import com.example.habitmanager.dto.CategoryDTO;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
public class CategoryDTOCreate extends CategoryDTO {
    private int id;

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }
}