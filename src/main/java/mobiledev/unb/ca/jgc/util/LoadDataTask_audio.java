package mobiledev.unb.ca.jgc.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.Executors;

import mobiledev.unb.ca.jgc.MyAdapter_audio;
import mobiledev.unb.ca.jgc.model.AudioInfo;

public class LoadDataTask_audio {
    private final AppCompatActivity activity;
    private final Context appContext;

    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;

    public LoadDataTask_audio(AppCompatActivity activity) {
        this.activity = activity;
        appContext = activity.getApplicationContext();
    }

    public LoadDataTask_audio setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        return this;
    }

    public LoadDataTask_audio setSharedPreferencesHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        return this;
    }

    public void execute() {
        Executors.newSingleThreadExecutor().execute(() -> {
            Handler mainHandler = new Handler(Looper.getMainLooper());

            // TODO
            //  Load the data from the JSON assets file and return the list of cities
            JsonUtils_audio jsonUtils = new JsonUtils_audio(appContext);
            List<AudioInfo> audio = jsonUtils.getAudio();
            // TODO
            //  Use result to set the adapter using the setupRecyclerView method below
            setupRecyclerView(audio);
        });
    }

    private void setupRecyclerView(List<AudioInfo> audioInfoList) {
        recyclerView.setAdapter(new MyAdapter_audio(activity, audioInfoList, sharedPreferences));
    }
}