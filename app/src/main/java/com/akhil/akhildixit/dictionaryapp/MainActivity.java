
package com.akhil.akhildixit.dictionaryapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    String word;
    String result;
    EditText editword;
    Button search;
    EditText res;
    JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editword=(EditText)findViewById(R.id.word);
        res=(EditText)findViewById(R.id.result);
        search=(Button)findViewById(R.id.search);






        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                word=editword.getText().toString();
                Toast.makeText(MainActivity.this,"Word is"+word,Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this,"in click",Toast.LENGTH_SHORT).show();
               final MakeConnection mc=new MakeConnection();
                mc.setWord(word);
                try {
                    result=mc.execute().get();
                    jsonObject=new JSONObject(result);

                    JSONArray jsonArray=jsonObject.getJSONArray("results").getJSONObject(0).getJSONArray("lexicalEntries").getJSONObject(0).getJSONArray("entries").getJSONObject(0).getJSONArray("etymologies");
                    res.setText(jsonArray.get(0).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Handler hd=new Handler();


                /*hd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        result=mc.getResult();
                        res.setText(result);

                    }
                },5000);*/
            }
        });




    }
}




/*
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//add dependencies to your class
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import android.os.AsyncTask;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CallbackTask().execute(dictionaryEntries());
    }

    private String dictionaryEntries() {
        final String language = "en";
        final String word = "Ace";
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }


    //in android calling network requests on the main thread forbidden by default
    //create class to do async job
    private class CallbackTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            //TODO: replace with your own app id and app key
            final String app_id = "30a82387";
            final String app_key = "73c3cac9baae525b3b34752b169a234b";
            try {
                URL url = new URL(params[0]);
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

            Log.e("awesome",result);
        }
    }
}
*/
