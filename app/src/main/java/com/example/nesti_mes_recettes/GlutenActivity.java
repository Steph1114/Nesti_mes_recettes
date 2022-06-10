package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.RecipeAdapter;
import entity.Recipe;
import com.example.nesti_mes_recettes.model.GlutenViewModel;

public class GlutenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gluten);

        GlutenViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(GlutenViewModel.class);
        viewModel.getRecipes().observe(this, recipes -> {
            ListView list_view = (ListView) findViewById(R.id.gluten_listView);
            RecipeAdapter adapter = new RecipeAdapter(this, R.layout.line_recipe, (ArrayList<Recipe>) recipes);
            list_view.setAdapter(adapter);
        });

        final Button btnReturn = (Button)findViewById(R.id.return_btn_gluten);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlutenActivity.this.finish();
            }
        });
    }

}