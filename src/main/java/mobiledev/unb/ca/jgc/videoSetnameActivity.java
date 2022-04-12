package mobiledev.unb.ca.jgc;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import mobiledev.unb.ca.jgc.R;
import mobiledev.unb.ca.jgc.videoPlayActivity;

public class videoSetnameActivity extends AppCompatActivity {

    private Button doneButton;
    private RadioButton Mp4, AAC;
    //private MediaRecorder myAudioRecorder;
    private String outPutFile;
    private TextView setName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_setname);
        setName = findViewById(R.id.editVideoTextName);
        //Log.e("Testing test view",setName.getText().toString());
        doneButton = findViewById(R.id.video_save_recording);
        Mp4 = (RadioButton) findViewById(R.id.mp4);
        AAC = (RadioButton) findViewById(R.id.aac);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String audioname = setName.getText().toString();
                audioname = audioname.replace(' ','_');
                String extension = ".mp4";
                if(AAC.isChecked())
                {
                    extension = ".aac";
                }
                Log.i("test1","test2");
                File oldname = new File(getIntent().getStringExtra("VideoFileName"));
                Log.i("test1",oldname.getAbsolutePath());
                Log.i("test1",oldname.getName());
                File name = Environment.getExternalStorageDirectory();
                String name2 = name.getAbsolutePath() + "/Movies/"+audioname;
                Log.i("test2",name2);
                File newname = new File(name2 + extension);
               // Log.i("test3",newname.getAbsolutePath());
                //Log.i("test3",newname.getName());
               // Path path =oldname.toPath();
                boolean successs = oldname.renameTo(newname);
                if(successs)
                    Toast.makeText(getApplicationContext(), "File Name and compression completed",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Error in name change",
                            Toast.LENGTH_LONG).show();
                Log.e("Testing done",setName.getText().toString()
                        + " //" +oldname + " Done?" + successs);
                Intent intent = new Intent(videoSetnameActivity.this, videoPlayActivity.class);
                startActivity(intent);
            }
        });
    }


}