package com.example.habitmanager.models;

import com.example.habitmanager.mapper.PriorityConverter;
import jakarta.persistence.*;
import lombok.*;
import org.mapstruct.EnumMapping;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private int amountAWeek;

    @Enumerated(EnumType.STRING)
    private HabitType habitType;

    @Convert(converter = PriorityConverter.class)
    private Priority priority;

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

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HabitCompletion> habitCompletions;


    public enum Priority {
        LOW(1),
        MEDIUM(2),
        HIGH(3);

        private final int value;

        Priority(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Priority fromValue(int value) {
            for (Priority priority : Priority.values()) {
                if (priority.value == value) {
                    return priority;
                }
            }
            throw new IllegalArgumentException("Invalid priority value: " + value);
        }
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