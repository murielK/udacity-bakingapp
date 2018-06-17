package hr.murielkamgang.mf.components.detail.stepdetail;

import android.os.Bundle;

import hr.murielkamgang.mf.components.base.BaseDialogView;
import hr.murielkamgang.mf.components.base.BasePresenter;
import hr.murielkamgang.mf.data.model.receipe.Recipe;
import hr.murielkamgang.mf.data.model.receipe.Step;

public interface StepDetailContract {

    interface View extends BaseDialogView {

        void hideGraphicContent();

        void showImage(String imgUrl);

        void showVideo(String videoUrl);

        void onFullDescription(String descriptionUrl);

        void onStep(Step step);

        void onRecipe(Recipe recipe);

    }

    interface Presenter extends BasePresenter<View> {

        void setBundle(Bundle bundle);
    }
}
