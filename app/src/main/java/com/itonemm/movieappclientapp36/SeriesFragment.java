package com.itonemm.movieappclientapp36;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeriesFragment extends Fragment {


    public SeriesFragment() {
        // Required empty public constructor
    }

    public static RecyclerView all_series,new_series;
    public static ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_series, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
       all_series=view.findViewById(R.id.rc_all_series);
       new_series=view.findViewById(R.id.rc_new_series);
       progressBar=view.findViewById(R.id.seriesloading);
       FireBaseConnect.inflater=getLayoutInflater();
       FireBaseConnect.context=getContext();
       FireBaseConnect fireBaseConnect=new FireBaseConnect();
       fireBaseConnect.getNewSeries();
       fireBaseConnect.getAllSeries();


       return  view;
    }

}
