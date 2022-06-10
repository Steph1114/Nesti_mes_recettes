package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.RecipeAdapter;
import entity.Recipe;
import com.example.nesti_mes_recettes.model.EasyViewModel;

public class EasyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);

        EasyViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(
                this.getApplication()).create(EasyViewModel.class);

        viewModel.getRecipes().observe(this, recipes -> {
            ListView listView = (ListView) findViewById(R.id.easy_listView);
            Log.i("TAG", "onCreate: AFFIICHEE");
            RecipeAdapter easyAdapter = new RecipeAdapter(this, R.layout.line_recipe, (ArrayList<Recipe>) recipes);

            listView.setAdapter(easyAdapter);
            Log.i("TAG", "onCreate: AFFIICHEE22");
        });

        ListView listView = findViewById(R.id.easy_listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EasyActivity.this,
                        ListIngActivity.class);

                Recipe recipe = (Recipe) parent.getItemAtPosition(position);

                Bundle extras = new Bundle();
                extras.putString("name_tag", String.valueOf(recipe.getTitle()));
                extras.putInt("id_recipe", (recipe.getIdRecipe()));
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        final Button btnReturn = (Button)findViewById(R.id.return_btn_easy);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyActivity.this.finish();
            }
        });
    }

}