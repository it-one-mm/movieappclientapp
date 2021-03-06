package com.itonemm.movieappclientapp36;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaDataSource;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.net.URLEncoder;

import javax.sql.DataSource;

public class VideoViewActivity extends AppCompatActivity {
    SimpleExoPlayer player;
    static String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        final ProgressBar progressBar = findViewById(R.id.videoloading);
        progressBar.setVisibility(View.VISIBLE);


        TrackSelector selector = new DefaultTrackSelector();
        SimpleExoPlayerView exoPlayerView = findViewById(R.id.exoplayer);
         player = ExoPlayerFactory.newSimpleInstance(getApplicationContext(), selector);

        DefaultHttpDataSourceFactory df = new DefaultHttpDataSourceFactory("exo player");
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();


        String []arr=url.split(":");
        url=arr[0]+"s:"+arr[1];
        Uri uri = Uri.parse(url);

            ExtractorMediaSource mediaSource = new ExtractorMediaSource(uri, df, extractorsFactory, null, null);
            player.prepare(mediaSource);
            exoPlayerView.setPlayer(player);
            player.setPlayWhenReady(true);
            SimpleExoPlayer.EventListener listener = new ExoPlayer.EventListener() {
                @Override
                public void onTimelineChanged(Timeline timeline, Object manifest) {

                }

                @Override
                public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

                }

                @Override
                public void onLoadingChanged(boolean isLoading) {

                }

                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

                    if (playbackState == SimpleExoPlayer.STATE_BUFFERING) {
                        progressBar.setVisibility(View.VISIBLE);
                    } else {
                        progressBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onRepeatModeChanged(int repeatMode) {

                }

                @Override
                public void onPlayerError(ExoPlaybackException error) {

                }

                @Override
                public void onPositionDiscontinuity() {

                }

                @Override
                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

                }
            };
            player.addListener(listener);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        player.stop();;
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.stop();
    }
}
