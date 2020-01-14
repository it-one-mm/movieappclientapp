package com.itonemm.movieappclientapp36;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.annotation.NonNull;

public class NotiService extends FirebaseMessagingService {
    public NotiService() {
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification() != null) {
            String title=remoteMessage.getNotification().getTitle();
            String body=remoteMessage.getNotification().getBody();
            String image=remoteMessage.getNotification().getImageUrl().toString();

        } else{
            String title=remoteMessage.getData().get("title");
            String body=remoteMessage.getData().get("body");
            String image=remoteMessage.getData().get("image");
            new SendNotification().execute(title,body,image);
        }
    }

    private class  SendNotification extends AsyncTask<String,Void, Bitmap>{


        @Override
        protected Bitmap doInBackground(String... strings) {

            String titile=strings[0];
            String body=strings[1];
            String imagelink=strings[2];

            try {
                URL url=new URL(imagelink);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                connection.connect();;
                connection.setDoInput(true);

                Bitmap image= BitmapFactory.decodeStream(connection.getInputStream());
                return  image;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }
    }
}
