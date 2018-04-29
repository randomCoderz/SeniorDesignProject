package com.pr.pr.pantryraid;


public class savedSettings {
    boolean CalendarDaily;
    boolean CalendarWeekly;
    boolean CalendarMonthly;
    boolean ExpDaily;
    boolean ExpWeekly;
    boolean ExpMonthly;
    boolean vegan;
    boolean DietRestrictions;
    boolean CalendarNotifications;
    boolean ExpNotifications;
    boolean dairy;
    boolean egg;
    boolean gluten;
    boolean peanut;
    boolean sesame;
    boolean seafood;
    boolean soy;
    boolean sulfite;
    boolean nuts;
    boolean wheat;
    boolean pescetarian;
    boolean lactoVegetarian;
    boolean ovoVegetarian;
    boolean vegetarian;


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

    public boolean getRestrictions(){
        return DietRestrictions;
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
    public boolean getDairy(){
        return dairy;
    }
    public boolean getEgg(){
        return egg;
    }
    public boolean getGluten(){
        return gluten;
    }
    public boolean getPeanut(){
        return peanut;
    }
    public boolean getSeafood(){
        return seafood;
    }
    public boolean getSoy(){
        return soy;
    }
    public boolean getSulfite(){
        return sulfite;
    }
    public boolean getNuts(){
        return nuts;
    }
    public boolean getWheat(){
        return  wheat;
    }
    public boolean getPescetarian(){
        return pescetarian;
    }
    public boolean getLactoVegetarian(){
        return lactoVegetarian;
    }
    public boolean getOvoVegetarian(){
        return ovoVegetarian;
    }
    public boolean getVegetarian(){
        return vegetarian;
    }



    public void setDairy(boolean dairy){
        this.dairy = dairy;

    }
    public void setEgg(boolean egg){
        this.egg = egg;
    }
    public void setGluten(boolean gluten){
        this.gluten = gluten;
    }
    public void setPeanut(boolean peanut){
        this.peanut = peanut;
    }
    public  void setSesame(boolean sesame){
        this.sesame = sesame;
    }
    public void setSeafood(boolean seafood){
        this.seafood = seafood;
    }
    public void setSoy(boolean soy){
        this.soy = soy;

    }
    public void setSulfite(boolean sulfite){
        this.sulfite = sulfite;
    }
    public void setNuts(boolean nuts){
        this.nuts = nuts;
    }
    public  void setWheat(boolean wheat){
        this.wheat = wheat;
    }
    public void setPescetarian(boolean pescetarian){
        this.pescetarian = pescetarian;
    }
    public void setLactoVegetarian(boolean lactovegetarian){
        this.lactoVegetarian = lactoVegetarian;
    }
    public void setOvoVegetarian(boolean ovoVegetarian){
        this. ovoVegetarian = ovoVegetarian;
    }
    public void setVegetarian(boolean vegetarian){
        this.vegetarian = vegetarian;
    }

    public void setRestrictions(boolean DietRestrictions){
        this.DietRestrictions = DietRestrictions;
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
