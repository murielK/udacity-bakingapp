package hr.murielkamgang.mf.components.detail.widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import hr.murielkamgang.mf.R;
import hr.murielkamgang.mf.data.model.receipe.Ingredient;
import hr.murielkamgang.mf.data.model.receipe.Recipe;
import hr.murielkamgang.mf.data.source.RealmHelper;
import io.realm.Realm;
import io.realm.RealmResults;

class IngredientViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private final DecimalFormat decimalFormat = new DecimalFormat("#.#");
    private final Context context;

    private List<Ingredient> ingredients = new ArrayList<>();

    IngredientViewFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        ingredients.clear();
        final RealmResults<Recipe> recipeRealmResults = RealmHelper.queryEqualTo(Realm.getDefaultInstance(), Recipe.class, "favorite", true);
        if (recipeRealmResults != null && !recipeRealmResults.isEmpty()) {
            ingredients.addAll(Realm.getDefaultInstance().copyFromRealm(recipeRealmResults.first().getIngredients()));
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.ingredient_item);

        final Ingredient ingredient = ingredients.get(position);

        remoteViews.setImageViewResource(R.id.imageView, R.drawable.cicle_indicator);
        remoteViews.setTextViewText(R.id.textIngredient, context.getString(R.string.placeholder_ingredient
                , decimalFormat.format(ingredient.getQuantity()), ingredient.getMeasure(), ingredient.getIngredient()));

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
