package mobiledev.unb.ca.jgc;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mobiledev.unb.ca.jgc.util.LoadDataTask_audio;

public class audioPlayActivity extends AppCompatActivity {
    private Button playButton;
    private Button recordButton;
    private Button emailButton;
    //private Button folderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_player);
        emailButton = findViewById(R.id.audio_share);
        //+ "/Music/AUD_
        File outfile = Environment.getExternalStorageDirectory();
        String outPutFile = outfile.getAbsolutePath() + "/Music/";
       // String outPutFile = outfile.getAbsolutePath()+"/DCIM/Camera";
        String path =outPutFile;
        //String path = Environment.getExternalStorageDirectory().toString()+"/DCIM/Camera";
        Log.i("path", path);
        File f = new File(path);
        File[] files= null;
        Log.i("pass", f+"");
        // File file[] = f.listFiles();
        int i;
        if(f != null) {
            int length =f.listFiles().length;
            for (i = 0; i < f.listFiles().length; i++) {
                if (f.listFiles()[i].getName().equals(".thumbnails")) {
                    //  Log.i("y", "tsetset");
                    length--;
                }
            }
            int j;
            files = new File[length];
            for(j=0;j<length;j++) {
                files[j] = f.listFiles()[j];
            }

        }
       // Log.i("error","stop");
        //List<File> fileList = Arrays.asList(f.listFiles());
        List<File> fileList = Arrays.asList(files);
      //  Log.i("test","testing"+files.length);
        RecyclerView recyclerView = findViewById(R.id.audio_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // TODO: SharedPreferences
        //  Setup the instance of shared preferences you will be using
        SharedPreferences sharedPreferences = getSharedPreferences("myPref",MODE_PRIVATE);
        // TODO
        //  Create an instance of LoadDataTask; pass in the recycler view
        //  and shared preferences then execute it
        LoadDataTask_audio loadDataTask = new LoadDataTask_audio(this,fileList);
        loadDataTask.setRecyclerView(recyclerView);
        loadDataTask.setSharedPreferencesHelper(sharedPreferences);
        loadDataTask.execute();
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAdapter_audio adapter_audio=loadDataTask.getMyAdapterAudio();
                ArrayList<File> fileList=adapter_audio.getListFiles();
                Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                //emailIntent.setType("text/plain");
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                //emailIntent.putExtra(Intent.EXTRA_TEXT, "Message...");
                emailIntent.setType("vnd.android.cursor.dir/email");
                ArrayList<Uri> uris = new ArrayList<Uri>();
                for(int num=0;num<fileList.size();num++) {
                    Log.i("testing",fileList.get(num).getPath());
                    //filelocation[num]= new File();
                    if (!fileList.get(num).exists() || !fileList.get(num).canRead()) {
                        return;
                    }
                    //Uri uri = Uri.fromFile(fileList.get(num));
                  //  Log.i("testingfhfhfghf", uri.toString());
                    Uri uri = FileProvider.getUriForFile(audioPlayActivity.this, BuildConfig.APPLICATION_ID + ".provider",fileList.get(num));
                    uris.add(uri);
                    // emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
                }
                emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
                emailIntent.setPackage("com.google.android.gm");
                try {
                    startActivityForResult(emailIntent,1234);
                    // startActivityForResult(Intent.createChooser(emailIntent, "Pick an Email provider"),1234);
                    finish();
                } catch (android.content.ActivityNotFoundException ex) {
                }

            }
        });
    }
}
