package hr.murielkamgang.mf.components.detail.stepdetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import hr.murielkamgang.mf.R;
import hr.murielkamgang.mf.components.base.BaseFragment;
import hr.murielkamgang.mf.components.base.BaseToolBarActivity;
import hr.murielkamgang.mf.components.detail.RecipeDetailActivity;
import hr.murielkamgang.mf.data.model.receipe.Recipe;
import hr.murielkamgang.mf.data.model.receipe.Step;

public class StepDetailActivity extends BaseToolBarActivity {

    public static final String EXTRA_STEP_ID_KEY = "EXTRA_STEP_ID_KEY";

    @Inject
    StepDetailsFragment stepDetailsFragment;

    public static void view(Context context, Recipe recipe, Step step) {
        final Intent intent = new Intent(context, StepDetailActivity.class);

        intent.putExtra(RecipeDetailActivity.EXTRA_RECIPE_ID_KEY, recipe.getId());
        intent.putExtra(EXTRA_STEP_ID_KEY, step.getId());

        context.startActivity(intent);
    }

    @Override
    protected int provideLayoutRes() {
        return R.layout.base_toolbar_activity;
    }

    @Override
    protected BaseFragment provideFragment() {
        stepDetailsFragment.setArguments(getIntent().getExtras());
        return stepDetailsFragment;
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
