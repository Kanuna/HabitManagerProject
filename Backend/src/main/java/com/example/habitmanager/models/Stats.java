package com.example.habitmanager.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(mappedBy = "stats")
    private Habit habit;
    @Column(nullable = true)
    private int finishedTotalTimesWeek;
    @Column(nullable = true)
    private int finishedTotalTimesMonth;
    @Column(nullable = true)
    private int finishedTotalTimesYear;
    @Transient
    public int getFinishedTotalTimesAll(){
        return finishedTotalTimesWeek + finishedTotalTimesMonth + finishedTotalTimesYear;
    }

    @Column(nullable = true)
    private int notFinishedTotalTimesWeek;
    @Column(nullable = true)
    private int notFinishedTotalTimesMonth;
    @Column(nullable = true)
    private int notFinishedTotalTimesYear;
    @Transient
    public int getNotFinishedTotalTimesAll(){
        return notFinishedTotalTimesWeek + notFinishedTotalTimesMonth + notFinishedTotalTimesYear;
    }

    @Transient
    public int getSuccessRate() {
        int finishedTotal = getFinishedTotalTimesAll();
        int notFinishedTotal = getNotFinishedTotalTimesAll();
        if (finishedTotal + notFinishedTotal == 0) {
            return 0;
        }
        return (finishedTotal * 100) / (finishedTotal + notFinishedTotal);
    }
}
