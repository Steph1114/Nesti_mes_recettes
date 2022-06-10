package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.nesti_mes_recettes.model.EasyViewModel;
import com.example.nesti_mes_recettes.model.SeasonViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import adapter.RecipeAdapter;
import entity.Recipe;

public class SeasonActivity extends AppCompatActivity {

    private String readJson(String pFileJsonName) {
        StringBuilder builder = new StringBuilder();
        AssetManager asset_manager = this.getAssets();
        InputStreamReader isr;

        BufferedReader data;

        try {
            isr = new InputStreamReader(asset_manager.open(pFileJsonName));
            data = new BufferedReader(isr);
            String line;
            while((line = data.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            Log.e("LogNesti", "Erreur de lecture du Json");
            e.printStackTrace();
        }
        return builder.toString();
    }

    private ArrayList<Recipe> readJSONRecipe(String pFileJsonName) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        String stringJSON = this.readJson(pFileJsonName);

        try {
            JSONArray tableau_JSON = new JSONArray(stringJSON);
            Log.i("LogNesti", "Nombre d'enregistrements :" + tableau_JSON.length());

            for (int i = 0; i < tableau_JSON.length(); i++) {
                JSONObject object_JSON = tableau_JSON.getJSONObject(i);

                Recipe r = new Recipe();
                r.setCat(object_JSON.getString("name_tag"));
                r.setTitle(object_JSON.getString("name_recipe"));
                r.setAuthor(object_JSON.getString("pseudo"));
                int img = R.drawable.r_default; //this.getResources().getIdentifier("r_default", "drawable", getPackageName());
                r.setImgId(img);
                r.setDifficulty(object_JSON.getInt("difficulty"));

                recipes.add(r);


//                Recipe r = new Recipe();
//                r.setCat(object_JSON.getString("cat"));
//                r.setTitle(object_JSON.getString("title"));
//                r.setAuthor(object_JSON.getString("author"));
//                int img = R.drawable.r_default; //this.getResources().getIdentifier("r_default", "drawable", getPackageName());
//                r.setImgId(img);
//                r.setDifficulty(object_JSON.getInt("diff"));
//
//                recipes.add(r);
            }
        } catch (Exception e) {
            Log.e("LogNesti", "Erreur de conversion du JSON");
            e.printStackTrace();
        }
        return recipes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season);

        SeasonViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(
                this.getApplication()).create(SeasonViewModel.class);

        viewModel.getRecipes().observe(this, recipes -> {
            ListView listView = (ListView) findViewById(R.id.season_listView);
            Log.i("TAG", "onCreate: AFFFFFFFFFFFFIIIICHHEEEEEEEEEee");
            RecipeAdapter seasonAdapter = new RecipeAdapter(this, R.layout.line_recipe, (ArrayList<Recipe>) recipes);

            listView.setAdapter(seasonAdapter);
            Log.i("TAG", "onCreate: AFFFFFFFFFFFFIIIICHHEEEEEEEEEee222222222");
        });


        /*ArrayList<Recipe> season_recipes = readJSONRecipe("season.json");
        Log.i("LogNesti", "Tableau reçu " + season_recipes.size());

        ListView list_view = (ListView) findViewById(R.id.season_listView);
        RecipeAdapter adapter = new RecipeAdapter(this, R.layout.line_recipe, season_recipes);
        list_view.setAdapter(adapter);*/

        ListView list_view = (ListView) findViewById(R.id.season_listView);

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SeasonActivity.this,
                        ListIngActivity.class);

                Recipe recipe = (Recipe) parent.getItemAtPosition(position);

                Bundle extras = new Bundle();
                extras.putString("name_recipe", String.valueOf(recipe.getTitle()));
                extras.putInt("id_recipe", (recipe.getIdRecipe()));
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        final Button btnReturn = (Button)findViewById(R.id.return_btn_season);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeasonActivity.this.finish();
            }
        });
    }
}