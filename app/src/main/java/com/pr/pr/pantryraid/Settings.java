package com.pr.pr.pantryraid;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.CompoundButton;
//import android.widget.ListView;
//import android.widget.Switch;

public class Settings extends Fragment {

    Switch restrictions;
    Switch calendar;
    Switch expNotification;
    Button calendarDaily;
    Button calendarWeekly;
    Button calendarMonthly;
    Button expirationDaily;
    Button expirationWeekly;
    Button expirationMonthly;
    savedSettings s;
    Button saveSettings;
    SharedPreferences mpref;
    private ListView listView;
    ArrayList<DietRestrictName> r = new ArrayList<>();
    private DietRestrictLVAdapter listAdapter;
    DietRestrictHolder l = new DietRestrictHolder();



    public Settings() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.settings, container, false);
        Gson gson = new Gson();
        mpref = getActivity().getPreferences(MODE_PRIVATE);
        String json = mpref.getString("settings", "");
        if (json.equals("")) {
            s = new savedSettings();
        } else {
            s = gson.fromJson(json, savedSettings.class);
        }
        getList();
        listView = rootView.findViewById(R.id.restrictionList);
        listAdapter = new DietRestrictLVAdapter(getActivity(), r);
        listView.setAdapter(listAdapter);
        // restrictionName.setText(restriction);

        restrictions = rootView.findViewById(R.id.DietaryRestrictions);
        saveSettings = rootView.findViewById(R.id.saveSettings);
        calendar = rootView.findViewById(R.id.calendarSwitch);
        expNotification = rootView.findViewById(R.id.expirationSwitch);
        calendarDaily = rootView.findViewById(R.id.calendarDaily);
        calendarWeekly = rootView.findViewById(R.id.calendarWeekly);
        calendarMonthly = rootView.findViewById(R.id.calendarMonthly);
        expirationDaily = rootView.findViewById(R.id.expirationDaily);
        expirationWeekly = rootView.findViewById(R.id.expirationWeekly);
        expirationMonthly = rootView.findViewById(R.id.expirationMonthly);

        if (s.getRestrictions() == false) {
            restrictions.setChecked(false);
            listView.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.grey));
        } else {
            restrictions.setChecked(true);
            listView.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.primaryLight));
        }
        if (s.getCalendarNotifications() == false) {
            calendar.setChecked(false);
        } else {
            calendar.setChecked(true);
        }
        if (s.getExpNotifications() == false) {
            expNotification.setChecked(false);
        } else {
            expNotification.setChecked(true);
        }
        if (s.getCalendarDaily() == false) {
            //change color to a false color
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                calendarDaily.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        } else {
            calendarDaily.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
            calendarWeekly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
            calendarMonthly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        }
        if (s.getCalendarWeekly() == false) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                calendarWeekly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        } else {
            calendarDaily.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
            calendarWeekly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
            calendarMonthly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        }
        if (s.getCalendarMonthly() == false) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                calendarMonthly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));

        } else {
            calendarDaily.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
            calendarWeekly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
            calendarMonthly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
        }
        if (s.getExpDaily() == false) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                expirationDaily.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        } else {
            expirationDaily.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
            expirationWeekly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
            expirationMonthly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        }
        if (s.getExpWeekly() == false) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                expirationWeekly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        } else {
            expirationDaily.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
            expirationWeekly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
            expirationMonthly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        }
        if (s.getExpMonthly() == false) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                expirationMonthly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        } else {

            expirationDaily.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
            expirationWeekly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
            expirationMonthly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
        }

        calendarDaily.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                s.setCalendarDaily(true);
                s.setCalendarWeekly(false);
                s.setCalendarMonthly(false);
                if (calendar.isChecked()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        calendarDaily.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                        calendarWeekly.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                        calendarMonthly.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                    }
                } else {
                    calendarDaily.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                    calendarWeekly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                    calendarMonthly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                }
            }
        });

        calendarWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.setCalendarWeekly(true);
                s.setCalendarDaily(false);
                s.setCalendarMonthly(false);
                if (calendar.isChecked()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        calendarDaily.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                        calendarWeekly.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                        calendarMonthly.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                    }
                } else {
                    calendarDaily.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                    calendarWeekly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                    calendarMonthly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                }
            }
        });

        calendarMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.setCalendarMonthly(true);
                s.setCalendarDaily(false);
                s.setCalendarWeekly(false);
                if (calendar.isChecked()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        calendarDaily.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                    calendarWeekly.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                    calendarMonthly.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                }
                } else {
                    calendarDaily.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                    calendarWeekly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                    calendarMonthly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                }
            }
        });

        restrictions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {

                if (restrictions.isChecked()) {
                    s.setRestrictions(true);
                    restrictions.setChecked(true);
                    listView.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.White));
                   
                } else {
                    listView.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.grey));
                    s.setRestrictions(false);
                }
            }
        });

        calendar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (calendar.isChecked()) {

                    //UI side
                    s.setCalendarNotifications(true);
                    calendar.setChecked(true);
                    // default state
                    s.setCalendarDaily(true);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        calendarDaily.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorAccent));

                } else {

                    //UI side
                    calendarDaily.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                    calendarWeekly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                    calendarMonthly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
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
                if (expNotification.isChecked()) {
                    s.setExpNotifications(true);
                    expNotification.setChecked(true);
                    s.setExpDaily(true);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        expirationDaily.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorAccent));

                } else {

                    expirationDaily.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                    expirationWeekly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                    expirationMonthly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
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
                if (expNotification.isChecked()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        expirationDaily.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                        expirationWeekly.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                        expirationMonthly.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                    }
                } else {
                    expirationDaily.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                    expirationWeekly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                    expirationMonthly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                }
            }
        });

        expirationWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.setExpDaily(false);
                s.setExpWeekly(true);
                s.setExpMonthly(false);
                if (expNotification.isChecked()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        expirationDaily.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                        expirationWeekly.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                        expirationMonthly.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                    }
                } else {
                    expirationDaily.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                    expirationWeekly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                    expirationMonthly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                }

            }
        });

        expirationMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.setExpDaily(false);
                s.setExpWeekly(false);
                s.setExpMonthly(true);
                if (expNotification.isChecked()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        expirationDaily.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                        expirationWeekly.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                        expirationMonthly.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                    }
                } else {
                    expirationDaily.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                    expirationWeekly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                    expirationMonthly.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
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
                prefsEditor.putString("settings", json);
                prefsEditor.commit();
                Toast.makeText(getActivity(), "Your Settings Have Been Saved", Toast.LENGTH_LONG).show();

            }
        });



//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if (view != null) {
//                    CheckBox checkBox = (CheckBox)view.findViewById(R.id.restrictionCheckBox);
//                    checkBox.setChecked(!checkBox.isChecked());
//                }
//            }
//        });

        return rootView;
    }
    public void getList() {
        r.add(new DietRestrictName("Dairy"));
        r.add(new DietRestrictName("Egg"));
        r.add(new DietRestrictName("Gluten"));
        r.add(new DietRestrictName("Peanut"));
        r.add(new DietRestrictName("Sesame"));
        r.add(new DietRestrictName("Seafood"));
        r.add(new DietRestrictName("Soy"));
        r.add(new DietRestrictName("Sulfite"));
        r.add(new DietRestrictName("Nuts"));
        r.add(new DietRestrictName("Wheat"));
        r.add(new DietRestrictName("Pescetarian"));
        r.add(new DietRestrictName("LactoVegetarian"));
        r.add(new DietRestrictName("OvoVegetarian"));
        r.add(new DietRestrictName("Vegetarian"));
        r.add(new DietRestrictName("Vegan"));



    }
}

