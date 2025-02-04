package com.example.habitmanager.dto;

import com.example.habitmanager.models.Category;
import com.example.habitmanager.models.Habit;
import com.example.habitmanager.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private User.RoleEnum role;


    public void setFirst_name(String first_name) {
        if(!first_name.isEmpty()){
            this.first_name = first_name;
        }
    }

    public void setLast_name(String last_name) {
        if(!last_name.isEmpty()){
            this.last_name = last_name;
        }
    }

    public void setEmail(String email) {
        if(!email.isEmpty()){
            this.email = email;
        }
    }

    public void setPassword(String password) {
        if(!password.isEmpty()){
            this.password = password;
        }
    }
}
