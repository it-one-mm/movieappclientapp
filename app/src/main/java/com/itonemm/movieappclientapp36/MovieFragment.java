package com.itonemm.movieappclientapp36;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {


    public static RecyclerView gridView;
    public static ProgressBar progressBar;
    public static RecyclerView recyclerView;
    public MovieFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_movie, container, false);
        gridView=view.findViewById(R.id.grid_view);
        progressBar=view.findViewById(R.id.progressBar);
        recyclerView=view.findViewById(R.id.new_movie_list);
        FireBaseConnect fireBaseConnect=new FireBaseConnect();
        FireBaseConnect.context=getContext();
        FireBaseConnect.inflater=getLayoutInflater();
        FireBaseConnect.activity=getActivity();
        fireBaseConnect.getAllMovies();;
        fireBaseConnect.getNewMovies();

        return view;

    }


}
