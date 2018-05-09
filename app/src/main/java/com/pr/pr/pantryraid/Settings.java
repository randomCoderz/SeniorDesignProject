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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.CompoundButton;
//import android.widget.ListView;
//import android.widget.Switch;



public class Settings extends Fragment implements AdapterView.OnItemSelectedListener{

    Switch restrictions;
    Switch calendar;
    Button calendarDaily;
    Button calendarWeekly;
    Button calendarMonthly;
    savedSettings s;
    Button saveSettings;
    SharedPreferences mpref;
    private ListView listView;
    DietRestrictName  data[] = new DietRestrictName[15];
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
        // TODO:
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
        calendarDaily = rootView.findViewById(R.id.calendarDaily);
        calendarWeekly = rootView.findViewById(R.id.calendarWeekly);
        calendarMonthly = rootView.findViewById(R.id.calendarMonthly);


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

