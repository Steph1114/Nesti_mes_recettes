package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nesti_mes_recettes.R;
import com.example.nesti_mes_recettes.TabRecipeActivity;

import java.util.ArrayList;

import entity.Recipe;
import entity.Step;

public class StepAdapter extends ArrayAdapter<Step> {

    public StepAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Step> steps) {
        super(context, textViewResourceId, steps);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View result = convertView;
        if(convertView == null) {
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_steps, parent, false);
        }
        Step steps = getItem(position);

        TextView stepsText = result.findViewById(R.id.line_steps_text);
        stepsText.setText(steps.getContentParagraph());

        return result;
    }
}
