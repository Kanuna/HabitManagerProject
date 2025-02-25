package com.example.habitmanager.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String colorCode;

    @OneToMany(mappedBy = "category")
    private List<Habit> habits;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
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

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}