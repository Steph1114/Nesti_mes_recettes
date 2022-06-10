package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.nesti_mes_recettes.model.EasyViewModel;
import com.example.nesti_mes_recettes.model.TradViewModel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import adapter.RecipeAdapter;
import entity.Recipe;

public class TradActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trad);


        TradViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(
                this.getApplication()).create(TradViewModel.class);

        viewModel.getRecipes().observe(this, recipes -> {
            ListView listView = (ListView) findViewById(R.id.trad_listView);
            Log.i("TAG", "onCreate: AFFFFFFFFFFFFIIIICHHEEEEEEEEEee");
            RecipeAdapter easyAdapter = new RecipeAdapter(this, R.layout.line_recipe, (ArrayList<Recipe>) recipes);

            listView.setAdapter(easyAdapter);
            Log.i("TAG", "onCreate: AFFFFFFFFFFFFIIIICHHEEEEEEEEEee222222222");
        });

        ListView list_view = (ListView)findViewById(R.id.trad_listView);
        //list_view.setAdapter(trad_recipes);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TradActivity.this,
                        ListIngActivity.class);

                Recipe recipe = (Recipe) parent.getItemAtPosition(position);

                Bundle extras = new Bundle();
                extras.putString("name_recipe", String.valueOf(recipe.getTitle()));
                extras.putInt("id_recipe", (recipe.getIdRecipe()));
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        final Button btnReturn = (Button)findViewById(R.id.return_btn_trad);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TradActivity.this.finish();
            }
        });
    }
}