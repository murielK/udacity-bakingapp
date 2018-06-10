package hr.murielkamgang.mf.components.Recipe;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import javax.inject.Inject;

import hr.murielkamgang.mf.R;
import hr.murielkamgang.mf.components.base.BaseContentFragment;
import hr.murielkamgang.mf.components.base.GridSpacingItemDecoration;
import hr.murielkamgang.mf.components.detail.RecipeDetailActivity;
import hr.murielkamgang.mf.data.model.receipe.Recipe;

public class RecipeFragment extends BaseContentFragment<Recipe, RecipeContract.View, RecipeContract.Presenter> implements RecipeContract.View {

    @Inject
    RecipeContract.Presenter presenter;
    @Inject
    RecipeAdapter recipeAdapter;

    @Inject
    public RecipeFragment() {
    }

    @Override
    protected void onItemClicked(Recipe recipe) {
        if (presenter != null) {
            presenter.delegateItemClicked(recipe);
        }
    }

    @Override
    protected void onViewClicked(View view, Recipe recipe) {
        if (presenter != null) {
            presenter.delegateToggleFavorite(recipe);
        }
    }

    @Override
    protected void initRecyclerView() {
        swipeRefreshLayout.setEnabled(false);
        final int spanCount = getResources().getInteger(R.integer.span_count);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        recyclerView.setAdapter(recipeAdapter);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing), true));
    }

    @Override
    protected RecipeContract.Presenter providePresenter() {
        return presenter;
    }

    @Override
    protected int getResourceView() {
        return R.layout.default_recycler_view_fragment;
    }

    @Override
    public void openDetailFor(Recipe recipe) {
        RecipeDetailActivity.view(getContext(), recipe);
    }
}
