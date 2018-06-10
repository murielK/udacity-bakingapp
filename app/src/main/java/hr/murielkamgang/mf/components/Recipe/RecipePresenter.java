package hr.murielkamgang.mf.components.Recipe;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;

import java.util.List;

import javax.inject.Inject;

import hr.murielkamgang.mf.R;
import hr.murielkamgang.mf.components.base.BaseContentListPresenter;
import hr.murielkamgang.mf.components.detail.widget.IngredientsWidgetProvider;
import hr.murielkamgang.mf.data.model.receipe.Recipe;
import hr.murielkamgang.mf.data.source.Repository;
import hr.murielkamgang.mf.data.source.base.BaseKVH;
import hr.murielkamgang.mf.data.source.receipe.RecipeRepository;
import hr.murielkamgang.mf.util.Utils;
import io.reactivex.Observable;
import io.realm.RealmResults;

public class RecipePresenter extends BaseContentListPresenter<Recipe, RecipeContract.View> implements RecipeContract.Presenter {

    private final Repository<Recipe, BaseKVH> recipeRepository;
    private RealmResults<Recipe> recipeRealmResults;

    @Inject
    RecipePresenter(Repository<Recipe, BaseKVH> recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void delegateToggleFavorite(Recipe recipe) {
        ((RecipeRepository) recipeRepository)
                .toggleFavoriteAsObservable(new BaseKVH("id", recipe.getId()))
                .subscribe(aBoolean -> updateIngredientWidget());
    }

    @Override
    public void delegateItemClicked(Recipe recipe) {
        view.openDetailFor(recipe);
    }

    @Override
    protected Observable<List<Recipe>> provideLoadObservable(boolean sync) {
        return recipeRepository.getAllDataAsObservable()
                .filter(recipes -> !recipes.isEmpty())
                .firstElement()
                .toObservable();
    }

    @Override
    protected void handleOnNewItems(List<Recipe> recipes) {
        if (recipeRealmResults != null) {
            recipeRealmResults.removeAllChangeListeners();
        }

        if (view != null) {
            recipeRealmResults = (RealmResults<Recipe>) recipes;
            recipeRealmResults.addChangeListener((recipes1, changeSet) -> {
                Utils.notifyAdapterView(recipes1, changeSet, view);
            });

            view.onLoaded(recipeRealmResults);
        }
    }

    private void updateIngredientWidget() {
        if (view != null) {
            final AppWidgetManager am = AppWidgetManager.getInstance(view.getContext());
            final int[] appWidgetIds = am.getAppWidgetIds(
                    new ComponentName(view.getContext(), IngredientsWidgetProvider.class));

            IngredientsWidgetProvider.update(view.getContext(), am, appWidgetIds);
            am.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.listView);
        }
    }
}
