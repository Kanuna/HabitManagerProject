package com.example.habitmanager.dto;

import com.example.habitmanager.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private User.RoleEnum role;


    public void setFirstname(String firstname) {
        if(!firstname.isEmpty()){
            this.firstname = firstname;
        }
    }

    public void setLastname(String lastname) {
        if(!lastname.isEmpty()){
            this.lastname = lastname;
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
