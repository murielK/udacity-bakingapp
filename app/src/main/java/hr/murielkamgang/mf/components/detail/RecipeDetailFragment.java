package hr.murielkamgang.mf.components.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import hr.murielkamgang.mf.R;
import hr.murielkamgang.mf.components.base.BaseDialogFragment;
import hr.murielkamgang.mf.components.detail.stepdetail.StepDetailActivity;
import hr.murielkamgang.mf.components.detail.stepdetail.StepDetailsFragment;
import hr.murielkamgang.mf.data.model.receipe.Recipe;
import hr.murielkamgang.mf.data.model.receipe.Step;

public class RecipeDetailFragment extends BaseDialogFragment<RecipeDetailContract.View, RecipeDetailContract.Presenter> implements RecipeDetailContract.View {

    @BindView(R.id.viewPage)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.imageViewRecipe)
    ImageView imageViewRecipe;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolBarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;


    @Inject
    Picasso picasso;
    @Inject
    RecipeDetailContract.Presenter presenter;
    @Inject
    StepFragment stepFragment;
    @Inject
    IngredientsFragment ingredientsFragment;

    @Inject
    public RecipeDetailFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter != null) {
            presenter.setView(this);

            if (stepFragment.presenter != null) {
                stepFragment.presenter.setOnStepClickListener(
                        (recipe, step) -> presenter.delegateStepClicked(recipe, step));
            }
        }
    }

    @Override
    protected void onPostViewCreate(View view) {
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new ViewPageAdapter(getChildFragmentManager()));
        collapsingToolbarLayout.setTitleEnabled(false);
        final RecipeDetailActivity recipeDetailActivity = (RecipeDetailActivity) getActivity();
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        recipeDetailActivity.setSupportActionBar(toolbar);
        recipeDetailActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected RecipeDetailContract.Presenter providePresenter() {
        return presenter;
    }

    @Override
    protected int getResourceView() {
        return R.layout.recipe_detail_fragment;
    }

    @Override
    public void openStepActivityDetailFor(Recipe recipe, Step step) {
        StepDetailActivity.view(getContext(), recipe, step);
    }

    @Override
    public void showFragmentDetailFor(Recipe recipe, Step step) {
        final Bundle bundle = new Bundle();
        bundle.putInt(StepDetailActivity.EXTRA_STEP_ID_KEY, step.getId());
        bundle.putInt(RecipeDetailActivity.EXTRA_RECIPE_ID_KEY, recipe.getId());
        final StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();

        stepDetailsFragment.setArguments(bundle);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.containerStep, stepDetailsFragment)
                .commit();

    }

    @Override
    public void onRecipe(Recipe recipe) {
        final RecipeDetailActivity recipeDetailActivity = (RecipeDetailActivity) getActivity();
        recipeDetailActivity.getSupportActionBar().setTitle(recipe.getName());
        recipeDetailActivity.getSupportActionBar().setSubtitle(getString(R.string.placeholder_serving, recipe.getServings()));
        if (recipe.getImage() != null && !recipe.getImage().isEmpty()) {
            picasso.load(recipe.getImage())
                    .fit()
                    .centerCrop()
                    .into(imageViewRecipe);
        }
    }

    class ViewPageAdapter extends FragmentPagerAdapter {

        ViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ingredientsFragment;
                case 1:
                    return stepFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.text_ingredients);
                case 1:
                    return getString(R.string.text_steps);
            }
            return null;
        }
    }
}
