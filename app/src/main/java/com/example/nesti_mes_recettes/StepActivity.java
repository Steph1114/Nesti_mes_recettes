package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import adapter.StepAdapter;
import entity.Step;
import com.example.nesti_mes_recettes.model.StepViewModel;

public class StepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        Toolbar textView = findViewById(R.id.bar_steps);

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name_recipe");
        int idRecipe = extras.getInt("id_recipe");

        textView.setTitle(name);

        StepViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(
                this.getApplication()).create(StepViewModel.class);

        viewModel.getSteps(name).observe(this, steps -> {
            ListView listView = (ListView) findViewById(R.id.listview_steps);
            StepAdapter stepAdapter = new StepAdapter(this, R.layout.line_steps, (ArrayList<Step>) steps);

            listView.setAdapter(stepAdapter);
        });

        FloatingActionButton fab = findViewById(R.id.step_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button to_ing_btn  = findViewById(R.id.to_ings);
        to_ing_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StepActivity.this, ListIngActivity.class);

                intent.putExtras(extras);
                startActivity(intent);
                startActivity(intent);
            }
        });

    }
}