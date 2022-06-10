package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import adapter.IngAdapter;
import entity.Ingredient;
import com.example.nesti_mes_recettes.model.IngredientViewModel;

public class ListIngActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ing);

        Bundle extras = getIntent().getExtras();
        /*String name = "";
        extras.putString("recipe_name", name);*/
        String ingName = extras.getString("product_name");
        String name = extras.getString("name_recipe");
        int idRecipe = extras.getInt("id_recipe");

        //extras.putInt("id_recipe", idRecipe);
        Log.i("TAG", "EXTRAAAAAAAAAAAAA: " + extras);
        Log.i("TAG", "NAAAAAMMMMEEEEE: " + name);
        Log.i("TAG", "IIIIIIIIIIIIDDDDDDDDDDDDDDDDDDDd: " + idRecipe);


        Toolbar titleTab = (Toolbar) findViewById(R.id.bar_ing);
        titleTab.setTitle(name);

        IngredientViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(
                this.getApplication()).create(IngredientViewModel.class);

        viewModel.getIngredients(name).observe(this, ingredients -> {
            Log.i("TAG", "IIIIIIIIIIIIDDDDDDDDDDDDDDDDDDDd: " + idRecipe);
            ListView listView = (ListView) findViewById(R.id.listview_ing);
            IngAdapter ingredientAdapter = new IngAdapter(this, R.layout.line_recipe, (ArrayList<Ingredient>) ingredients);
            listView.setAdapter(ingredientAdapter);
        });


        FloatingActionButton fab = findViewById(R.id.ing_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListIngActivity.this, CartActivity.class);
                extras.putString("product_name", ingName);

                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        Button prep_to_step  = findViewById(R.id.to_steps);
        prep_to_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ListIngActivity.this, StepActivity.class);
                extras.putString("name_recipe", name);
                extras.putInt("id_recipe", idRecipe);

                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        }
}