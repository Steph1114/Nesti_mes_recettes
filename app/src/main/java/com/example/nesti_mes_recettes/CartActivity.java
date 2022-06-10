package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.nesti_mes_recettes.model.IngredientViewModel;

import java.util.ArrayList;

import adapter.CartAdapter;
import data.sqlite.TableCart;
import entity.Ingredient;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ListView listView = findViewById(R.id.list_cart);

        IngredientViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(
                this.getApplication()).create(IngredientViewModel.class);
        TableCart model = new TableCart(CartActivity.this);

        AllItemControl(model, listView);

        final Button btnEmpty = (Button) findViewById(R.id.btn_cart);
        btnEmpty.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TableCart model = new TableCart(CartActivity.this);
                model.removeAllItem();
                AllItemControl(model, listView);

            }
        });


        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final Ingredient ing = (Ingredient) parent.getItemAtPosition(position);

                        ing.setChecked(1);
                        model.insertItem(ing);

                        AllItemControl(model,listView);

                    }
                }
        );

    }

    public void AllItemControl(TableCart model,ListView listView) {
        ArrayList<Ingredient> cart = model.getAllItems();
        CartAdapter cartAdapter = new CartAdapter(this, R.layout.line_cart, (ArrayList<Ingredient>) cart);
        listView.setAdapter(cartAdapter);
    }
}