package mobiledev.unb.ca.jgc;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class audioSetnameActivity extends AppCompatActivity {
    private Button playButton;
    private Button recordButton;
    private Button stopButton;
    private MediaRecorder myAudioRecorder;
    private String outPutFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_setname);
    }
}