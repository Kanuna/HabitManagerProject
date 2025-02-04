package com.example.habitmanager.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StatsDTO {
    private int finishedTotalTimesWeek;
    private int finishedTotalTimesMonth;
    private int finishedTotalTimesYear;

    public int getFinishedTotalTimesAll(){
        return finishedTotalTimesWeek + finishedTotalTimesMonth + finishedTotalTimesYear;
    }

    private int notFinishedTotalTimesWeek;
    private int notFinishedTotalTimesMonth;
    private int notFinishedTotalTimesYear;

    public int getNotFinishedTotalTimesAll(){
        return notFinishedTotalTimesWeek + notFinishedTotalTimesMonth + notFinishedTotalTimesYear;
    }

    public int getSuccessRate() {
        int finishedTotal = getFinishedTotalTimesAll();
        int notFinishedTotal = getNotFinishedTotalTimesAll();
        if (finishedTotal + notFinishedTotal == 0) {
            return 0;
        }
        return (finishedTotal * 100) / (finishedTotal + notFinishedTotal);
    }
}
