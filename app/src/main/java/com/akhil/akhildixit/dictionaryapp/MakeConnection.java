package com.akhil.akhildixit.dictionaryapp;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Akhil Dixit on 7/24/2017.
 */

public class MakeConnection extends AsyncTask<String, Integer, String> {


    String mainResult;
    String word;
    String url1;

    public void setWord(String word) {
        url1="https://od-api.oxforddictionaries.com:443/api/v1/entries/en/"+word.toLowerCase();


    }


    protected String doInBackground(String... params) {

        //TODO: replace with your own app id and app key
        final String app_id = "30a82387";
        final String app_key = "73c3cac9baae525b3b34752b169a234b";
        try {
            URL url = new URL(url1);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            mainResult=stringBuilder.toString();

            return stringBuilder.toString();


        }
        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        }

    public String getResult()
    {
        return mainResult;
    }

}
