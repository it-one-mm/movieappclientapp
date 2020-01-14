package com.itonemm.movieappclientapp36;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MediaFireConnect {
   static String BASE_URL="https://channelbox.apkmm.net/api/v1/";
   static String file_path="mediafire?url=";

    public static String getJsonData(String videolink) throws IOException, JSONException {
        String query= URLEncoder.encode(videolink);
        String url=BASE_URL+file_path+query;
        URL siteurl=new URL(url);
        HttpURLConnection connection=(HttpURLConnection)siteurl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type","application/json");
        connection.setRequestProperty("Accept","application/json;charset=utf-8");
        connection.setDoInput(true);
        InputStream inputStream=new BufferedInputStream(connection.getInputStream());
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
        String line="";
        StringBuffer result=new StringBuffer();
        while ((line=reader.readLine())!=null)
        {
            result.append(line);
        }
        String jsonData=result.toString();
        JSONObject jsonObject=new JSONObject(jsonData);
        JSONObject data=jsonObject.getJSONObject("data");
        String fileName=data.getString("file");

return  fileName;
    }

}
