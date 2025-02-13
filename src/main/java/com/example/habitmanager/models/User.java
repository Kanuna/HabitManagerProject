package com.example.habitmanager.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private RoleEnum role;

    public enum RoleEnum {
        ADMIN, USER
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Habit> habits;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories;


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