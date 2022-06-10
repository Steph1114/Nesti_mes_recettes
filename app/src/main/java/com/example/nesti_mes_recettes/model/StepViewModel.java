package com.example.nesti_mes_recettes.model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import entity.Step;

public class StepViewModel extends AndroidViewModel {

    private MutableLiveData<List<Step>> steps;

    public void setSteps(List<Step> steps) {
        this.steps.setValue(steps);
    }

    public StepViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Step>> getSteps(String nameRecipe) {
        if (steps == null) {
            steps = new MutableLiveData<List<Step>>();
            loadSteps(nameRecipe);
        }
        return steps;
    }

    private void loadSteps(String nameRecipe) {
        requestApi(nameRecipe);
    }

    public void requestApi(String nameRecipe) {
        String url = "https://razafiasimanana.needemand.com/api2/content/" + nameRecipe;
        final RequestQueue request_queue = Volley.newRequestQueue(getApplication().getApplicationContext());
        JsonArrayRequest array_request = new JsonArrayRequest(
                url,
                response -> {
                    Log.i("TAG", "RESPONSE STEPPPPPPPS: " + response);
                    ArrayList<Step> steps = new ArrayList<>();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject object_JSON = response.getJSONObject(i);
                            Step step = new Step();
                            step.setOrderParagraph(object_JSON.getInt("order_paragraph"));
                            step.setContentParagraph(object_JSON.getString("content"));
                            steps.add(step);
                        }
                    } catch (Exception e) {
                        Log.e("LogNesti", "Erreur de conversion du JSON");
                        e.printStackTrace();
                    }
                    setSteps(steps);
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
}
