package com.example.habitmanager.models;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int habit_id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private State state;

    @Column(nullable = true)
    private int amountAWeek;
    @Enumerated(EnumType.STRING)
    private HabitType habitType;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<daysEnum> specificDays;

    @ElementCollection
    private List<LocalDate> specificDates;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stats_id", nullable = false)
    private Stats stats;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public enum State {
        Finished,
        Not_Finished
    }

    public enum HabitType {
        WEEKLY,
        SPECIFIC_DAYS,
        SPECIFIC_DATES
    }

    public enum daysEnum {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }


    public void setTitle(String title) {
        if(!title.isEmpty()){
            this.title = title;
        }
    }

    public void setAmountAWeek(int amountAWeek) {
        if(amountAWeek > 0){
            this.amountAWeek = amountAWeek;
        }
    }
}