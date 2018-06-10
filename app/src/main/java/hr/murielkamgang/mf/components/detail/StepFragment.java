package hr.murielkamgang.mf.components.detail;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import javax.inject.Inject;

import hr.murielkamgang.mf.R;
import hr.murielkamgang.mf.components.base.BaseContentFragment;
import hr.murielkamgang.mf.components.base.GridSpacingItemDecoration;
import hr.murielkamgang.mf.data.model.receipe.Step;

public class StepFragment extends BaseContentFragment<Step, StepContract.View, StepContract.Presenter> implements StepContract.View {

    @Inject
    StepContract.Presenter presenter;

    @Inject
    public StepFragment() {
    }

    @Override
    protected void onItemClicked(Step step) {
        if (presenter != null) {
            presenter.delegateOnStepClicked(step);
        }
    }

    @Override
    protected void onViewClicked(View view, Step step) {

    }

    @Override
    protected void initRecyclerView() {
        swipeRefreshLayout.setEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerView.setAdapter(new StepAdapter());
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing), true));
    }

    @Override
    protected StepContract.Presenter providePresenter() {
        return presenter;
    }

    @Override
    protected int getResourceView() {
        return R.layout.default_recycler_view_fragment;
    }

}
