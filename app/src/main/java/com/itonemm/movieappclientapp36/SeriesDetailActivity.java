package com.itonemm.movieappclientapp36;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SeriesDetailActivity extends AppCompatActivity {
    static SeriesModel seriesModel;
    String id;
    static RecyclerView recyclerView;
    TextView seriesName;
    ImageView seriesImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_detail);

        recyclerView=findViewById(R.id.series_ep_list);

        seriesName=findViewById(R.id.SeriesName);
        seriesImage=findViewById(R.id.series_ep_image);
        if(seriesModel!=null)
        {

            seriesName.setText(seriesModel.seriesName);
            Glide.with(getApplicationContext())
                    .load(seriesModel.seriesImage)
                    .into(seriesImage);
        }


        FireBaseConnect fireBaseConnect=new FireBaseConnect();
        fireBaseConnect.getAllEpisodes(seriesModel.seriesName);

    }

}
