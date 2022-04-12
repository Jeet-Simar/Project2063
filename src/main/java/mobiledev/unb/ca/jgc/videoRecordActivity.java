package mobiledev.unb.ca.jgc;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
public class videoRecordActivity extends AppCompatActivity {
    private Button playButton;
    private Button recordButton;
    private String outFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_recorder);
        recordButton = findViewById(R.id.video_startrecord);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecordingVideo();
                Log.i("test","test");
                /*Intent intent = new Intent(videoRecordActivity.this, videoRecordActivity.class);
                startActivity(intent);*/
            }
        });
    }
    private static final int VIDEO_CAPTURE = 101;
    Uri videoUri;
    public void startRecordingVideo() {
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            // Log.i("test1","test1");
            File outfile = Environment.getExternalStorageDirectory();
            String outPutFile = outfile.getAbsolutePath() + "/Movies/VID_";
            File mediaFile = new File(outPutFile+timestamp+".mp4");
            outFile = mediaFile.toString();
            videoUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", mediaFile);
            // outFile = videoUri.toString();
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
            //Log.i("test location",videoUri.toString());
            startActivityForResult(intent, VIDEO_CAPTURE);
        } else {
            Toast.makeText(this, "No camera on device", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Video has been saved to:\n" + data.getData(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(videoRecordActivity.this, videoSetnameActivity.class);
                intent.putExtra("VideoFileName", outFile);
                startActivity(intent);
                //playbackRecordedVideo();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to record video", Toast.LENGTH_LONG).show();
            }
        }
    }
}