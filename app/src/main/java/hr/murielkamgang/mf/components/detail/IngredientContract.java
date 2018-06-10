package hr.murielkamgang.mf.components.detail;

import hr.murielkamgang.mf.components.base.BaseContentListContract;
import hr.murielkamgang.mf.data.model.receipe.Ingredient;

public interface IngredientContract {

    interface View extends BaseContentListContract.View<Ingredient> {

    }

    interface Presenter extends BaseContentListContract.Presenter<View> {

    }
}
