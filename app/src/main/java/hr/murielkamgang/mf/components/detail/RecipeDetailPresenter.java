package hr.murielkamgang.mf.components.detail;

import javax.inject.Inject;

import hr.murielkamgang.mf.R;
import hr.murielkamgang.mf.data.model.receipe.Recipe;
import hr.murielkamgang.mf.data.model.receipe.Step;
import hr.murielkamgang.mf.data.source.Repository;
import hr.murielkamgang.mf.data.source.base.BaseKVH;
import io.reactivex.disposables.Disposable;

public class RecipeDetailPresenter implements RecipeDetailContract.Presenter {

    private final Repository<Recipe, BaseKVH> recipeRepository;
    private final int recipeId;

    private Disposable disposable;
    private RecipeDetailContract.View view;

    @Inject
    public RecipeDetailPresenter(Repository<Recipe, BaseKVH> recipeRepository, int recipeId) {
        this.recipeRepository = recipeRepository;
        this.recipeId = recipeId;
    }

    @Override
    public void delegateStepClicked(Recipe recipe, Step step) {
        view.openStepActivityDetailFor(recipe, step);//todo for now
    }

    @Override
    public void onDestroy() {
        this.view = null;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void setView(RecipeDetailContract.View view) {
        this.view = view;
        loadRecipeAndPopulateTitle();
    }

    private void loadRecipeAndPopulateTitle() {
        disposable = recipeRepository.getLocalDataSource()
                .getDataAsObservable(new BaseKVH("id", recipeId))
                .subscribe(recipe -> {
                    if (view != null) {
                        view.onRecipe(recipe);
                    }
                }, throwable -> {
                    if (view != null) {
                        view.toast(R.string.error_msg_unable_to_load_recipe);
                    }
                });
    }
}
