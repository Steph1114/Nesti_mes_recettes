package com.example.nesti_mes_recettes.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import entity.Ingredient;

public class IngredientViewModel extends AndroidViewModel {
    private MutableLiveData<List<Ingredient>> ingredients;

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients.setValue(ingredients);
    }

    public IngredientViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Ingredient>> getIngredients(String nameRecipe) {
        if (ingredients == null) {
            ingredients = new MutableLiveData<List<Ingredient>>();
            loadIng(nameRecipe);
        }
        return ingredients;
    }

    private void loadIng(String nameRecipe) {
        requestApi(nameRecipe);
    }

    protected void requestApi(String nameRecipe) {
        String url = "https://razafiasimanana.needemand.com/api2/ingredients/" + nameRecipe;
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplication());
        Log.i("TAG", "IIIIDDDDDDDDDd2: " + nameRecipe);

//        final RequestQueue request_queue = Volley.newRequestQueue(getApplication());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                url,
                response -> {
                    Log.i("TAG", "RESPONSEEE: " + response);
                    ArrayList<Ingredient> ing_list = new ArrayList<>();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Ingredient ing = new Ingredient();
                            ing.setName(jsonObject.getString("product_name").replaceAll("_", " "));
                            ing.setQuantity(jsonObject.getString("quantity"));
                            ing.setUnit(jsonObject.getString("name_measurement_unit"));
                            ing_list.add(ing);
                        }
                        /*JSONArray jsonArray = response.getJSONArray(0);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Ingredient ing = new Ingredient();
                            ing.setName(jsonArray.getString(0));
                            ing.setQuantity(jsonArray.getString(2));
                            ingredients.add(ing);
                            Log.i("TAG", "BLOUUUUUUUUUUUUUUUUUUUUUUUUU: " + ing);
                            Log.i("TAG", "BLAAAAAAAAAAAAAAAAAAAAAAAAA: " + ingredients);
                        }*/
                        /*JSONArray jsonArray = response.getJSONArray("ingredients");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Ingredient ing = new Ingredient();
                            ing.setName(jsonArray.getString(0));
                            ing.setQuantity(jsonArray.getString(2));
                            ingredients.add(ing);
                            Log.i("TAG", "BLOUUUUUUUUUUUUUUUUUUUUUUUUU: " + ing);
                            Log.i("TAG", "BLAAAAAAAAAAAAAAAAAAAAAAAAA: " + ingredients);
                        }*/
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    setIngredients(ing_list);
                },
                error -> Log.i("TAG", "ERRRRRRRRRRROOOOOOOOOORRRRRRRR: " + error)
        );
        requestQueue.add(jsonArrayRequest);

        /*JsonObjectRequest array_request = new JsonObjectRequest(
                url,
                response -> {
                    ArrayList<Ingredient> ingredients = new ArrayList<>();

                    try {
                        //JSONArray array_ings = response.getJSONArray("ingredients");
                        Log.i("TAG", "requestApi: " + response.toString());
                        *//*for (int i = 0; i < response.length(); i++) {
                            JSONObject object_JSON = response.getJSONObject(i);
                            Log.i("TAG", "onResponse: " + response);
                            Ingredient ing = new Ingredient();
                            ing.setName(object_JSON.getString("product_name"));
                            ing.setOrder(object_JSON.getInt("r_order"));
                            ing.setUnit(object_JSON.getString("name"));
                            ing.setQuantity(object_JSON.getString("quantity"));
                            ingredients.add(ing);
                        }*//*
                    } catch (Exception e) {
                        Log.e("LogNesti", "Erreur de conversion du JSON");
                        e.printStackTrace();
                    }
                    setIngredients(ingredients);
                },
                (Response.ErrorListener) error -> {
                    Toast.makeText(
                            getApplication().getApplicationContext(),
                            "Une erreur est survenue sur l'intérrogation de l'API",
                            Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
        );
        request_queue.add((array_request));*/
    }

    //public void readJSON(JSONObject response) {
        //Log.i("TAG", "readJSON: " + response.toString());
    //}

}
