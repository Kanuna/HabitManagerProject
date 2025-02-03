package com.example.habitmanager.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String colorCode;

    @OneToMany(mappedBy = "category")
    private List<Habit> habits;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


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