package hr.murielkamgang.mf.data.source.receipe;

import javax.inject.Inject;

import hr.murielkamgang.mf.data.model.receipe.Recipe;
import hr.murielkamgang.mf.data.source.base.BaseKVH;
import hr.murielkamgang.mf.data.source.base.BaseLocalDataSource;
import hr.murielkamgang.mf.data.source.base.BaseRemoteDataSource;
import hr.murielkamgang.mf.data.source.base.BaseRepository;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecipeRepository extends BaseRepository<Recipe, BaseKVH> implements RecipeDataSourceExAsObservable {

    private final RecipeLocalDataSource recipeLocalDataSource;
    private final RecipeRemoteDataSource recipeRemoteDataSource;

    @Inject
    public RecipeRepository(RecipeLocalDataSource recipeLocalDataSource, RecipeRemoteDataSource recipeRemoteDataSource) {
        this.recipeLocalDataSource = recipeLocalDataSource;
        this.recipeRemoteDataSource = recipeRemoteDataSource;
    }

    @Override
    public BaseLocalDataSource<Recipe, BaseKVH> getLocalDataSource() {
        return recipeLocalDataSource;
    }

    @Override
    public BaseRemoteDataSource<Recipe, BaseKVH> getRemoteDataSource() {
        return recipeRemoteDataSource;
    }


    @Override
    public Observable<Boolean> toggleFavoriteAsObservable(BaseKVH baseKVH) {
        return recipeLocalDataSource.toggleFavoriteAsObservable(baseKVH)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
