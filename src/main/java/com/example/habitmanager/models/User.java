package com.example.habitmanager.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Habit> habits;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
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