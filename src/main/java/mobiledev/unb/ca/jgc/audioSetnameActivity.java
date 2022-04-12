package mobiledev.unb.ca.jgc;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
public class audioSetnameActivity extends AppCompatActivity {
    private Button doneButton;
    private RadioButton ThreeGP, Amr;
    private MediaRecorder myAudioRecorder;
    private String outPutFile;
    private TextView setName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_setname);
        setName = findViewById(R.id.editAudioTextName);
        //Log.e("Testing test view",setName.getText().toString());
        doneButton = findViewById(R.id.done_button);
        ThreeGP = (RadioButton) findViewById(R.id.ThreeGP);
        Amr = (RadioButton) findViewById(R.id.amr);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String audioname = setName.getText().toString();
                audioname = audioname.replace(' ','_');
                String extension = ".mp3";
                if(Amr.isChecked())
                {
                    extension = ".amr";
                }
                if(ThreeGP.isChecked())
                {
                    extension = ".3gp";
                }

                File oldname = new File(getIntent().getStringExtra("AudioFileName"));
                File newname = new File(Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/Music/"+audioname + extension);
                boolean successs = oldname.renameTo(newname);
                if(successs)
                    Toast.makeText(getApplicationContext(), "File Name and compression completed",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Error in name change",
                            Toast.LENGTH_LONG).show();
                Log.e("Testing done",setName.getText().toString()
                        + " //" +oldname + " Done?" + successs);
                Intent intent = new Intent(audioSetnameActivity.this, audioPlayActivity.class);
                startActivity(intent);
            }
        });
    }
}