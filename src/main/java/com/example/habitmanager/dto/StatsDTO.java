package com.example.habitmanager.dto;

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

    public int getFinishedTotalTimesWeek(){
        return finishedTotalTimesWeek;
    }
    public void setFinishedTotalTimesWeek(int finishedTotalTimesWeek){
        this.finishedTotalTimesWeek = finishedTotalTimesWeek;
    }

    public int getFinishedTotalTimesMonth(){
        return finishedTotalTimesMonth;
    }
    public void setFinishedTotalTimesMonth(int finishedTotalTimesMonth){
        this.finishedTotalTimesMonth = finishedTotalTimesMonth;
    }

    public int getFinishedTotalTimesYear(){
        return finishedTotalTimesYear;
    }
    public void setFinishedTotalTimesYear(int finishedTotalTimesYear){
        this.finishedTotalTimesYear = finishedTotalTimesYear;
    }


    public int getNotFinishedTotalTimesWeek(){
        return notFinishedTotalTimesWeek;
    }
    public void setNotFinishedTotalTimesWeek(int notFinishedTotalTimesWeek){
        this.notFinishedTotalTimesWeek = notFinishedTotalTimesWeek;
    }

    public int getNotFinishedTotalTimesMonth(){
        return notFinishedTotalTimesMonth;
    }
    public void setNotFinishedTotalTimesMonth(int notFinishedTotalTimesMonth){
        this.notFinishedTotalTimesMonth = notFinishedTotalTimesMonth;
    }

    public int getNotFinishedTotalTimesYear(){
        return notFinishedTotalTimesYear;
    }
    public void setNotFinishedTotalTimesYear(int notFinishedTotalTimesYear){
        this.notFinishedTotalTimesYear = notFinishedTotalTimesYear;
    }
}
