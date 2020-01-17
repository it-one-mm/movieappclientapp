package com.itonemm.movieappclientapp36;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MoiveHolder> {
    private RewardedAd rewardedAd;
    ArrayList<MoiveModel> moiveModels=new ArrayList<MoiveModel>();
    Context context;
    LayoutInflater layoutInflater;
    Activity activity;

    public MovieRecyclerAdapter(ArrayList<MoiveModel> moiveModels, Context context, LayoutInflater layoutInflater,Activity activity) {
        this.moiveModels = moiveModels;
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.activity=activity;
    }

    @NonNull
    @Override
    public MoiveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.movieitem,parent,false);
        return new MoiveHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MoiveHolder holder, final int position) {
        //Rewarded Ads
        String appid=context.getResources().getString(R.string.app_id);
        MobileAds.initialize(context,appid);
        String unitid=context.getResources().getString(R.string.reward_unit_id);
        rewardedAd = new RewardedAd(context,unitid);
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);

        //Rewarded Ads


        holder.movieName.setText(moiveModels.get(position).movieName);
        Glide.with(context)
                .load(moiveModels.get(position).movieImage)
                .into(holder.movieImage);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rewardedAd.isLoaded())
                {
                    RewardedAdCallback adCallback = new RewardedAdCallback() {
                        @Override
                        public void onRewardedAdOpened() {
                            // Ad opened.
                        }

                        @Override
                        public void onRewardedAdClosed() {

                        }

                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem reward) {
                            Intent intent=new Intent(context,VideoViewActivity.class);


                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            try {
                                VideoViewActivity.url=MediaFireConnect.getJsonData(moiveModels.get(position).movieVideo);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            context.startActivity(intent);
                        }

                        @Override
                        public void onRewardedAdFailedToShow(int errorCode) {
                            Intent intent=new Intent(context,VideoViewActivity.class);


                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            try {
                                VideoViewActivity.url=MediaFireConnect.getJsonData(moiveModels.get(position).movieVideo);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            context.startActivity(intent);
                        }
                    };
                    rewardedAd.show(activity, adCallback);
                }
                else
                {
                    Intent intent=new Intent(context,VideoViewActivity.class);


                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    try {
                        VideoViewActivity.url=MediaFireConnect.getJsonData(moiveModels.get(position).movieVideo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    context.startActivity(intent);
                }

            }
        });

       /* holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu pMenu=new PopupMenu(context,holder.movieImage);
                pMenu.getMenuInflater().inflate(R.menu.download,pMenu.getMenu());
                pMenu.show();
                return true;
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return moiveModels.size();
    }

    public class MoiveHolder extends RecyclerView.ViewHolder{

        ImageView movieImage;
        TextView movieName;
        RelativeLayout relativeLayout;
        public MoiveHolder(@NonNull View itemView) {
            super(itemView);
            movieImage=itemView.findViewById(R.id.movie_image);
            movieName=itemView.findViewById(R.id.movie_name);
            relativeLayout=itemView.findViewById(R.id.moviepanel);
    }

    }
}
