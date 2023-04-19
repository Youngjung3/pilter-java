package com.cookandroid.home;

public class ConditionInfo {

    private int id;
    private String month;
    private String day;
    private String condition;
    private String medicine;
    private String monthDay;


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDay(String day) {
        this.day = day;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getCondition() {
        return condition;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMonthDay(String monthDay) {
        this.monthDay = monthDay;
    }

    public String getMonthDay() {
        return monthDay;
    }
}
