package mobiledev.unb.ca.jgc;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class audioRecordActivity extends AppCompatActivity {
    private Button playButton;
    private Button recordButton;
    private Button stopButton;
    private MediaRecorder myAudioRecorder;
    private String outPutFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_recorder);
        Log.i("iser","testsett");
        recordButton = (Button) findViewById(R.id.audio_startrecord);
        stopButton = (Button)findViewById(R.id.audio_endrecord);
        stopButton.setEnabled(false);
        outPutFile = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/recording.mp3";
        myAudioRecorder = new MediaRecorder();
        Log.e("testset",MediaRecorder.AudioSource.MIC+"");

        myAudioRecorder.reset();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        myAudioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        myAudioRecorder.setOutputFile(outPutFile);
        Log.i("iser","sdkfjhsdfkjlhsdf");
        /*playButton = findViewById(R.id.video_play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(audioRecordActivity.this, audioRecordActivity.class);
                startActivity(intent);
                MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(outputFile);
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            // make something
        }
            }
        });*/
        //recordButton = findViewById(R.id.video_record);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(audioRecordActivity.this, videoMenuActivity.class);
                //startActivity(intent);
                try {
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                } catch (Exception e) {

                }
                recordButton.setEnabled(false);
                stopButton.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Recording started",
                        Toast.LENGTH_LONG).show();
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("terstset","testet");
                Log.d("terstset",outPutFile);
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;
                recordButton.setEnabled(true);
                stopButton.setEnabled(false);
                Toast.makeText(getApplicationContext(),
                        "Audio Recorder stopped", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(audioRecordActivity.this, audioSetnameActivity.class);
                startActivity(intent);
            }
        });
    }
    public String getPathandNameOfFile()
    {
        return outPutFile ;
    }
}