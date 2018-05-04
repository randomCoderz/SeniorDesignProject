package com.pr.pr.pantryraid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;


public class Settings extends Fragment implements AdapterView.OnItemSelectedListener{

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
    DietRestrictName  data[] = new DietRestrictName[15];
    private DietRestrictLVAdapter listAdapter;


    public Settings() {

    }

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
        listAdapter = new DietRestrictLVAdapter(getActivity(), R.layout.settings, data);
        listAdapter.notifyDataSetChanged();

        listView.setAdapter(listAdapter);

        //alternative way for listview clicker: look for implements AdapterView.OnItemSelectedListener
        // add listener to this (getActivity)
        // import all methods
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
           @Override
           public void onItemClick(AdapterView<?> a, View v, int position, long id){
               Toast.makeText(getActivity(), position,Toast.LENGTH_SHORT ).show();
               listAdapter.toggle(position);
           }
        });
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
        } else {
            restrictions.setChecked(true);
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
            calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else {
            calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        if (s.getCalendarWeekly() == false) {
            calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else {
            calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        if (s.getCalendarMonthly() == false) {
            calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        } else {
            calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
        if (s.getExpDaily() == false) {
            expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else {
            expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            expirationWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            expirationMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        if (s.getExpWeekly() == false) {
            expirationWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else {
            expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            expirationWeekly.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            expirationMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        if (s.getExpMonthly() == false) {
            expirationMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else {
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
                if (calendar.isChecked()) {
                    calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
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
                if (calendar.isChecked()) {
                    calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
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
                if (calendar.isChecked()) {
                    calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                } else {
                    calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    calendarMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
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
                    calendarDaily.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                } else {

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
                if (expNotification.isChecked()) {
                    s.setExpNotifications(true);
                    expNotification.setChecked(true);
                    s.setExpDaily(true);
                    expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                } else {

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
                if (expNotification.isChecked()) {
                    expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    expirationWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    expirationMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
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
                if (expNotification.isChecked()) {
                    expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    expirationWeekly.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    expirationMonthly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
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
                if (expNotification.isChecked()) {
                    expirationDaily.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    expirationWeekly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    expirationMonthly.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                } else {
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
                prefsEditor.putString("settings", json);
                prefsEditor.commit();
                Toast.makeText(getActivity(), "Your Settings Have Been Saved", Toast.LENGTH_LONG).show();

            }
        });

        restrictions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {

                if (b) {
                    s.setRestrictions(true);

                } else {
                    s.setRestrictions(false);

                }
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
        data[0] = new DietRestrictName("Dairy");
        data[1] = new DietRestrictName("Egg");
        data[2] = new DietRestrictName("Gluten");
        data[3] = new DietRestrictName("Peanut");
        data[4] = new DietRestrictName("Sesame");
        data[5] = new DietRestrictName("Seafood");
        data[6] = new DietRestrictName("Soy");
        data[7] = new DietRestrictName("Sulfite");
        data[8] = new DietRestrictName("Nuts");
        data[9] = new DietRestrictName("Wheat");
        data[10] = new DietRestrictName("Pescetarian");
        data[11] = new DietRestrictName("LactoVegetarian");
        data[12] = new DietRestrictName("OvoVegetarian");
        data[13] = new DietRestrictName("Vegetarian");
        data[14] = new DietRestrictName("Vegan");



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getActivity(), i,Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

