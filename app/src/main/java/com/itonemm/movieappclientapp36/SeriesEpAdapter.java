package com.itonemm.movieappclientapp36;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SeriesEpAdapter extends RecyclerView.Adapter<SeriesEpAdapter.MoiveHolder> {

    ArrayList<MoiveModel> moiveModels=new ArrayList<MoiveModel>();
    Context context;
    LayoutInflater layoutInflater;

    public SeriesEpAdapter(ArrayList<MoiveModel> moiveModels, Context context, LayoutInflater layoutInflater) {
        this.moiveModels = moiveModels;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public MoiveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.sereisep,parent,false);
        return new MoiveHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoiveHolder holder, final int position) {

        holder.epName.setText(moiveModels.get(position).movieName);
       holder.epSr.setText((position+1)+"");
       holder.playvideo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
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
       });

    }

    @Override
    public int getItemCount() {
        return moiveModels.size();
    }

    public class MoiveHolder extends RecyclerView.ViewHolder{


        TextView epName,epSr;
        FloatingActionButton playvideo;
        public MoiveHolder(@NonNull View itemView) {
            super(itemView);
            epName=itemView.findViewById(R.id.txt_ep_name);
           epSr=itemView.findViewById(R.id.txt_ep_sr);
           playvideo=itemView.findViewById(R.id.ep_play);
        }

    }
}
