package com.example.habitmanager.dtoCreate;

import com.example.habitmanager.dto.CategoryDTO;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
public class CategoryDTOCreate extends CategoryDTO {
    private int category_id;

    public void setUser_id(int user_id) {
        if(user_id > 0){
            setUser_id(user_id);
        }
    }
}