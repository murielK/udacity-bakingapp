package hr.murielkamgang.mf.components.detail;

import hr.murielkamgang.mf.components.base.BaseContentListContract;
import hr.murielkamgang.mf.data.model.receipe.Recipe;
import hr.murielkamgang.mf.data.model.receipe.Step;

public interface StepContract {

    interface View extends BaseContentListContract.View<Step> {

    }

    interface Presenter extends BaseContentListContract.Presenter<View> {

        void delegateOnStepClicked(Step step);

        void setOnStepClickListener(onStepClickListener listener);
    }

    interface onStepClickListener {

        void onStepClick(Recipe recipe, Step step);
    }

}
