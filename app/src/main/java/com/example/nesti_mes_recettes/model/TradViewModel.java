package com.example.nesti_mes_recettes.model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import entity.Recipe;

public class TradViewModel extends AndroidViewModel {

    private MutableLiveData<List<Recipe>> recipes;

    public void setRecipes(List<Recipe> recipes) {
        this.recipes.setValue(recipes);
    }

    public TradViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Recipe>> getRecipes() {

        if (recipes == null) {
            recipes = new MutableLiveData<List<Recipe>>();
            loadRecipes();
        }
        return recipes;
    }

    private void loadRecipes() {
        requestApi();
    }

    private int getResourceImage(String nameImage) {
        String path = getApplication().getPackageName() + ":drawable/" + nameImage;
        return getApplication().getResources().getIdentifier(path, null, null);
    }

    private void requestApi() {
        Log.i("TAG", "requestApi: TOTOOOOOOOO");
        //ArrayList<Recipe> recipes = new ArrayList<>();
        String url = "https://razafiasimanana.needemand.com/api2/recipes/traditionnel";
        final RequestQueue request_queue = Volley.newRequestQueue(getApplication().getApplicationContext());
        JsonArrayRequest array_request = new JsonArrayRequest(
                url,
                response -> {
                    Log.i("TAG", "onResponse: " + response);
                    ArrayList<Recipe> rec = new ArrayList<>();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject object_JSON = response.getJSONObject(i);

                            Recipe r = new Recipe();
                            r.setTitle(object_JSON.getString("name_recipe").replaceAll("_", " "));
                            r.setAuthor(object_JSON.getString("pseudo"));
                            int img = getResourceImage(object_JSON.getString("id_image"));
                            r.setImgId(img);
                            int img_star = difficulty(object_JSON.getString("difficulty"));
                            r.setStarImg(img_star);
                            r.setCat(object_JSON.getString("name_tag"));
                            rec.add(r);
                        }
                    } catch (Exception e) {
                        Log.e("LogNesti", "Erreur de conversion du JSON");
                        e.printStackTrace();
                    }
                    setRecipes(rec);
                },
                error -> {
                    Toast.makeText(
                            getApplication().getApplicationContext(),
                            "Une erreur est survenue sur l'intérrogation de l'API",
                            Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
        );
        request_queue.add((array_request));
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
