package com.pr.pr.pantryraid;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.github.clans.fab.FloatingActionButton;
import com.pr.pr.pantryraid.RoomPersist.AppDatabase;
import com.pr.pr.pantryraid.RoomPersist.IngredientRepository;
import com.pr.pr.pantryraid.RoomPersist.RecipeRepository;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//import android.support.design.widget.FloatingActionButton;

public class recipeFragment extends Fragment implements DatePickerDialog.OnDateSetListener   {
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
    boolean homePage;
    int day;
    int month;
    int year;

    myPantryAdapter adapter = new myPantryAdapter(ingredients);
    AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
    IngredientRepository pbI = new IngredientRepository(mdb);
    final RecipeRepository dbI = new RecipeRepository(mdb);


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
        this.homePage = rec.homePage;
        this.day = rec.day;
        this.month = rec.month;
        this.year = rec.year;
    }

    public void setValues(recipe rec)
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
        this.homePage = rec.homePage;
        this.day = rec.day;
        this.month = rec.month;
        this.year = rec.year;
    }

    public recipe getAsRecipe()
    {
        return new recipe(id, name, url, readyInMinutes, ingredients, analyzedInstructions, instructions, favorites, mealCalendar, homePage, month , day, year);
    }


    private ArrayList<ingredient> pantryList = new ArrayList<ingredient>();
    private RecyclerView rv;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.recipe_info, container, false);
        rv = rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext()){
                @Override
                public boolean canScrollVertically() {
                return false;
            }
        };
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        if(ingredients == null)
        {
            cookBook c = new cookBook(KEY);
            try {
                recipe rec = c.getRecipeInformation(id, true);
                setValues(rec);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        setMissing();

        ScrollView scrollView = rootView.findViewById(R.id.scrollView);
        scrollView.setSmoothScrollingEnabled(true);


        TextView recipeName = rootView.findViewById(R.id.recipeName);
        recipeName.setText(name);
        ImageView img = rootView.findViewById(R.id.recipeImage);

        Picasso.with(rootView.getContext()).load(url).resize(2300,1300).into(img);
        TextView readyInMin = rootView.findViewById(R.id.readyInMin);
        readyInMin.setText("Ready in: " + readyInMinutes + " minutes");

        final FloatingActionButton instructions = rootView.findViewById(R.id.instructions);
        final FloatingActionButton addToCalendar = rootView.findViewById(R.id.addToCalendar);
        final FloatingActionButton missingToCart = rootView.findViewById(R.id.missingToCart);
        final FloatingActionButton selectedToCart = rootView.findViewById(R.id.selectedToCart);
        final FloatingActionButton favoritesButton = rootView.findViewById(R.id.favorites);

        if(mealCalendar == true)
        {
            addToCalendar.setLabelText("Remove from Calendar");
        }

        Calendar currentDate = Calendar.getInstance();
        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                this.getContext(), recipeFragment.this, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));

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
                    instr += x.number + ". " + x.step_description + "\n\n";
                }

                Fragment fragment =  new instructions(instr);

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, fragment).addToBackStack(null).commit();

            }
        });

        missingToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ingredient> toCart = new ArrayList<>();
                for (int i = 0; i < ingredients.size(); i++)
                {
                    if(ingredients.get(i).missing)
                    {
                        ingredients.get(i).shoppingCart = true;
                        toCart.add(ingredients.get(i));
                    }
                }

                System.out.println(toCart.size());
                pbI.insertIngredientList(toCart);

                CharSequence text = "Added to Cart";
                Toast toast = Toast.makeText(view.getContext(), text, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        selectedToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ingredient> toCart = new ArrayList<>();
                for (int i = 0; i < ingredients.size(); i++)
                {
                    if(ingredients.get(i).selected && ingredients.get(i) != null)
                    {
                        ingredients.get(i).shoppingCart = true;
                        toCart.add(ingredients.get(i));
                    }
                }
                pbI.insertIngredientList(toCart);
                CharSequence text = "Added to Cart";
                Toast toast = Toast.makeText(view.getContext(), text, Toast.LENGTH_SHORT);
                toast.show();
            }
        });



        addToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mealCalendar == false)
                {
                    datePickerDialog.show();
                    homePage = false;
                    mealCalendar = true;
                }
                else
                {
                    dbI.removeRecipe(getAsRecipe());
                    mealCalendar = false;
                    addToCalendar.setLabelText("Add to Calendar");
                }


            }
        });

        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(favorites)
                {
                    favorites = false;
                }
                else
                {
                    favorites = true;
                }
                dbI.insertRecipe(getAsRecipe());
                CharSequence text = "Added to Favorites";
                Toast toast = Toast.makeText(view.getContext(), text, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return rootView;
    }

    public void onStart()
    {
        super.onStart();
        adapter = new myPantryAdapter(ingredients);
        rv.setAdapter(adapter);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        this.year = year;
        this.month = month+1;
        this.day = day;

        dbI.insertRecipe(getAsRecipe());
    }

    public void setMissing()
    {
        pbI.getAllIngredients();
        ArrayList<ingredient> ing = pbI.getIngredients();

        if(ing != null && ingredients != null)
        {

            for(int i = 0; i < ingredients.size(); i++)
            {
                if(!contains(ing, ingredients.get(i)))
                {
                    ingredients.get(i).missing = true;
                }
                else
                {
                    ingredients.get(i).missing = false;
                }
                System.out.println(ingredients.get(i).name + " " + ingredients.get(i).missing);
            }

        }

    }

    public boolean contains(ArrayList<ingredient> list, ingredient ing)
    {
        boolean result = false;

        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).pantry)
            {
                if(list.get(i).id == ing.id)
                {
                    result = true;
                }
            }
        }
        return result;
    }
}