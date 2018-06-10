package hr.murielkamgang.mf.components.Recipe;

import hr.murielkamgang.mf.components.base.BaseContentListContract;
import hr.murielkamgang.mf.data.model.receipe.Recipe;

public interface RecipeContract {

    interface View extends BaseContentListContract.View<Recipe> {

        void openDetailFor(Recipe recipe);
    }

    interface Presenter extends BaseContentListContract.Presenter<View> {

        void delegateToggleFavorite(Recipe recipe);

        void delegateItemClicked(Recipe recipe);
    }
}
