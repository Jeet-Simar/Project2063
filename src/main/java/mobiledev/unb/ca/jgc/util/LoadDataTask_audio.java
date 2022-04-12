package mobiledev.unb.ca.jgc.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

import mobiledev.unb.ca.jgc.MyAdapter_audio;
import mobiledev.unb.ca.jgc.MyAdapter_video;
import mobiledev.unb.ca.jgc.model.AudioInfo;

public class LoadDataTask_audio {
    private final AppCompatActivity activity;
    private final Context appContext;

    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private List <File> fileList;
    private MyAdapter_audio myAdapterAudio;
    public LoadDataTask_audio(AppCompatActivity activity,List <File> fileList) {
        this.activity = activity;
        this.fileList =fileList;
        this.myAdapterAudio = null;
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
            //File f = new File(path);
            // File file[] = f.listFiles();
            //  List <File> fileList = Arrays.asList(f.listFiles());
            //Log.i("test",fileList.size()+"test");
            // Log.i("test",path+"   test");
            // int i;
            // for (i=0;i<fileList.size();i++) {
            //Log.i("test", fileList.size() + "test");
            ///fileList.get(i);
            //  Log.i("test",fileList.get(i)+"test");
            // }

            //JsonUtils_audio jsonUtils = new JsonUtils_audio(appContext);
            // List<AudioInfo> audio = jsonUtils.getAudio();
            // TODO
            //  Use result to set the adapter using the setupRecyclerView method below
            myAdapterAudio =new MyAdapter_audio(activity, fileList, sharedPreferences);
            //setupRecyclerView(fileList);
            setupRecyclerView(myAdapterAudio);
        });
    }

    /* private void setupRecyclerView( List<File> fileList) {
         recyclerView.setAdapter(new MyAdapter_video(activity, fileList, sharedPreferences));
     }*/
    private void setupRecyclerView(MyAdapter_audio myAdapterAudio) {
        recyclerView.setAdapter(myAdapterAudio);
    }
    public MyAdapter_audio getMyAdapterAudio(){
        return myAdapterAudio;
    }
}