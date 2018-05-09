package com.pr.pr.pantryraid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pr.pr.pantryraid.RoomPersist.AppDatabase;
import com.pr.pr.pantryraid.RoomPersist.RecipeRepository;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nam on 4/30/2018.
 */

public class homePage extends Fragment {
    private static final String KEY = "Y2arFIdXItmsh3d4HlBeB2ar1Zdzp17aqmJjsnUYGxgm2KHYG5";
    private List<recipe> recipeList;
    private List<recipe> homeRecipeList;
    private RecyclerView rv;
    private home h = new home(KEY);


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.homepage, container, false);


//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(getDailyRecipes(), 0, 24, TimeUnit.HOURS);
        AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
        RecipeRepository dbI = new RecipeRepository(mdb);

        rv = rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        getAllRecipesFromDB();
        if (recipeList == null) {
            try {
                recipeList = h.randomRecipe(false, 5, null);
                dbI.insertRecipeList(recipeList);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //demo
//        initalizeData();

        return rootView;
    }

    public void onStart() {
        super.onStart();

        recipeRVAdapter adapter = new recipeRVAdapter(recipeList);
        rv.setAdapter(adapter);

    }

    //make new list for all recipes and home recipes and pull from database method
    public void getAllRecipesFromDB() {
        System.out.println("In get home recipes from DB");
        AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
        RecipeRepository dbI = new RecipeRepository(mdb);
        dbI.getAllRecipes();
        List<recipe> allRecipes = dbI.getRecipes();

        if (allRecipes != null) {
            homeRecipeList = new ArrayList<>();
            for (int i = 0; i < allRecipes.size(); i++) {
                if (allRecipes.get(i).homePage) {
                    homeRecipeList.add(allRecipes.get(i));
                }
            }
            recipeList = homeRecipeList;
        }
        initalizeData();
    }

    public void removeOldHomeRecipes() {
        AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
        RecipeRepository dbI = new RecipeRepository(mdb);
        dbI.getAllRecipes();
        List<recipe> allRecipes = dbI.getRecipes();
        if (allRecipes != null) {
            for (int i = 0; i < allRecipes.size(); i++) {
                if (allRecipes.get(i).homePage) {
                    dbI.removeRecipe(allRecipes.get(i));
                }
            }
        }
    }

    public Runnable getDailyRecipes() {
        final Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    recipeList = h.randomRecipe(false, 5, null);
                    System.out.println("in getDailyRecipes, recipeslist size: " + recipeList.size());
                    //gets correct recipe, need to store it
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ;
        };
        return task;
    }

    public void initalizeData() {
        ArrayList<ingredient> r1list = new ArrayList<>();
        r1list.add(new ingredient(18632, "brownie mix", "1", "box", null, 0, false, false, false, false));
        r1list.add(new ingredient(1123, "egg", "2", "", null, 0, false, false, false, false));
        r1list.add(new ingredient(10018166, "oreo cookies", "5", "whole", null, 0, false, false, false, false));
        r1list.add(new ingredient(14412, "water", "8", "oz", null, 0, false, false, false, false));

        ArrayList<step> r1steps = new ArrayList<>();
        r1steps.add(new step(1, "Preheat the oven to 325", null, null));
        r1steps.add(new step(2, "Prepare the brownie mix as directed with the egg, oil and water.Coarsely crush the Oreos in a large bag with your hands or a rolling pin.  I like to leave some pieces pretty big.", null, null));
        r1steps.add(new step(3, "Pour them into the batter and mix well. Batter will be thick.", null, null));
        r1steps.add(new step(4, "Pour batter into a greased square baking pan (mine is about 8x8)", null, null));
        r1steps.add(new step(5, "and bake for 30-40 minutes. Check doneness by inserting a knife into the brownies; if it comes out mostly clean, you should be good to go. But when in doubt, I'd say underbake!", null, null));

        recipe r1 = new recipe(601688, "Best Ever Brownies", "https://spoonacular.com/recipeImages/601688-556x370.jpg", 45, r1list, r1steps, "Preheat the oven to 325. Prepare the brownie mix as directed with the egg, oil and water.Coarsely crush the Oreos in a large bag with your hands or a rolling pin.  I like to leave some pieces pretty big. Pour them into the batter and mix well. Batter will be thick.Pour batter into a greased square baking pan (mine is about 8x8) and bake for 30-40 minutes. Check doneness by inserting a knife into the brownies; if it comes out mostly clean, you should be good to go. But when in doubt, I'd say underbake!",
                true, false, true, 0, 0, 0);

        ArrayList<ingredient> r2list = new ArrayList<>();
        r2list.add(new ingredient(9050, "blueberries", "10", "", null, 0, false, false, false, false));
        r2list.add(new ingredient(2010, "cinnamon", "1", "stick", null, 0, false, false, false, false));
        r2list.add(new ingredient(12220, "flax seed meal", "1", "cup", null, 0, false, false, false, false));
        r2list.add(new ingredient(11216, "fresh ginger", "10", "grams", null, 0, false, false, false, false));
        r2list.add(new ingredient(1256, "greek yogurt", "1", "cup", null, 0, false, false, false, false));
        r2list.add(new ingredient(10014412, "ice", "10", "cubes", null, 0, false, false, false, false));
        r2list.add(new ingredient(9236, "peach", "10", null, "whole", 0, false, false, false, false));
        r2list.add(new ingredient(1223, "protein powder", "10", "grams", null, 0, false, false, false, false));
        r2list.add(new ingredient(98892, "psyllium husks", "10", "", null, 0, false, false, false, false));
        r2list.add(new ingredient(9316, "strawberries", "10", "", null, 0, false, false, false, false));
        r2list.add(new ingredient(14412, "water", "10", "oz", null, 0, false, false, false, false));

        ArrayList<step> r2steps = new ArrayList<>();
        r2steps.add(new step(1, "Puree all ingredients in a high speed blender until smooth. Enjoy!", null, null));

        recipe r2 = new recipe(482640, "Strawberry Ginger Smoothie", "https://spoonacular.com/recipeImages/482640-556x370.jpg", 10, r2list, r2steps, "Puree all ingredients in a high speed blender until smooth. Enjoy!",
                false, false, true, 0, 0, 0);


        ArrayList<ingredient> r3list = new ArrayList<>();
        r3list.add(new ingredient(19335, "granulated sugar", "4", "cups", null, 0, false, false, false, false));
        r3list.add(new ingredient(1019016, "juice", "1", "cup", null, 0, false, false, false, false));
        r3list.add(new ingredient(9150, "lemon", "1", "whole", null, 0, false, false, false, false));
        r3list.add(new ingredient(9279, "plums", "2", "", null, 0, false, false, false, false));
        r3list.add(new ingredient(19335, "sugar", "3", "cups", null, 0, false, false, false, false));

        ArrayList<step> r3steps = new ArrayList<>();
        r3steps.add(new step(1, "Strip verbena leaves from stems and place leaves in a tight-sealing container with 1 cup of sugar. Allow to sit at least overnight and up to several weeks to infuse flavor.", null, null));
        r3steps.add(new step(2, "Preheat oven to 350°F and prepare boiling water canner. Sterilize 6 half-pint jars by boiling them for ten minutes. Wash lids and rings and bring to a simmer in a separate, small saucepan of water. Turn off heat and allow jars, lids and rings to sit in hot water until you need them.", null, null));
        r3steps.add(new step(3, "Wash and quarter plums, removing pits. Toss plum quarters with infused sugar and verbena leaves and spread in a single layer on a sheet pan. Roast, uncovered, until plums have softened and begun to release juice, about 30 minutes..", null, null));
        r3steps.add(new step(4, "When plums and verbena are finished roasting, place them in a heavy-bottomed saucepan or Dutch oven, along with lemon juice. Bring to a boil over high heat. Stir together remaining sugar and pectin in a small bowl, then stir mixture into Dutch oven and return to a boil. Cook until the mixture registers 220°F on a candy or instant read thermometer.", null, null));
        r3steps.add(new step(5, "Turn off heat and skim any foam with a spoon. Ladle jam into prepared jars, leaving a quarter inch of head space. Wipe the rims of the jar lids with a clean kitchen or paper towel and seal.", null, null));
        r3steps.add(new step(6, "Place the sealed jars back into the canning kettle. When all jars are added, make sure that the water level clears the jar lids by at least one inch. Add more water if necessary, and, over high heat, bring the water back up to a boil. Once the water boils, set a timer for 10 minutes.", null, null));
        r3steps.add(new step(7, "After 10 minutes, turn off heat, and allow jars to sit in water for 5 additional minutes. Then, using a jar lifter, remove the jars to a cooling rack.", null, null));
        r3steps.add(new step(8, "Once jars have reached room temperature, remove rings and test that all lids have sealed properly. If any have not sealed, store them in the refrigerator. Label and store sealed jars in a cool place out of direct sunlight.", null, null));


        recipe r3 = new recipe(202483, "Roasted Plum and Lemon Verbena Jam", "https://spoonacular.com/recipeImages/202483-556x370.jpg", 120, r3list, r3steps, "Check out the analyzed instructions!",
                false, false, true, 0, 0, 0);

        ArrayList<ingredient> r4list = new ArrayList<>();
        r4list.add(new ingredient(9040, "bananas", "1", "", null, 0, false, false, false, false));
        r4list.add(new ingredient(10118192, "cookie", "3", "whole", null, 0, false, false, false, false));
        r4list.add(new ingredient(10016098, "crunchy peanut butter", "1", "tbs", null, 0, false, false, false, false));
        r4list.add(new ingredient(1077, "dairy milk", "1/2", "gallon", null, 0, false, false, false, false));

        ArrayList<step> r4steps = new ArrayList<>();
        r4steps.add(new step(1, "Add your bananas, peanut butter and non-dairy milk to the blender and blend until thick, creamy and smooth. Start out with less milk to ensure you achieve a thick texture, then add more to ensure it all blends evenly.Once blended, drop in two no bake cookie bites and give it two more pulses to loosely mix.", null, null));
        r4steps.add(new step(2, "Pour into two serving glasses, and top with another crumbled no bake cookie bite (optional).", null, null));
        r4steps.add(new step(3, "Serve immediately.", null, null));

        recipe r4 = new recipe(500550, "No Bake Cookie Dough Blizzard", "https://spoonacular.com/recipeImages/500550-556x370.jpg", 5, r4list, r4steps, "Add your bananas, peanut butter and non-dairy milk to the blender and blend until thick, creamy and smooth. Start out with less milk to ensure you achieve a thick texture, then add more to ensure it all blends evenly.Once blended, drop in two no bake cookie bites and give it two more pulses to loosely mix.Pour into two serving glasses, and top with another crumbled no bake cookie bite (optional). Serve immediately.",
                false, false, true, 0, 0, 0);

        ArrayList<ingredient> r5list = new ArrayList<>();
        r5list.add(new ingredient(9003, "apples", null, null, null, 0, false, false, false, false));
        r5list.add(new ingredient(19334, "brown sugar", null, null, null, 0, false, false, false, false));
        r5list.add(new ingredient(1001, "butter", null, null, null, 0, false, false, false, false));
        r5list.add(new ingredient(2010, "cinnamon", null, null, null, 0, false, false, false, false));
        r5list.add(new ingredient(1123, "egg", null, null, null, 0, false, false, false, false));
        r5list.add(new ingredient(18337, "puff pastry", null, null, null, 0, false, false, false, false));

        ArrayList<step> r5steps = new ArrayList<>();
        r5steps.add(new step(1, "In a medium skillet heat the butter and then add the apples, cinnamon and sugar.Cook until all the juice from the apples is soaked up and the apples are cooked through, about 10 minutes.Preheat oven to 400 F degrees.On a floured surface, roll out each sheet of pastry and cut into 4 pieces, each piece should be about 5 x 5 inches.This will make 8 turnovers, so add 1/8 of the apple mixture to each pastry. You can fold it any way you want, either as a triangle or as a rectangle.", null, null));
        r5steps.add(new step(2, "Brush some egg around the edges of the pastry, then seal the edges with a fork.", null, null));
        r5steps.add(new step(3, "Brush the turnovers with egg wash and cut a couple slits into each pastry.", null, null));
        r5steps.add(new step(3, "Bake for about 20 min or until and and golden brown.", null, null));

        recipe r5 = new recipe(840500, "Quick Apple Turnovers", "https://spoonacular.com/recipeImages/840500-556x370.jpg", 50, r5list, r5steps, "In a medium skillet heat the butter and then add the apples, cinnamon and sugar.Cook until all the juice from the apples is soaked up and the apples are cooked through, about 10 minutes.Preheat oven to 400 F degrees.On a floured surface, roll out each sheet of pastry and cut into 4 pieces, each piece should be about 5 x 5 inches.This will make 8 turnovers, so add 1/8 of the apple mixture to each pastry. You can fold it any way you want, either as a triangle or as a rectangle. Brush some egg around the edges of the pastry, then seal the edges with a fork.Brush the turnovers with egg wash and cut a couple slits into each pastry.Bake for about 20 min or until and and golden brown.",
                false, false, true, 0, 0, 0);


        ArrayList<recipe> r = new ArrayList<>();
        r.add(r1);
        r.add(r2);
        r.add(r3);
        r.add(r4);
        r.add(r5);
        recipeList = new ArrayList<>();
        recipeList.add(r1);
        recipeList.add(r2);
        recipeList.add(r3);
        recipeList.add(r4);
        recipeList.add(r5);

        AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
        RecipeRepository dbI = new RecipeRepository(mdb);

        dbI.insertRecipeList(r);

    }


}
