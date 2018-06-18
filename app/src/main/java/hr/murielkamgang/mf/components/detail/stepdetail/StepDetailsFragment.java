package hr.murielkamgang.mf.components.detail.stepdetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

    private static final String EXTRA_CURRENT_POSITION_KEY = "EXTRA_CURRENT_POSITION_KEY";
    private static final String EXTRA_PLAY_WHEN_READY_KEY = "EXTRA_PLAY_WHEN_READY_KEY";
    private static final String EXTRA_CURRENT_WINDOW_KEY = "EXTRA_CURRENT_WINDOW_KEY";
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

    private String currentUrl;
    private boolean playWhenReady = true;
    private long currentPosition;
    private int currentWindow;

    @Inject
    public StepDetailsFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getLong(EXTRA_CURRENT_POSITION_KEY);
            playWhenReady = savedInstanceState.getBoolean(EXTRA_PLAY_WHEN_READY_KEY);
            currentWindow = savedInstanceState.getInt(EXTRA_CURRENT_WINDOW_KEY);
        }
        if (presenter != null) {
            presenter.setBundle(getArguments());
            presenter.setView(this);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(EXTRA_CURRENT_POSITION_KEY, currentPosition);
        outState.putBoolean(EXTRA_PLAY_WHEN_READY_KEY, playWhenReady);
        outState.putInt(EXTRA_CURRENT_WINDOW_KEY, currentWindow);
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
        currentUrl = videoUrl;
        playerView.setVisibility(View.VISIBLE);
        player = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());
        playerView.setPlayer(player);

        final MediaSource mediaSource = new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("udacity"))
                .createMediaSource(Uri.parse(videoUrl));

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentPosition);
        player.prepare(mediaSource, false, false);
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
    }

    @Override
    public void onResume() {
        super.onResume();
        if (currentUrl != null && player == null) {
            showVideo(currentUrl);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    private void releasePlayer() {
        if (player != null) {
            currentPosition = Math.max(0, player.getCurrentPosition());
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

}
