package mobiledev.unb.ca.jgc;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import mobiledev.unb.ca.jgc.audioRecordActivity;

public class audioRecordActivity extends AppCompatActivity {
    private Button recordButton;
    private Button stopButton;
    private MediaRecorder myAudioRecorder;
    private String outPutFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_recorder);
        Log.i("iser", "testsett");
        recordButton = (Button) findViewById(R.id.audio_startrecord);
        stopButton = (Button) findViewById(R.id.audio_endrecord);
        stopButton.setEnabled(false);
        File outfile = Environment.getExternalStorageDirectory();
        outPutFile = outfile.getAbsolutePath() + "/Music/AUD_";
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        outPutFile = outPutFile + timestamp + ".mp3";
        myAudioRecorder = new MediaRecorder();
      //  Log.e("testset", outPutFile + "");
        myAudioRecorder.reset();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        myAudioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        myAudioRecorder.setOutputFile(outPutFile);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                } catch (Exception e) {
                    Log.d("Error in start", e.toString());
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
                Log.d("terstset", "testet");
                Log.d("terstset", outPutFile);
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;
                recordButton.setEnabled(true);
                stopButton.setEnabled(false);
            /*MediaPlayer mediaPlayer = new MediaPlayer();
            try {
            mediaPlayer.setDataSource(outPutFile);
            mediaPlayer.prepare();
            mediaPlayer.start();
            } catch (Exception e) {
                // make something
                Log.e("In play exception","Testing");
            }*/
                Toast.makeText(getApplicationContext(),
                        "Audio Recorder stopped", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(audioRecordActivity.this, audioSetnameActivity.class);
                intent.putExtra("AudioFileName", outPutFile);
                startActivity(intent);
            }
        });
    }
    public String getPathandNameOfFile() {
        return outPutFile;
    }
}

