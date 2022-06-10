package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nesti_mes_recettes.ListIngActivity;
import com.example.nesti_mes_recettes.R;
import com.example.nesti_mes_recettes.TabRecipeActivity;

import java.util.ArrayList;

import entity.Recipe;
import entity.Step;

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    public RecipeAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Recipe> recipes) {
        super(context, textViewResourceId, recipes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View result = convertView;
        if(convertView == null) {
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_recipe, parent, false);
        }
        Recipe one_recipe = getItem(position);

        TextView title = result.findViewById(R.id.line_recipe_title);
        title.setText(one_recipe.getTitle());

        TextView author = result.findViewById(R.id.line_recipe_author);
        author.setText(one_recipe.getAuthor());

        ImageView imageView = result.findViewById(R.id.line_recipe_imageView);
        imageView.setImageResource(one_recipe.getImgId());

        ImageView starView = result.findViewById(R.id.line_recipe_starImg);
        starView.setImageResource(one_recipe.getStarImg());

        result.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ListIngActivity.class);
            String str = title.getText().toString();
            int idRecipe = 0;
            intent.putExtra("name_recipe", str);
            intent.putExtra("id_recipe", idRecipe);
            getContext().startActivity(intent);

        });

        return result;
    }
}
