package hr.murielkamgang.mf.components.detail.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import hr.murielkamgang.mf.R;
import hr.murielkamgang.mf.data.model.receipe.Recipe;
import hr.murielkamgang.mf.data.source.RealmHelper;
import io.realm.Realm;
import io.realm.RealmResults;

public class IngredientsWidgetProvider extends AppWidgetProvider {


    public static void update(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (final int appWidgetId : appWidgetIds) {

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.ingredient_widget);
            final RealmResults<Recipe> recipeRealmResults = RealmHelper.queryEqualTo(Realm.getDefaultInstance(), Recipe.class, "favorite", true);
            if (recipeRealmResults != null && !recipeRealmResults.isEmpty()) {
                final Recipe recipe = recipeRealmResults.first();
                remoteViews.setTextViewText(R.id.textTitle, recipe.getName());
                remoteViews.setTextViewText(R.id.textSubTitle, context.getString(R.string.placeholder_serving, recipe.getServings()));
            } else {
                remoteViews.setTextViewText(R.id.textTitle, context.getString(R.string.error_msg_recipe_not_int_favorite));
                remoteViews.setTextViewText(R.id.textSubTitle, "");
            }

            final Intent intent = new Intent(context, IngredientWidgetService.class);

            remoteViews.setRemoteAdapter(R.id.listView, intent);

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        update(context, appWidgetManager, appWidgetIds);
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
}
