package hr.murielkamgang.mf.components.Recipe;

import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import hr.murielkamgang.mf.R;
import hr.murielkamgang.mf.components.base.BaseFragment;
import hr.murielkamgang.mf.components.base.BaseToolBarActivity;

public class RecipeActivity extends BaseToolBarActivity {

    @Inject
    RecipeFragment recipeFragment;

    @Override
    protected int provideLayoutRes() {
        return R.layout.base_toolbar_activity;
    }

    @Override
    protected BaseFragment provideFragment() {
        return recipeFragment;
    }

    @Override
    protected int provideToolbarTitleResId() {
        return R.string.title_recipes;
    }

    @Override
    protected Class<? extends AppCompatActivity> provideParentActivityClass() {
        return null;
    }
}
