package com.pr.pr.pantryraid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.pr.pr.pantryraid.RoomPersist.AppDatabase;
import com.pr.pr.pantryraid.RoomPersist.RecipeRepository;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.ArrayList;

public class recipeFragment extends Fragment {
    private final String KEY = "Y2arFIdXItmsh3d4HlBeB2ar1Zdzp17aqmJjsnUYGxgm2KHYG5";
    int id;
    String name;
    String url;
    int readyInMinutes;
    private ingredientsLVAdapter listAdapter;
    ArrayList<ingredient> ingredients = new ArrayList<>();
    String instructions;
    ArrayList<step> analyzedInstructions;
    boolean favorites;
    boolean mealCalendar;
    int day;
    int month;
    int year;

    private ListView listView;

    public recipeFragment() {

    }

    @SuppressLint("ValidFragment")
    public recipeFragment(recipe rec)
    {
        this.id = rec.getId();
        this.name = rec.name;
        this.url = rec.url;
        this.readyInMinutes = rec.getReadyInMinutes();
        this.ingredients = rec.getIngredients();
        this.instructions = rec.getInstructions();
        this.analyzedInstructions = rec.getAnalyzedInstructions();
        this.favorites = rec.favorites;
        this.mealCalendar = rec.mealCalendar;
        this.day = rec.day;
        this.month = rec.month;
        this.year = rec.year;
    }

    public recipe getAsRecipe()
    {
        return new recipe(id, name, url, readyInMinutes, ingredients, analyzedInstructions, instructions, favorites, mealCalendar, day, month , year);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
        final RecipeRepository dbI = new RecipeRepository(mdb);

        final View rootView = inflater.inflate(R.layout.recipe_info, container, false);

//        listView = rootView.findViewById(R.id.ingredientList);
//        listView.setAdapter(new ingredientsLVAdapter(getActivity(), ingredients));
        TextView recipeName = rootView.findViewById(R.id.recipeName);
        recipeName.setText(name);
        ImageView img = rootView.findViewById(R.id.recipeImage);
//        img.getLayoutParams().width = 700;
//        img.getLayoutParams().height = 700;
        Picasso.with(rootView.getContext()).load(url).resize(2300,1300).into(img);
        TextView readyInMin = rootView.findViewById(R.id.readyInMin);
        readyInMin.setText("Ready in: " + readyInMinutes + " minutes");

        final Button instructions = rootView.findViewById(R.id.instructions);
        final Button addToCalendar = rootView.findViewById(R.id.addToCalendar);
        final Button missingToCart = rootView.findViewById(R.id.missingToCart);
        final Button selectedToCart = rootView.findViewById(R.id.selectedToCart);

        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(analyzedInstructions == null)
                {
                    home h = new home(KEY);
                    try {
                        analyzedInstructions = h.getAnalyzedInstructions(id, true);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                String instr = "";
                for(int i = 0; i < analyzedInstructions.size(); i++)
                {
                    step x = analyzedInstructions.get(i);
                    instr += x.number + ". " + x.step_description + "\n";
                }

                Fragment fragment =  new instructions(instr);

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, fragment).addToBackStack(null).commit();
            }
        });

        missingToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<ingredient> toCart = new ArrayList<ingredient>();
                for (int i = 0; i < ingredients.size(); i++)
                {
                    if(ingredients.get(i).missing)
                    {
                        toCart.add(ingredients.get(i));
                    }
                }

            }
        });

        selectedToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<ingredient> toCart = new ArrayList<ingredient>();
                for (int i = 0; i < ingredients.size(); i++)
                {
                    if(ingredients.get(i).selected)
                    {
                        toCart.add(ingredients.get(i));
                    }
                }

            }
        });



        addToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mealCalendar = true;
                dbI.insertRecipe(getAsRecipe());

            }
        });

        return rootView;
    }
}