package mobiledev.unb.ca.jgc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class videoMenuActivity extends AppCompatActivity {
    private Button playButton;
    private Button recordButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_setting);
        playButton = findViewById(R.id.video_play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(videoMenuActivity.this, videoPlayActivity.class);
                startActivity(intent);
            }

        });
        recordButton = findViewById(R.id.video_record);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(videoMenuActivity.this, videoRecordActivity.class);
                startActivity(intent);
            }
        });
    }
}