package mobiledev.unb.ca.jgc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class audio_player extends AppCompatActivity {
    private Button stop;
    private ImageButton play;
    private ImageButton pause;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_player_play);
        VideoView audioView =(VideoView)findViewById(R.id.audioView1);
        play = findViewById(R.id.imageButton2_play_audio);
        stop = findViewById(R.id.Done_play_button_audio);
        pause = findViewById(R.id.imageButton_play_audio);
        // TODO
        //  Get the intent that started this activity along with the extras added to it
        Intent intent = this.getIntent();
        String path = intent.getStringExtra("path");
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(audioView);
        Uri uri = Uri.parse(path);
        audioView.setMediaController(mediaController);
        audioView.setVideoURI(uri);
        audioView.requestFocus();
        audioView.start();
        position = 0;
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("teset","test");
                Log.i("teset",position+"");
                audioView.start();
                audioView.seekTo(position);

            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position=audioView.getCurrentPosition();
                Log.i("teset","test2");
                Log.i("teset",position+"");
                audioView.pause();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioView.stopPlayback();
                Intent intent = new Intent(audio_player.this, audioPlayActivity.class);
                startActivity(intent);
            }
        });
    }
}
