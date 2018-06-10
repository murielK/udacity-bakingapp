package hr.murielkamgang.mf.components.detail.stepdetail;

import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import hr.murielkamgang.mf.R;
import hr.murielkamgang.mf.data.model.receipe.Recipe;
import hr.murielkamgang.mf.data.model.receipe.Step;
import hr.murielkamgang.mf.data.source.Repository;
import hr.murielkamgang.mf.data.source.base.BaseKVH;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class StepDetailsPresenter implements StepDetailContract.Presenter {

    private final Repository<Recipe, BaseKVH> recipeRepository;
    private final int recipeId;

    private int stepId;
    private Disposable disposable;
    private Recipe recipe;
    private Step step;
    private StepDetailContract.View view;

    @Inject
    public StepDetailsPresenter(Repository<Recipe, BaseKVH> recipeRepository, int recipeId) {
        this.recipeRepository = recipeRepository;
        this.recipeId = recipeId;
    }

    @Override
    public void onDestroy() {
        this.view = null;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void setView(StepDetailContract.View view) {
        this.view = view;
        loadRecipe();
    }

    private void loadRecipe() {
        disposable = recipeRepository.getLocalDataSource()
                .getDataAsObservable(new BaseKVH("id", recipeId))
                .map((Function<Recipe, List<Step>>) recipe -> {
                    StepDetailsPresenter.this.recipe = recipe;
                    return recipe.getSteps();
                })
                .flatMapIterable((Function<List<Step>, Iterable<Step>>) steps -> steps)
                .filter(step -> step.getId() == stepId)
                .firstOrError()
                .subscribe(step -> {
                    StepDetailsPresenter.this.step = step;
                    if (view != null) {
                        if ((step.getVideoURL() != null && !step.getVideoURL().isEmpty())) {
                            view.showVideo(step.getVideoURL());
                        } else if (step.getThumbnailURL() != null && step.getThumbnailURL().endsWith(".mp4")) {
                            view.showVideo(step.getThumbnailURL());
                        } else if (step.getThumbnailURL() != null && !step.getThumbnailURL().isEmpty()) {
                            view.showImage(step.getThumbnailURL());
                        } else {
                            view.hideGraphicContent();
                        }

                        view.onFullDescription(step.getDescription());
                        view.onStep(view.getContext().getString(R.string.placeholder_step, step.getId() + 1));
                    }
                }, throwable -> {
                    if (view != null) {
                        view.toast(R.string.error_msg_unable_to_load_step);
                    }
                });
    }

    @Override
    public void setBundle(Bundle bundle) {
        stepId = bundle.getInt(StepDetailActivity.EXTRA_STEP_ID_KEY, -1);
    }
}
