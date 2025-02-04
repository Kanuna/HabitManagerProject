package com.example.habitmanager.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @Column(nullable = false)
    private String first_name;
    @Column(nullable = false)
    private String last_name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private RoleEnum role;

    public enum RoleEnum {
        ADMIN, USER
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Habit> habits;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Category> categories;


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