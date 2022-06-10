package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nesti_mes_recettes.R;

import java.util.ArrayList;
import java.util.List;

import entity.Ingredient;

public class CartAdapter extends ArrayAdapter<Ingredient> {

    public CartAdapter(@NonNull Context context, int textViewResourceId, @NonNull ArrayList<Ingredient> ingredients) {
        super(context, textViewResourceId, ingredients);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View result = convertView;
        if (convertView == null) {
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_cart, parent, false);
        }
        Ingredient ing = getItem(position);



        CheckedTextView checked = (CheckedTextView)result.findViewById(R.id.cart_txt);
        checked.setText(ing.getName());

        checked.setChecked(ing.getChecked() == 1);

        return result;

    }
}
