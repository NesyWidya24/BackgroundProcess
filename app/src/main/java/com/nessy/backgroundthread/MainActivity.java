package com.nessy.backgroundthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AsyncCallback{ //implement asyncCallback

    private TextView tvStat;
    private TextView tvDesc;

    private  final static String INPUT_STRING = "Halo This is Demo AsyncTask!!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStat = findViewById(R.id.tv_stat);
        tvDesc = findViewById(R.id.tv_desc);

        DemoAsync demoAsync = new DemoAsync(this);
        demoAsync.execute(INPUT_STRING);
    }

    @Override
    public void onPreExecute() {
        tvStat.setText(R.string.stat_pre);
        tvDesc.setText(INPUT_STRING);
    }

    @Override
    public void onPostExecute(String text) {
        tvStat.setText(R.string.stat_post);
        if (text != null){
            tvDesc.setText(text);
        }
    }

    private static class DemoAsync extends AsyncTask<String, Void, String> { //serves to manage data asynchronously ------// extend class AsyncTask
        static final String LOG_ASYNC = "DemoAsync";
        WeakReference<AsyncCallback> listener;

        DemoAsync(AsyncCallback listener){
            this.listener = new WeakReference<>(listener);
        }

        @Override
        public void onPreExecute() { //2 method from AsyncCallback----//add method for prepare and when process successfully
            super.onPreExecute();
            Log.d(LOG_ASYNC, "stat : onPreExecute");

            AsyncCallback listener = this.listener.get();
            if (listener != null){
                listener.onPreExecute();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d(LOG_ASYNC, "stat: doInBackground");
            String output = null;
            try {
                String input = params[0];
                output = input + " Happy Learning!!";
                Thread.sleep(2400);
            } catch (Exception e) {
                Log.d(LOG_ASYNC, Objects.requireNonNull(e.getMessage()));
            }
            return output;
        }


        @Override
        public void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(LOG_ASYNC, "stat : onPostExecute");

            AsyncCallback listener = this.listener.get();
            if (listener != null){
                listener.onPostExecute(result);
            }
        }
    }
}

interface AsyncCallback{ //create interface
    void onPreExecute();
    void onPostExecute(String text);
}