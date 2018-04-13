package com.pr.pr.pantryraid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;


public class Settings extends Fragment {
    Switch vegan;
    Switch calendar;
    Switch expNotification;
    Button calendarDaily;
    Button calendarWeekly;
    Button calendarMonthly;
    Button expirationDaily;
    Button expirationWeekly;
    Button expirationMonthly;
    Button test;
    savedSettings s;
    Button saveSettings;
    SharedPreferences mpref;
    public Settings(){

    }



        @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.settings, container, false);
        Gson gson = new Gson();
        mpref = getActivity().getPreferences(MODE_PRIVATE);
        String json = mpref.getString("settings", "");
        if(json.equals("")) {
            s = new savedSettings();
        }
        else {
            s = gson.fromJson(json,savedSettings.class);
        }

        vegan = (Switch)rootView.findViewById(R.id.vegan);
        saveSettings = (Button)rootView.findViewById(R.id.saveSettings);
        calendar = (Switch)rootView.findViewById(R.id.calendarSwitch);
        expNotification = (Switch)rootView.findViewById(R.id.expirationSwitch);
        calendarDaily = (Button)rootView.findViewById(R.id.calendarDaily);
        calendarWeekly = (Button)rootView.findViewById(R.id.calendarWeekly);
        calendarMonthly = (Button)rootView.findViewById(R.id.calendarMonthly);
        expirationDaily = (Button)rootView.findViewById(R.id.expirationDaily);
        expirationWeekly = (Button)rootView.findViewById(R.id.expirationWeekly);
        expirationMonthly = (Button)rootView.findViewById(R.id.expirationMonthly);

        if(s.getVegan() == false){
            vegan.setChecked(false);
        }
        else{
            vegan.setChecked(true);
        }
        if(s.getCalendarNotifications() == false){
            calendar.setChecked(false);
        }
        else{
            calendar.setChecked(true);
        }
        if(s.getExpNotifications() == false){
            expNotification.setChecked(false);
        }
        else{
            expNotification.setChecked(true);
        }
        if(s.getCalendarDaily() == false){
            //change color to a false color
            calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        else{
            calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        if(s.getCalendarWeekly() == false){
            calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        else{
            calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        if(s.getCalendarMonthly() == false){
            calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        }
        else{
            calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
        if(s.getExpDaily() == false){
            expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        else{
            expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            expirationWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            expirationMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        if(s.getExpWeekly() == false){
            expirationWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        else{
            expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            expirationWeekly.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            expirationMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        if(s.getExpMonthly() == false){
            expirationMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        else{
            expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            expirationWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            expirationMonthly.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }

        calendarDaily.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                s.setCalendarDaily(true);
                s.setCalendarWeekly(false);
                s.setCalendarMonthly(false);
                if(calendar.isChecked()) {
                    calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
                else{
                    calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        calendarWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.setCalendarWeekly(true);
                s.setCalendarDaily(false);
                s.setCalendarMonthly(false);
                if(calendar.isChecked()) {
                    calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
                else{
                    calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        calendarMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.setCalendarMonthly(true);
                s.setCalendarDaily(false);
                s.setCalendarWeekly(false);
                if(calendar.isChecked()) {
                    calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
                else{
                    calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        calendar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(calendar.isChecked()) {

                    //UI side
                    s.setCalendarNotifications(true);
                    calendar.setChecked(true);
                    // default state
                    s.setCalendarDaily(true);
                    calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                }
                else{

                    //UI side
                    calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendar.setChecked(false);

                    // background side
                    s.setCalendarDaily(false);
                    s.setCalendarWeekly(false);
                    s.setCalendarMonthly(false);
                    s.setCalendarNotifications(false);

                }
            }
        });


        expNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(expNotification.isChecked()) {
                    s.setExpNotifications(true);
                    expNotification.setChecked(true);
                    s.setExpDaily(true);
                    expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                    }

                else{

                    expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    expirationWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    expirationMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    s.setExpDaily(false);
                    s.setExpWeekly(false);
                    s.setExpMonthly(false);
                    s.setExpNotifications(false);
                }
            }
        });

        expirationDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.setExpDaily(true);
                s.setExpWeekly(false);
                s.setExpMonthly(false);
                if(expNotification.isChecked()) {
                    expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    expirationWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    expirationMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
                else{
                    expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    expirationWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    expirationMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        expirationWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.setExpDaily(false);
                s.setExpWeekly(true);
                s.setExpMonthly(false);
                if(expNotification.isChecked()) {
                    expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    expirationWeekly.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    expirationMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
                else{
                    expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    expirationWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    expirationMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }

            }
        });

        expirationMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.setExpDaily(false);
                s.setExpWeekly(false);
                s.setExpMonthly(true);
                if(expNotification.isChecked()) {
                    expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    expirationWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    expirationMonthly.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
                else{
                    expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    expirationWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    expirationMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        saveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getActivity().getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = pref.edit();
                Gson gson = new Gson();
                String json = gson.toJson(s);
                prefsEditor.putString("settings",json);
                prefsEditor.commit();
                Toast.makeText(getActivity(), "Your Settings Have Been Saved", Toast.LENGTH_LONG).show();

            }
        });

        vegan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {

                if(b){
                    s.setVegan(true);

                }
                else{
                    s.setVegan(false);

                }
            }
        });



        return rootView;
    }



}

