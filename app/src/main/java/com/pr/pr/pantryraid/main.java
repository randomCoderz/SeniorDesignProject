package com.pr.pr.pantryraid;

//Android Tools

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.pr.pr.pantryraid.roomPersist.AppDatabase;
import com.pr.pr.pantryraid.roomPersist.dbInitialize;

import org.json.JSONException;

import java.util.List;

//Unirest, Spoonacular Imports, JSON

public class main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    NavigationView navigationView;
    private static final String TAG = "MainActivity";
    private static final String KEY = "Y2arFIdXItmsh3d4HlBeB2ar1Zdzp17aqmJjsnUYGxgm2KHYG5";

    private List<ingredient> ingredientList;
    private List<recipe> recipeList;
    private RecyclerView rv;
    private home h = new home(KEY);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);

            Toolbar toolbar = findViewById(R.id.toolbar);

        //Database
            AppDatabase mdb = AppDatabase.getInMemoryDatabase(getApplicationContext());
            dbInitialize dbI = new dbInitialize();
            dbInitialize.populateRecipes(mdb);


            //start of navigation drawer
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
            //end of navigation drawer

            rv = findViewById(R.id.rv);

            LinearLayoutManager llm = new LinearLayoutManager(this);

            rv.setHasFixedSize(true);
            rv.setLayoutManager(llm);



        try {
            //make recipe list
//            cookBook c = new cookBook(KEY);
//            String[] ingredients = {"butter", "flour", "corn"};
//            c.getInstructions(640058, true);
//            c.start();
//            c.join();
//
//            HttpResponse<JsonNode> response = c.getResponse();
//            System.out.println(response.getBody().getArray().toString());

            recipeList = h.randomRecipe(false, 5, null);


//            recipeList = h.searchRecipes(true, 10, "pasta");
            //intialize adapter
            recipeRVAdapter adapter = new recipeRVAdapter(recipeList);

            rv.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private void initializeAdapter()
    {
        recyclerViewAdapter adapter = new recyclerViewAdapter(ingredientList);

        rv.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment frag = null;

        Toast.makeText(this, "selected item", Toast.LENGTH_SHORT).show();


        if (id == R.id.nav_home) {
            Intent intent = new Intent(this, main.class);
            startActivity(intent);
        } else if (id == R.id.nav_cart) {
            frag = new shoppingCart();
        } else if (id == R.id.nav_pantry) {

        }
         else if (id == R.id.nav_cookbook) {

        }
        else if (id == R.id.nav_calendar) {

        }
        else if (id == R.id.nav_recipe) {

        }
        else if (id == R.id.nav_favorites) {

        }
        else if (id == R.id.nav_settings) {
            frag = new Settings();

        }
        if(frag != null){
            FragmentManager fragman = getSupportFragmentManager();
            fragman.beginTransaction().replace(R.id.mainFrame, frag).commit();

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


