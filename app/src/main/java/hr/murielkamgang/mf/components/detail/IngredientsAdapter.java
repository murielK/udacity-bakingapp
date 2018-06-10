package hr.murielkamgang.mf.components.detail;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.BindView;
import hr.murielkamgang.mf.R;
import hr.murielkamgang.mf.components.base.BaseRecyclerViewAdapter;
import hr.murielkamgang.mf.data.model.receipe.Ingredient;

public class IngredientsAdapter extends BaseRecyclerViewAdapter<Ingredient, BaseRecyclerViewAdapter.ItemBaseVH> {

    @NonNull
    @Override
    public ItemBaseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsVh(LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false));
    }

    class IngredientsVh extends IngredientsAdapter.ItemBaseVH {

        private final DecimalFormat decimalFormat = new DecimalFormat("#.#");
        @BindView(R.id.textIngredient)
        TextView textIngredient;

        IngredientsVh(View itemView) {
            super(itemView);
        }

        @Override
        protected void performBinding(Ingredient ingredient) {
            textIngredient.setText(
                    textIngredient.getContext().getString(R.string.placeholder_ingredient
                            , decimalFormat.format(ingredient.getQuantity()), ingredient.getMeasure(), ingredient.getIngredient()));
        }
    }
}
