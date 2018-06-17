package hr.murielkamgang.mf.components.detail.stepdetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import hr.murielkamgang.mf.R;
import hr.murielkamgang.mf.components.base.BaseDialogFragment;
import hr.murielkamgang.mf.data.model.receipe.Recipe;
import hr.murielkamgang.mf.data.model.receipe.Step;

public class StepDetailsFragment extends BaseDialogFragment<StepDetailContract.View, StepDetailContract.Presenter> implements StepDetailContract.View {

    @BindView(R.id.frame)
    FrameLayout frameLayout;
    @BindView(R.id.playerView)
    PlayerView playerView;
    @BindView(R.id.imageStep)
    ImageView imageView;
    @BindView(R.id.textFullDescription)
    TextView textFullDescription;
    @BindView(R.id.textStep)
    TextView textStep;

    @Inject
    StepDetailContract.Presenter presenter;
    @Inject
    Picasso picasso;

    ExoPlayer player;

    @Inject
    public StepDetailsFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter != null) {
            presenter.setBundle(getArguments());
            presenter.setView(this);
        }
    }

    @Override
    protected StepDetailContract.Presenter providePresenter() {
        return presenter;
    }

    @Override
    protected int getResourceView() {
        return R.layout.step_detail_fragment;
    }

    @Override
    public void hideGraphicContent() {
        frameLayout.setVisibility(View.GONE);
    }

    @Override
    public void showImage(String imgUrl) {
        imageView.setVisibility(View.VISIBLE);
        picasso.load(imgUrl)
                .fit()
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void showVideo(String videoUrl) {
        playerView.setVisibility(View.VISIBLE);
        player = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());
        playerView.setPlayer(player);

        final MediaSource mediaSource = new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("udacity"))
                .createMediaSource(Uri.parse(videoUrl));
        player.prepare(mediaSource, true, true);
    }

    @Override
    public void onFullDescription(String descriptionUrl) {
        textFullDescription.setText(descriptionUrl);
    }

    @Override
    public void onStep(Step step) {
        textStep.setText(getString(R.string.placeholder_step, step.getId() + 1));
    }

    @Override
    public void onRecipe(Recipe recipe) {
        if (getActivity() instanceof StepDetailActivity) {
            ((StepDetailActivity) getActivity()).getToolbar().setTitle(recipe.getName());
            ((StepDetailActivity) getActivity()).getToolbar().setSubtitle(getString(R.string.placeholder_serving, recipe.getServings()));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.stop();
            player.release();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (player != null) {
            player.setPlayWhenReady(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            player.setPlayWhenReady(false);
        }
    }
}
