package hr.murielkamgang.mf.components.detail;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import javax.inject.Inject;

import hr.murielkamgang.mf.R;
import hr.murielkamgang.mf.components.base.BaseContentFragment;
import hr.murielkamgang.mf.components.base.GridSpacingItemDecoration;
import hr.murielkamgang.mf.data.model.receipe.Ingredient;

public class IngredientsFragment extends BaseContentFragment<Ingredient, IngredientContract.View, IngredientContract.Presenter> implements IngredientContract.View {

    @Inject
    IngredientContract.Presenter presenter;

    @Inject
    public IngredientsFragment() {
    }

    @Override
    protected void onItemClicked(Ingredient ingredient) {

    }

    @Override
    protected void onViewClicked(View view, Ingredient ingredient) {

    }

    @Override
    protected void initRecyclerView() {
        swipeRefreshLayout.setEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerView.setAdapter(new IngredientsAdapter());
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing), true));
    }

    @Override
    protected IngredientContract.Presenter providePresenter() {
        return presenter;
    }

    @Override
    protected int getResourceView() {
        return R.layout.default_recycler_view_fragment;
    }
}
