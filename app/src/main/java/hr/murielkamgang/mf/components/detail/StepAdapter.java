package hr.murielkamgang.mf.components.detail;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import hr.murielkamgang.mf.R;
import hr.murielkamgang.mf.components.base.BaseRecyclerViewAdapter;
import hr.murielkamgang.mf.data.model.receipe.Step;

public class StepAdapter extends BaseRecyclerViewAdapter<Step, BaseRecyclerViewAdapter.ItemBaseVH> {

    @NonNull
    @Override
    public ItemBaseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StepVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.step_item, parent, false));
    }

    class StepVH extends StepAdapter.ItemBaseVH {

        @BindView(R.id.textStep)
        TextView textStep;
        @BindView(R.id.textShortDescription)
        TextView textShortDescription;

        StepVH(View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(StepAdapter.this, StepVH.this, getAdapterPosition());
                }
            });
        }

        @Override
        protected void performBinding(Step step) {
            if (textShortDescription.getVisibility() != View.VISIBLE) {
                textShortDescription.setVisibility(View.VISIBLE);
            }
            textStep.setText(textStep.getContext().getString(R.string.placeholder_step, step.getId() + 1));
            textShortDescription.setText(step.getShortDescription());
        }
    }
}
