package com.itonemm.movieappclientapp36;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    ArrayList<MoiveModel> moiveModels=new ArrayList<MoiveModel>();
    LayoutInflater inflater;
    Context context;
    public GridAdapter(ArrayList<MoiveModel> moiveModels,LayoutInflater inflater,Context context) {
        this.moiveModels = moiveModels;
        this.inflater=inflater;
        this.context=context;
    }

    @Override
    public int getCount() {
        return moiveModels.size();
    }

    @Override
    public Object getItem(int position) {
        return moiveModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.movieitem, null);
        MoiveModel moiveModel = moiveModels.get(position);
        TextView movie_name = view.findViewById(R.id.movie_name);
        ImageView movie_image = view.findViewById(R.id.movie_image);
        Glide.with(context)
                .load(moiveModel.movieImage)
                .into(movie_image);
        movie_name.setText(moiveModel.movieName);
        return view;

    }
}
