package com.example.nesti_mes_recettes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnEasy = (Button)findViewById(R.id.main_btn_easy);
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Facile à faire");
                alertDialog.setMessage("Voulez-vous choisir une recette facile à faire?");
                alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), btnEasy.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, EasyActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });

        final Button btnTrad = (Button)findViewById(R.id.main_btn_traditional);
        btnTrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Traditionelles");
                alertDialog.setMessage("Voulez-vous choisir une recette traditionelle?");
                alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), btnTrad.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(MainActivity.this, TradActivity.class);
                        startActivity(intent2);
                    }
                });
                alertDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });

        final Button btnSeason = (Button)findViewById(R.id.main_btn_season);
        btnSeason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("C'est de saison");
                alertDialog.setMessage("Voulez-vous choisir une recette de saison?");
                alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), btnSeason.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, SeasonActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });

        final Button btnGluten = (Button)findViewById(R.id.main_btn_gluten);
        btnGluten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Sans gluten");
                alertDialog.setMessage("Voulez-vous choisir une recette sans gluten?");
                alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), btnGluten.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, GlutenActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu pMenu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_general, pMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem pItem) {
        switch (pItem.getItemId()){
            case R.id.menu_search:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                //Log.i("LogNesti", "Menu: Recherche");
                Toast t = Toast.makeText(this, "Menu: Recherche", Toast.LENGTH_SHORT);
                t.show();
                break;
            case R.id.menu_list:
                //Log.i("LogNesti", "Menu: Liste de courses");
                Intent intent2 = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent2);
                Toast t2 = Toast.makeText(this, "Menu: Liste de courses", Toast.LENGTH_SHORT);
                t2.show();
                break;
            case R.id.menu_pres:
                //Log.i("LogNesti", "Menu: Recherche");
                Toast t3 = Toast.makeText(this, "Menu: Présentation", Toast.LENGTH_SHORT);
                t3.show();
                break;
            case R.id.menu_contact:
                Intent intent4 = new Intent(MainActivity.this, TabRecipeActivity.class);
                startActivity(intent4);
                //Log.i("LogNesti", "Menu: Liste de courses");
                Toast t4 = Toast.makeText(this, "Menu: Contact", Toast.LENGTH_SHORT);
                t4.show();
                break;
            case R.id.menu_team:
                //Log.i("LogNesti", "Menu: Recherche");
                Toast t5 = Toast.makeText(this, "Menu: Team", Toast.LENGTH_SHORT);
                t5.show();
                break;
            case R.id.menu_project:
                //Log.i("LogNesti", "Menu: Liste de courses");
                Toast t6 = Toast.makeText(this, "Menu: Projet", Toast.LENGTH_SHORT);
                t6.show();
                break;
        }
        return super.onOptionsItemSelected(pItem);
    }
}