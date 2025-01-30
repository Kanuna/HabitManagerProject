package com.example.habitmanager.dto;

import com.example.habitmanager.models.Habit;
import com.example.habitmanager.models.User;
import java.util.List;

public class CategoryDTO {
    private int category_id;
    private String name;
    private String colorCode;
    private List<Habit> habits;
    private User user;


    public int getCategory_id() {
        return category_id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(!name.isEmpty()){
            this.name = name;
        }
    }

    public String getColorCode() {
        return colorCode;
    }
    public void setColorCode(String colorCode) {
        if(!colorCode.isEmpty()){
            this.colorCode = colorCode;
        }
    }

    public List<Habit> getHabits() {
        return habits;
    }
    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }
}
