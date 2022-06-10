package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText edit_text = findViewById(R.id.search_editTxt_recipe);

        final Button okBtn = (Button)findViewById(R.id.search_btn_ok);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String term = edit_text.getText().toString();
                Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
                intent.putExtra("term", term);
                startActivity(intent);
            }
        });
    }
}