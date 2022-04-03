package mobiledev.unb.ca.jgc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import mobiledev.unb.ca.jgc.util.LoadDataTask_audio;

public class audioPlayActivity extends AppCompatActivity {
    private Button playButton;
    private Button recordButton;
    private Button emailButton;
    private Button folderButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_player);
        emailButton = findViewById(R.id.audio_share);
        folderButton=findViewById(R.id.audio_folder_select);
        folderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                startActivityForResult(Intent.createChooser(i, "Choose directory"), 9999);

            }
        });


        RecyclerView recyclerView = findViewById(R.id.audio_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // TODO: SharedPreferences
        //  Setup the instance of shared preferences you will be using
        SharedPreferences sharedPreferences = getSharedPreferences("myPref",MODE_PRIVATE);
        // TODO
        //  Create an instance of LoadDataTask; pass in the recycler view
        //  and shared preferences then execute it
        LoadDataTask_audio loadDataTask = new LoadDataTask_audio(this);
        loadDataTask.setRecyclerView(recyclerView);
        loadDataTask.setSharedPreferencesHelper(sharedPreferences);
        loadDataTask.execute();

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test","testing");
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Message...");
                try {
                    startActivity(emailIntent);
                    finish();
                } catch (android.content.ActivityNotFoundException ex) {
                }
            }
        });
    }
}