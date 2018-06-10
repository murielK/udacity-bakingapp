package hr.murielkamgang.mf.components.detail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import hr.murielkamgang.mf.R;
import hr.murielkamgang.mf.components.base.BaseFragment;
import hr.murielkamgang.mf.components.base.BaseToolBarActivity;
import hr.murielkamgang.mf.data.model.receipe.Recipe;

public class RecipeDetailActivity extends BaseToolBarActivity {

    public static final String EXTRA_RECIPE_ID_KEY = "EXTRA_RECIPE_ID_KEY";

    @Inject
    RecipeDetailFragment recipeDetailFragment;

    public static void view(Context context, Recipe recipe) {
        final Intent intent = new Intent(context, RecipeDetailActivity.class);
        intent.putExtra(EXTRA_RECIPE_ID_KEY, recipe.getId());
        context.startActivity(intent);
    }

    @Override
    protected int provideLayoutRes() {
        return R.layout.base_activity;
    }

    @Override
    protected BaseFragment provideFragment() {
        return recipeDetailFragment;
    }

    @Override
    protected int provideToolbarTitleResId() {
        return 0;
    }

    @Override
    protected Class<? extends AppCompatActivity> provideParentActivityClass() {
        return null;
    }
}
