package hr.murielkamgang.mf.components.detail;

import java.util.List;

import javax.inject.Inject;

import hr.murielkamgang.mf.components.base.BaseContentListPresenter;
import hr.murielkamgang.mf.data.model.receipe.Ingredient;
import hr.murielkamgang.mf.data.model.receipe.Recipe;
import hr.murielkamgang.mf.data.source.Repository;
import hr.murielkamgang.mf.data.source.base.BaseKVH;
import io.reactivex.Observable;

public class IngredientPresenter extends BaseContentListPresenter<Ingredient, IngredientContract.View> implements IngredientContract.Presenter {

    private final Repository<Recipe, BaseKVH> recipeRepository;
    private final int recipeId;

    @Inject
    public IngredientPresenter(Repository<Recipe, BaseKVH> recipeRepository, int recipeId) {
        this.recipeRepository = recipeRepository;
        this.recipeId = recipeId;
    }

    @Override
    protected Observable<List<Ingredient>> provideLoadObservable(boolean sync) {
        return recipeRepository.getLocalDataSource()
                .getDataAsObservable(new BaseKVH("id", recipeId))
                .map(Recipe::getIngredients);
    }

}
