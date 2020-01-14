package com.itonemm.movieappclientapp36;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FireBaseConnect {


    public static Context context;
    public static LayoutInflater inflater;
    static Activity activity;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    CollectionReference movieRef=db.collection("movies");
    CollectionReference seriesRef=db.collection("series");
    CollectionReference categoryRef=db.collection("categories");


    public void getMovies()
    {

        movieRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                ArrayList<MoiveModel>moiveModels=new ArrayList<MoiveModel>();
                moiveModels.clear();
                for(DocumentSnapshot snapshot:queryDocumentSnapshots)
                {
                    moiveModels.add(snapshot.toObject(MoiveModel.class));
                }

                /*GridAdapter adapter=new GridAdapter(moiveModels,inflater,context);
                MovieFragment.gridView.setAdapter(adapter);
                MovieFragment.gridView.setVisibility(View.VISIBLE);
                MovieFragment.progressBar.setVisibility(View.GONE);*/


            }
        });
    }
    public void getAllMovies()
    {

       movieRef.whereEqualTo("movieCategory","AllMovies").addSnapshotListener(new EventListener<QuerySnapshot>() {
           @Override
           public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
               ArrayList<MoiveModel>moiveModels=new ArrayList<MoiveModel>();
               moiveModels.clear();
               for(DocumentSnapshot snapshot:queryDocumentSnapshots)
               {
                   moiveModels.add(snapshot.toObject(MoiveModel.class));
               }

              MovieRecyclerAdapter adapter=new MovieRecyclerAdapter(moiveModels,context,inflater,activity);
               GridLayoutManager gridLayoutManager=new GridLayoutManager(context,3,RecyclerView.VERTICAL,false);
               MovieFragment.gridView.setAdapter(adapter);
               MovieFragment.gridView.setLayoutManager(gridLayoutManager);
               MovieFragment.gridView.setVisibility(View.VISIBLE);
               MovieFragment.progressBar.setVisibility(View.GONE);
           }
       });
    }
    public void getNewMovies()
    {

        movieRef.whereEqualTo("movieCategory","NewMovies").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<MoiveModel>moiveModels=new ArrayList<MoiveModel>();
                moiveModels.clear();
                for(DocumentSnapshot snapshot:queryDocumentSnapshots)
                {
                    moiveModels.add(snapshot.toObject(MoiveModel.class));
                }

               MovieRecyclerAdapter adapter=new MovieRecyclerAdapter(moiveModels,context,inflater,activity);
               MovieFragment.recyclerView.setAdapter(adapter);

               MovieFragment.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false));
                MovieFragment.progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void getNewSeries()
    {

        seriesRef.whereEqualTo("seriesCategory","NewSeries").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<SeriesModel>models=new ArrayList<SeriesModel>();
                models.clear();
                for(DocumentSnapshot snapshot:queryDocumentSnapshots)
                {
                    models.add(snapshot.toObject(SeriesModel.class));
                }

                SeriesRecyclerAdapter adapter=new SeriesRecyclerAdapter(models,context,inflater);
                SeriesFragment.new_series.setAdapter(adapter);

                SeriesFragment.new_series.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false));
               SeriesFragment.progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void getAllSeries()
    {

        seriesRef.whereEqualTo("seriesCategory","AllSeries").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<SeriesModel>models=new ArrayList<SeriesModel>();
                models.clear();
                for(DocumentSnapshot snapshot:queryDocumentSnapshots)
                {
                    models.add(snapshot.toObject(SeriesModel.class));
                }

                SeriesRecyclerAdapter adapter=new SeriesRecyclerAdapter(models,context,inflater);
                SeriesFragment.all_series.setAdapter(adapter);
                GridLayoutManager gridLayoutManager=new GridLayoutManager(context,3,RecyclerView.VERTICAL,false);
                SeriesFragment.all_series.setLayoutManager(gridLayoutManager);
                SeriesFragment.progressBar.setVisibility(View.GONE);
                SeriesFragment.all_series.setVisibility(View.VISIBLE);
            }
        });
    }

    public void getAllEpisodes(String seriesName)
    {
        movieRef.whereEqualTo("movieSeries",seriesName).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<MoiveModel>moiveModels=new ArrayList<MoiveModel>();
                moiveModels.clear();
                for(DocumentSnapshot snapshot:queryDocumentSnapshots)
                {
                    moiveModels.add(snapshot.toObject(MoiveModel.class));
                }

                SeriesEpAdapter adapter=new SeriesEpAdapter(moiveModels,context,inflater);
                SeriesDetailActivity.recyclerView.setAdapter(adapter);

                SeriesDetailActivity.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));

            }
        });
    }

}
