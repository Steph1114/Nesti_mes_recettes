package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nesti_mes_recettes.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import data.sqlite.TableCart;
import entity.Ingredient;

public class IngAdapter extends ArrayAdapter<Ingredient> {

    public IngAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Ingredient> ingredients) {
        super(context, textViewResourceId, ingredients);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View result = convertView;
        if (convertView == null) {
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_ing, parent, false);
        }
        Ingredient ing = getItem(position);

        TextView name = result.findViewById(R.id.ingredient_name);
        name.setText(ing.getName());

        TextView quant = result.findViewById(R.id.ingredient_quantity);
        quant.setText(ing.getQuantity());

        TextView unit = result.findViewById(R.id.ingredient_unit);
        unit.setText(ing.getUnit());

        FloatingActionButton btn_add = result.findViewById(R.id.fab_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addItem(ing);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return result;

    }

    public void addItem(Ingredient item) {
        try {
            TableCart model = new TableCart(getContext());
            model.insertItem(item);
            Toast.makeText(getContext(), item.getName() + " ajouté au panier",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
