package com.example.habitmanager.dto;

import com.example.habitmanager.models.Category;
import com.example.habitmanager.models.Habit;
import java.util.List;

public class UserDTO {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private List<Habit> habits;
    private List<Category> categories;


    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        if(!first_name.isEmpty()){
            this.first_name = first_name;
        }
    }

    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        if(!last_name.isEmpty()){
            this.last_name = last_name;
        }
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        if(!email.isEmpty()){
            this.email = email;
        }
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        if(!password.isEmpty()){
            this.password = password;
        }
    }

    public List<Habit> getHabits() {
        return habits;
    }
    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }

    public List<Category> getCategories() {
        return categories;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
