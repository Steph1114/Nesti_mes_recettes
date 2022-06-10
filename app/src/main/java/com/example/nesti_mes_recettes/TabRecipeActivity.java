package com.example.nesti_mes_recettes;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.example.nesti_mes_recettes.databinding.ActivityTabRecipeBinding;

public class TabRecipeActivity extends AppCompatActivity {

private ActivityTabRecipeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tab_recipe);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name_recipe");
        String idRecipe = extras.getString("id_recipe");
        TextView textView = findViewById(R.id.tab_title);
        textView.setText(name);

        ViewPager viewPager = binding.viewPager;

        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(TabRecipeActivity.this, BuyActivity.class);
                //startActivity(intent);
                Snackbar.make(view, "Liste de courses", Snackbar.LENGTH_LONG)
                        .setAction("", null).show();
            }
        });
    }
}