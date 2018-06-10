package hr.murielkamgang.mf.components.detail;

import hr.murielkamgang.mf.components.base.BaseDialogView;
import hr.murielkamgang.mf.components.base.BasePresenter;
import hr.murielkamgang.mf.data.model.receipe.Recipe;
import hr.murielkamgang.mf.data.model.receipe.Step;

interface RecipeDetailContract {

    interface View extends BaseDialogView {

        void openStepActivityDetailFor(Recipe recipe, Step step);

        void showFragmentDetailFor(Recipe recipe, Step step);

        void onRecipe(Recipe recipe);

    }

    interface Presenter extends BasePresenter<View> {

        void delegateStepClicked(Recipe recipe, Step step);

    }
}
