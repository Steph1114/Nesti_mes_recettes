package com.example.nesti_mes_recettes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


/**
 * Gestion ouverture/fermeture du menu general
 * @param pMenu Menu
 * @return boolean true
 */
    public boolean onCreateOptionsMenu(Menu pMenu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menugeneral, pMenu);
        return true;
    }


    /**
     * Lance une action en focntion de l'élément du menu selectionné
     * @param pItem MenuItem
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem pItem) {
        switch (pItem.getItemId()){
            case R.id.menu_search:
                Log.i("LogNesti", "Menu : Recherche");
                break;
            case R.id.menu_list:
                Log.i("LogNesti", "Menu : Liste de course");
                break;

            case R.id.menu_presentation:
                Log.i("LogNesti", "Menu : Présentation");
                break;
        }
        return true;
    }



//    public void toastMsg(String msg) {
//        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
//        toast.show();
//    }
//
//    public void displayToastMsg(View v) {
//        toastMsg("Je suis entrain de chercher");
//    }

    //Snackbar instead of Toasts
//    Snackbar.make(contextView, "Text label", Snackbar.LENGTH_LONG)
//            .setAction("Action") {
//        // Responds to click on the action
//    }
//    .show()

    final Button btnEasy = (Button)findViewById(R.id.buttonMainEasy);

    btnEasy.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v)
//           Affiche un message
        Toast toast = Toast.makeText(getApplicationContext(), btnEasy.getText(), Toast.LENGTH_SHORT);
        toast.show();
    };



}