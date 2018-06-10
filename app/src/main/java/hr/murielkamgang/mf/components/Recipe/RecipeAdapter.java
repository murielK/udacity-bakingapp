package hr.murielkamgang.mf.components.Recipe;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import hr.murielkamgang.mf.R;
import hr.murielkamgang.mf.components.base.BaseRecyclerViewAdapter;
import hr.murielkamgang.mf.data.model.receipe.Recipe;

public class RecipeAdapter extends BaseRecyclerViewAdapter<Recipe, RecipeAdapter.RecipeVH> {

    private final Picasso picasso;

    @Inject
    public RecipeAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @NonNull
    @Override
    public RecipeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false));
    }

    class RecipeVH extends BaseRecyclerViewAdapter<Recipe, RecipeAdapter.RecipeVH>.ItemBaseVH {

        @BindView(R.id.textTitle)
        TextView textTitle;
        @BindView(R.id.imageFavorite)
        ImageView imageFavorite;
        @BindView(R.id.textServing)
        TextView textServing;
        @BindView(R.id.imageRecipe)
        ImageView imageView;

        RecipeVH(View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(RecipeAdapter.this, RecipeVH.this, getAdapterPosition());
                }
            });
        }

        @Override
        protected void performBinding(Recipe recipe) {
            textTitle.setText(recipe.getName());
            imageFavorite.setImageResource(recipe.isFavorite() ? R.drawable.ic_favourite_on : R.drawable.ic_favourite_off);
            textServing.setText(textServing.getContext().getString(R.string.placeholder_serving, recipe.getServings()));
            if (recipe.getImage() != null && !recipe.getImage().isEmpty()) {
                picasso.load(recipe.getImage())
                        .fit()
                        .centerCrop()
                        .into(imageView);
            }
        }

        @OnClick(R.id.imageFavorite)
        void onClick(View view) {
            if (listener != null) {
                listener.onViewClick(RecipeAdapter.this, this, view, getAdapterPosition());
            }
        }
    }
}
