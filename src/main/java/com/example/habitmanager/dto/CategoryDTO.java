package com.example.habitmanager.dto;

import com.example.habitmanager.models.Habit;
import com.example.habitmanager.models.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    private int category_id;
    public String name;
    public String colorCode;
    private Habit habits;
    private User user;


    public void setName(String name) {
        if(!name.isEmpty()){
            this.name = name;
        }
    }

    public void setColorCode(String colorCode) {
        if(!colorCode.isEmpty()){
            this.colorCode = colorCode;
        }
    }
}
