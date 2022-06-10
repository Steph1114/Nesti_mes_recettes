package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import adapter.RecipeAdapter;
import entity.Recipe;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String term = this.getIntent().getStringExtra("term");
        TextView text_view = findViewById(R.id.result_txtView_term);
        text_view.setText(term);
        requestApi(term,this);
    }

    private MutableLiveData<List<Recipe>> recipes;

    public void setRecipes(List<Recipe> recipes) {
        this.recipes.setValue(recipes);
    }

    private int getResourceImage(String nameImage) {
        String path = getApplication().getPackageName() + ":drawable/" + nameImage;
        return getApplication().getResources().getIdentifier(path, null, null);
    }

    private void requestApi(String term, ResultActivity resultActivity) {
        String url = "https://razafiasimanana.needemand.com/api2/search/" + term;
        final RequestQueue request_queue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest array_request = new JsonArrayRequest(
                url,
                response -> {
                    ArrayList<Recipe> rec = new ArrayList<>();
                        try {
                            Log.i("TAG", "requestApi: " + response);
                            if (response.length() == 0) {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Veuillez rentrer une recherche valide",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject object_JSON = response.getJSONObject(i);

                                    Recipe r = new Recipe();
                                    String tmp = object_JSON.getString("name_recipe");
                                    r.setTitle(tmp.substring(0, 1).toUpperCase() + tmp.substring(1).replaceAll("_", " "));
                                    r.setAuthor(object_JSON.getString("pseudo"));
                                  //  int img = getResourceImage(object_JSON.getString("i_name"));
                                    int img = getResourceImage(object_JSON.getString("name"));
                                    r.setImgId(img);
                                    int img_star = difficulty(object_JSON.getString("difficulty"));
                                    r.setStarImg(img_star);
                                    r.setCat(object_JSON.getString("name_tag"));
                                    rec.add(r);

                                    Log.i("TAG", "RESPONSEEEE SEARCHHHHH: " + response);
                                }
                            }
                            //Log.i("TAG", "RESPONSEEEE SEARCHHHHH: " + response);

                        } catch (Exception e) {
                            Log.e("LogNesti", "Erreur de conversion du JSON");
                            e.printStackTrace();
                        }

                    //Log.i("tototitityty", "requestApi: "+response);

                    //setRecipes(rec);

                    ListView list_view = (ListView) findViewById(R.id.result_listView);

                    RecipeAdapter resultAdapter = new RecipeAdapter(resultActivity, R.layout.line_result, rec);
                    list_view.setAdapter(resultAdapter);
                },
                error -> {
                    Toast.makeText(
                            getApplicationContext(),
                            "Une erreur est survenue sur l'interrogation de l'api",
                            Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
        );
        request_queue.add(array_request);
    }

    private int difficulty(String diff){
        int new_diff = Integer.parseInt(diff);
        if (new_diff==1){
            return getApplication().getResources().getIdentifier("star_1","drawable",getApplication().getPackageName());
        }else if (new_diff==2){
            return getApplication().getResources().getIdentifier("star_2","drawable",getApplication().getPackageName());
        }else if (new_diff==3){
            return getApplication().getResources().getIdentifier("star_3","drawable",getApplication().getPackageName());
        }else if (new_diff==4){
            return getApplication().getResources().getIdentifier("star_4","drawable",getApplication().getPackageName());
        }else {
            return getApplication().getResources().getIdentifier("star_5","drawable",getApplication().getPackageName());
        }
    }
}