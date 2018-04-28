package com.pr.pr.pantryraid;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.pr.pr.pantryraid.RoomPersist.AppDatabase;
import com.pr.pr.pantryraid.RoomPersist.RecipeRepository;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Nam on 4/2/2018.
 */

public class calendar extends Fragment
{
    EditText editText;
    Calendar currentDate = Calendar.getInstance();
    Button dateButton;
    Button addButton;
    Button removeButton;

    private static final String KEY = "Y2arFIdXItmsh3d4HlBeB2ar1Zdzp17aqmJjsnUYGxgm2KHYG5";

    private List<recipe> recipeList;
    private RecyclerView rv;
    private home h = new home(KEY);

    private int year;
    private int month;
    private int day;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.calendar, container, false);

        //database stuff
        AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
        RecipeRepository d = new RecipeRepository(mdb);

        //RVAdapter for recipe cards
        rv = rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        try {
            recipeList = h.randomRecipe(false, 2, null);
            recipeRVAdapter adapter = new recipeRVAdapter(recipeList);
            rv.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        editText = (EditText) rootView.findViewById(R.id.textBox);
        dateButton = (Button) rootView.findViewById(R.id.dateButton);
        addButton = (Button) rootView.findViewById(R.id.addButton);
        removeButton = (Button) rootView.findViewById(R.id.removeButton);

        String date = new SimpleDateFormat("M/d/yyyy", Locale.getDefault()).format(new Date());
        editText.setText(date);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = currentDate.get(Calendar.YEAR);
                month = currentDate.get(Calendar.MONTH);
                day = currentDate.get(Calendar.DAY_OF_MONTH);
                //int dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);

                DatePickerDialog mDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        currentDate.set(year, month, dayOfMonth);
                        editText.setText((month + 1) + "/" + dayOfMonth + "/" + year);
                    }
                }, year, month, day);
                mDatePicker.show();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return rootView;

    }

}
