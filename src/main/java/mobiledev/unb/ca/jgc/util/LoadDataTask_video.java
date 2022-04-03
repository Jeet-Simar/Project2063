package mobiledev.unb.ca.jgc.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.Executors;

import mobiledev.unb.ca.jgc.MyAdapter_video;
import mobiledev.unb.ca.jgc.model.AudioInfo;
import mobiledev.unb.ca.jgc.model.VideoInfo;


public class LoadDataTask_video {
    private final AppCompatActivity activity;
    private final Context appContext;

    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;

    public LoadDataTask_video(AppCompatActivity activity) {
        this.activity = activity;
        appContext = activity.getApplicationContext();
    }

    public LoadDataTask_video setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        return this;
    }

    public LoadDataTask_video setSharedPreferencesHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        return this;
    }

    public void execute() {
        Executors.newSingleThreadExecutor().execute(() -> {
            Handler mainHandler = new Handler(Looper.getMainLooper());

            // TODO
            //  Load the data from the JSON assets file and return the list of cities
            JsonUtils_video jsonUtils = new JsonUtils_video(appContext);
            List<VideoInfo> videos = jsonUtils.getHostCities();
            // TODO
            //  Use result to set the adapter using the setupRecyclerView method below
            setupRecyclerView(videos);
        });
    }

    private void setupRecyclerView(List<VideoInfo> videosInfoList) {
        recyclerView.setAdapter(new MyAdapter_video(activity, videosInfoList, sharedPreferences));
    }
}