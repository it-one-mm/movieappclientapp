package com.itonemm.movieappclientapp36;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SeriesRecyclerAdapter extends RecyclerView.Adapter<SeriesRecyclerAdapter.SeriesHolder> {

    private InterstitialAd mInterstitialAd;
    ArrayList<SeriesModel> SeriesModels=new ArrayList<SeriesModel>();
    Context context;
    LayoutInflater layoutInflater;

    public SeriesRecyclerAdapter(ArrayList<SeriesModel> SeriesModels, Context context, LayoutInflater layoutInflater) {
        this.SeriesModels = SeriesModels;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public SeriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.movieitem,parent,false);
        return new SeriesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesHolder holder, final int position) {
        loadAds();
        holder.movieName.setText(SeriesModels.get(position).seriesName);
        Glide.with(context)
                .load(SeriesModels.get(position).seriesImage)
                .into(holder.movieImage);
        holder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(mInterstitialAd.isLoaded())
                {
                    mInterstitialAd.show();
                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdLoaded() {
                            // Code to be executed when an ad finishes loading.
                        }

                        @Override
                        public void onAdFailedToLoad(int errorCode) {
                            gotNextPage(position);
                        }

                        @Override
                        public void onAdOpened() {
                            // Code to be executed when the ad is displayed.
                        }

                        @Override
                        public void onAdClicked() {
                            // Code to be executed when the user clicks on an ad.
                        }

                        @Override
                        public void onAdLeftApplication() {
                            // Code to be executed when the user has left the app.
                        }

                        @Override
                        public void onAdClosed() {

                        }
                    });
                }
                else
                {
                    gotNextPage(position);
                }




            }
        });


    }

    @Override
    public int getItemCount() {
        return SeriesModels.size();
    }

    public class SeriesHolder extends RecyclerView.ViewHolder{

        ImageView movieImage;
        TextView movieName;
        public SeriesHolder(@NonNull View itemView) {
            super(itemView);
            movieImage=itemView.findViewById(R.id.movie_image);
            movieName=itemView.findViewById(R.id.movie_name);
    }
}

    public void loadAds()
    {
        MobileAds.initialize(context,context.getResources().getString(R.string.app_id));
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(context.getResources().getString(R.string.interstitial_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    public void gotNextPage(int index)
    {
        SeriesDetailActivity.seriesModel=SeriesModels.get(index);
        Intent intent=new Intent(context,SeriesDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}



