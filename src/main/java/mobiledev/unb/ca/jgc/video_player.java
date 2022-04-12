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

public class video_player extends AppCompatActivity {
    private Button stop;
    private ImageButton play;
    private ImageButton pause;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player_play);
        VideoView videoView =(VideoView)findViewById(R.id.videoView1);
        play = findViewById(R.id.imageButton2_play_video);
        stop = findViewById(R.id.Done_play_button_video);
        pause = findViewById(R.id.imageButton_play_video);
        // TODO
        //  Get the intent that started this activity along with the extras added to it
        Intent intent = this.getIntent();
        String path = intent.getStringExtra("path");
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse(path);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
        position = 0;
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("teset","test");
                Log.i("teset",position+"");
                videoView.start();
                videoView.seekTo(position);

            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position=videoView.getCurrentPosition();
                Log.i("teset","test2");
                Log.i("teset",position+"");
                videoView.pause();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.stopPlayback();
                Intent intent = new Intent(video_player.this, videoPlayActivity.class);
                startActivity(intent);
            }
        });
    }
}
