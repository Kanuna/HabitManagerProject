package com.example.habitmanager.models;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    private stateEnum state;

    @Column(nullable = true)
    private int amountAWeek;

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


    public enum stateEnum {
        Finished,
        Not_Finished
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


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        if(!title.isEmpty()){
            this.title = title;
        }
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public stateEnum getState() {
        return state;
    }
    public void setState(stateEnum state) {
        this.state = state;
    }

    public int getAmountAWeek() {
        return amountAWeek;
    }
    public void setAmountAWeek(int amountAWeek) {
        if(amountAWeek > 0){
            this.amountAWeek = amountAWeek;
        }
    }

    public List<daysEnum> getSpecificDays() {
        return specificDays;
    }
    public void setSpecificDays(List<daysEnum> specificDays) {
        this.specificDays = specificDays;
    }

    public List<LocalDate> getSpecificDates() {
        return specificDates;
    }
    public void setSpecificDates(List<LocalDate> specificDates) {
        this.specificDates = specificDates;
    }
}
