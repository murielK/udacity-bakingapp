package hr.murielkamgang.mf.data.source.receipe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.murielkamgang.mf.data.model.receipe.Recipe;
import hr.murielkamgang.mf.data.source.RealmHelper;
import hr.murielkamgang.mf.data.source.base.BaseKVH;
import hr.murielkamgang.mf.data.source.base.BaseLocalDataSource;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

public class RecipeLocalDataSource extends BaseLocalDataSource<Recipe, BaseKVH> implements RecipeDataSourceEx, RecipeDataSourceExAsObservable {

    private final Logger logger = LoggerFactory.getLogger(RecipeLocalDataSource.class);

    @Override
    public boolean toggleFavorite(BaseKVH baseKVH) {
        try {
            Realm
                    .getDefaultInstance()
                    .executeTransaction(realm -> {
                        final RealmResults<Recipe> recipeRealmResults = RealmHelper.findAll(realm, Recipe.class);
                        if (recipeRealmResults != null) {
                            for (final Recipe recipe : recipeRealmResults) {
                                if (baseKVH.getFieldValue() == recipe.getId()) {
                                    recipe.setFavorite(!recipe.isFavorite());
                                } else if (recipe.isFavorite()) {
                                    recipe.setFavorite(false);
                                }
                            }
                        }
                    });

        } catch (Exception e) {
            logger.debug("toggleFavorite: " + baseKVH, e);
            return false;
        }
        return true;
    }

    @Override
    public Observable<Boolean> toggleFavoriteAsObservable(BaseKVH baseKVH) {
        return Observable.fromCallable(() -> toggleFavorite(baseKVH));
    }
}
