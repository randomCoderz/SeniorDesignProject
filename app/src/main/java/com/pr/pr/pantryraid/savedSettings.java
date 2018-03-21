package com.pr.pr.pantryraid;


public class savedSettings {
    boolean CalendarDaily;
    boolean CalendarWeekly;
    boolean CalendarMonthly;
    boolean ExpDaily;
    boolean ExpWeekly;
    boolean ExpMonthly;
    boolean vegan;
    boolean CalendarNotifications;
    boolean ExpNotifications;


    public savedSettings(){
        CalendarDaily = false;
        CalendarWeekly = false;
        CalendarMonthly = false;
        ExpNotifications = false;
        ExpDaily = false;
        ExpWeekly = false;
        ExpMonthly = false;
        CalendarNotifications = false;
        ExpNotifications = false;
        vegan = false;
    }

    public boolean getCalendarDaily(){
        return CalendarDaily;
    }
    public boolean getCalendarWeekly(){
        return CalendarWeekly;
    }
    public boolean getCalendarMonthly(){
        return CalendarMonthly;
    }
    public boolean getExpDaily(){
        return ExpDaily;
    }
    public boolean getExpWeekly(){
        return ExpWeekly;
    }
    public boolean getExpMonthly(){
        return ExpMonthly;
    }
    public boolean getVegan(){
        return vegan;
    }
    public boolean getCalendarNotifications(){
        return CalendarNotifications;
    }
    public boolean getExpNotifications(){
        return ExpNotifications;
    }


    public void setCalendarDaily(boolean CalendarDaily){
        this.CalendarDaily = CalendarDaily;
    }
    public void setCalendarWeekly(boolean CalendarWeekly){
        this.CalendarWeekly = CalendarWeekly;
    }
    public void setCalendarMonthly(boolean CalendarMonthly){
        this.CalendarMonthly = CalendarMonthly;
    }
    public void setExpDaily(boolean ExpDaily){
        this.ExpDaily = ExpDaily;
    }
    public void setExpWeekly(boolean ExpWeekly){
        this.ExpWeekly = ExpWeekly;
    }
    public void setExpMonthly(boolean ExpMonthly){
        this.ExpMonthly = ExpMonthly;
    }
    public void setVegan(boolean vegan){
        this.vegan = vegan;
    }
    public void setCalendarNotifications(boolean CalendarNotifications){
        this.CalendarNotifications = CalendarNotifications;
    }
    public void setExpNotifications(boolean ExpNotifications){
        this.ExpNotifications = ExpNotifications;
    }
}
