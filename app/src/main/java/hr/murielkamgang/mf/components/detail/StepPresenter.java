package hr.murielkamgang.mf.components.detail;

import java.util.List;

import javax.inject.Inject;

import hr.murielkamgang.mf.components.base.BaseContentListPresenter;
import hr.murielkamgang.mf.data.model.receipe.Recipe;
import hr.murielkamgang.mf.data.model.receipe.Step;
import hr.murielkamgang.mf.data.source.Repository;
import hr.murielkamgang.mf.data.source.base.BaseKVH;
import io.reactivex.Observable;

public class StepPresenter extends BaseContentListPresenter<Step, StepContract.View> implements StepContract.Presenter {

    private final Repository<Recipe, BaseKVH> recipeRepository;
    private final int recipeId;

    private StepContract.onStepClickListener listener;
    private Recipe recipe;

    @Inject
    public StepPresenter(Repository<Recipe, BaseKVH> recipeRepository, int recipeId) {
        this.recipeRepository = recipeRepository;
        this.recipeId = recipeId;
    }

    @Override
    protected Observable<List<Step>> provideLoadObservable(boolean sync) {
        return recipeRepository.getLocalDataSource()
                .getDataAsObservable(new BaseKVH("id", recipeId))
                .map(recipe -> {
                    StepPresenter.this.recipe = recipe;
                    return recipe.getSteps();
                });
    }

    @Override
    public void delegateOnStepClicked(Step step) {
        if (listener != null) {
            listener.onStepClick(recipe, step);
        }
    }

    @Override
    public void setOnStepClickListener(StepContract.onStepClickListener listener) {
        this.listener = listener;
    }
}
