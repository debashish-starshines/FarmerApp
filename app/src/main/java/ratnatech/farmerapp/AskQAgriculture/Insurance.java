package ratnatech.farmerapp.AskQAgriculture;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import ratnatech.farmerapp.Config;
import ratnatech.farmerapp.CustomHttpClient;
import ratnatech.farmerapp.ModelClass.EventModel;
import ratnatech.farmerapp.NetworkRelatedClass.NetworkCall;
import ratnatech.farmerapp.R;
import ratnatech.farmerapp.Utility;

public class Insurance extends AppCompatActivity {

    static final int REQUEST_VIDEO_CAPTURE = 1;
    public static final int ACTIVITY_RECORD_SOUND = 0;
    private final int requestCode = 20;
    EditText edittext;
    String urlRouteList;  //<--------------------------------------------

    String str_mystory_type="";
    Spinner subgroup;
    String strsubgroup="";
    int qna_id=0,my_story_id=0;  // <-----------------------------------------------
    String mystory="";  //<------------------------------------------------------

    StringBuilder encodedImage;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    String str_user_id="";
    ProgressDialog progressDialog;

    //Added for upload section - image video audio
    String user_id="";
    EditText editqid_mystoryid;  // <--------------------------------------------------

    private VideoView videoView;
    private EditText nameEditText;
    private EditText ageEditText;
    private ImageView imageView;
    private Button uploadButton;
    private TextView responseTextView,txtAudioPath;

    private String imagefilePath="",videofilePath="",audiofilePath="";
    private static final int PICK_PHOTO = 1958;
    private static final int PICK_VIDEO= 1993;
    private static final int PICK_AUDIO= 1994;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventModel event) throws ClassNotFoundException {
        if (event.isTagMatchWith("response")) {
            String responseMessage = event.getMessage();
            responseTextView.setText(responseMessage);
            progressDialog.hide();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_insurance);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("Agriculture/ Insurance");

        //GETTING THE MYSTORY STATUS                                 <---------------------------------------------------

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        mystory=sharedpreferences.getString("MY_STORY", "");
        Log.d("mystory:",mystory);
        editor.commit(); // commit changes

        editqid_mystoryid=(EditText) findViewById(R.id.editqid);  //   <--------------------------------------------------
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        ageEditText = (EditText) findViewById(R.id.ageEditText);
        imageView = (ImageView) findViewById(R.id.imageView);
        uploadButton = (Button) findViewById(R.id.uploadButton);
        responseTextView = (TextView) findViewById(R.id.responseTextView);
        videoView=(VideoView) findViewById(R.id.videoView);
        txtAudioPath=(TextView)findViewById(R.id.txtAudioPath);


        subgroup=findViewById(R.id.subgroup);
        edittext = findViewById(R.id.edittext);
        //GETTING USER_ID
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_MULTI_PROCESS);


        str_user_id=sharedpreferences.getString("FLAG",  "");
        Log.d("GET", str_user_id);

        subgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strsubgroup= subgroup.getSelectedItem().toString();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final Button capturedImageButton = (Button)findViewById(R.id.photo_button);
        capturedImageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, requestCode);
            }
        });

        final Button record_video=(Button)findViewById(R.id.record_video);
        record_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra("android.intent.extra.durationLimit", 10);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_VIDEO_CAPTURE);
                }
            }
        });

        final Button record_audio=(Button)findViewById(R.id.record_audio);
        record_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                startActivityForResult(intent, ACTIVITY_RECORD_SOUND);

            }
        });
    }

    public void addPhoto(View view) {
        responseTextView.setText("");
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_PHOTO);
    }


    public void addVideo(View view) {
        responseTextView.setText("");
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_VIDEO);
    }

    public void addAudio(View view) {
        responseTextView.setText("");
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_AUDIO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_PHOTO) {
            Uri imageUri = data.getData();
            imagefilePath = getPath(imageUri);
            imageView.setImageURI(imageUri);
            imageView.setVisibility(View.VISIBLE);
        }
        else if(resultCode==RESULT_OK && requestCode==PICK_VIDEO)
        {
            Uri videoUri = data.getData();
            videofilePath = getVideoPath(videoUri);
            videoView.setVisibility(View.VISIBLE);
            videoView.setVideoURI(videoUri);
            videoView.start();
        }
        else if(resultCode==RESULT_OK && requestCode==PICK_AUDIO)
        {
            Uri audioUri = data.getData();
            audiofilePath = getAudioPath(audioUri);
            txtAudioPath.setVisibility(View.VISIBLE);
            txtAudioPath.setText(audiofilePath);
        }

        else if(this.requestCode == requestCode && resultCode == RESULT_OK)
        {

            imageView.setVisibility(View.VISIBLE);
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            Uri cameraUri= getImageUri(this,bitmap);
            imagefilePath = getPath(cameraUri);
            Log.d("imagefilePath",imagefilePath);
        }
        else if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            videoView.setVideoURI(videoUri);
            videoView.setVisibility(View.VISIBLE);
            videoView.start();
            videofilePath = getVideoPath(videoUri);
        }

        else if (requestCode == ACTIVITY_RECORD_SOUND && resultCode == RESULT_OK) {
            Uri audioUri = data.getData();
            audiofilePath = getAudioPath(audioUri);
            txtAudioPath.setVisibility(View.VISIBLE);
            txtAudioPath.setText(audiofilePath);
        }

    }

    public void uploadIButtonClicked(View view)
    {

        String name = nameEditText.getText().toString();
        int age = Integer.parseInt(ageEditText.getText().toString());
        //NetworkCall.fileUpload(filePath, new ImageSenderInfo(name,age));

        //user_id=edituserid.getText().toString().trim();
        if(imagefilePath.equals(""))
        {
            Toast.makeText(this, "Select Image", Toast.LENGTH_SHORT).show();
        }
        else
        {
          //  NetworkCall.fileUpload(imagefilePath,String.valueOf(qna_id));
            if(qna_id!=0)                                                             // <------------------------------------
                NetworkCall.fileUpload(imagefilePath,String.valueOf(qna_id),"0");
            else if(my_story_id!=0)
                NetworkCall.fileUpload(imagefilePath,"0",String.valueOf(my_story_id));
            progressDialog= new ProgressDialog(this);
            progressDialog.setMessage("Uploading Image");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

    }

    public void uploadVButtonClicked(View view)
    {

        String name = nameEditText.getText().toString();
        int age = Integer.parseInt(ageEditText.getText().toString());
        //NetworkCall.fileUpload(filePath, new ImageSenderInfo(name,age));

        // user_id=edituserid.getText().toString().trim();

        if(videofilePath.equals(""))
        {
            Toast.makeText(this, "Select Video", Toast.LENGTH_SHORT).show();
        }
        else
        {
           // NetworkCall.fileUpload(videofilePath,String.valueOf(qna_id));
            if(qna_id!=0)                                                   // <------------------------------------------------
                NetworkCall.fileUpload(videofilePath,String.valueOf(qna_id),"0");
            else if(my_story_id!=0)
                NetworkCall.fileUpload(videofilePath,"0",String.valueOf(my_story_id));
            progressDialog= new ProgressDialog(this);
            progressDialog.setMessage("Uploading Video");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

    }

    public void uploadAButtonClicked(View view)
    {

        String name = nameEditText.getText().toString();
        int age = Integer.parseInt(ageEditText.getText().toString());
        //NetworkCall.fileUpload(filePath, new ImageSenderInfo(name,age));

        //user_id=edituserid.getText().toString().trim();
        if(audiofilePath.equals(""))
        {
            Toast.makeText(this, "Select Audio", Toast.LENGTH_SHORT).show();
        }
        else
        {
           // NetworkCall.fileUpload(audiofilePath,String.valueOf(qna_id));
            if(qna_id!=0)                                                   // <------------------------------------------------
                NetworkCall.fileUpload(audiofilePath,String.valueOf(qna_id),"0");
            else if(my_story_id!=0)
                NetworkCall.fileUpload(audiofilePath,"0",String.valueOf(my_story_id));
            progressDialog= new ProgressDialog(this);
            progressDialog.setMessage("Uploading Audio");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    private String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };//,Video,Audio
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);//Video
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    private String getVideoPath(Uri uri) {
        String[] projection = { MediaStore.Video.Media.DATA };//,Video,Audio
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);//Video,Images
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private String getAudioPath(Uri uri) {
        String[] projection = { MediaStore.Audio.Media.DATA };//,Video,Audio
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);//Video,Images
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public void onClickSubmit(View view)
    {
        new Asyncqnaagriinsurance().execute();
    }




    private  class Asyncqnaagriinsurance extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null;
        boolean status=false;

        @Override
        protected void onPreExecute() {



            pDialog = new ProgressDialog(Insurance.this);
            pDialog.setMessage("Quering...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void  doInBackground(Void... params) {
            ArrayList<NameValuePair> cred = new ArrayList<NameValuePair>();
            cred.add(new BasicNameValuePair("category_id","1"));
            cred.add(new BasicNameValuePair("sub_category_id","7"));
            cred.add(new BasicNameValuePair("dropdown",strsubgroup));
            cred.add(new BasicNameValuePair("questext",edittext.getText().toString()));
            cred.add(new BasicNameValuePair("user_id",str_user_id));//
         /*   cred.add(new BasicNameValuePair("file_base64",String.valueOf(encodedImage)));//encodedImage
            cred.add(new BasicNameValuePair("file_ext",".png"));*/


            if(mystory.equals("success")||mystory.equals("failure"))        // <------------------------------------------------
            {
                urlRouteList= Config.apimystory;
            }
            else
            {
                urlRouteList= Config.apiaqna;
            }

            try {
                String route_response = CustomHttpClient.executeHttpPost(urlRouteList, cred);
                Log.v(" ", "Response is " + route_response);

                success = route_response;

                JSONObject jsonObject = new JSONObject(success);
                status =jsonObject.getBoolean("status");
                if(mystory.equals("success")||mystory.equals("failure"))       // <------------------------------------------------
                {
                    my_story_id=jsonObject.getInt("my_story_id");
                    Log.d("my_story_id", String.valueOf(my_story_id));
                }
                else
                {
                    qna_id = jsonObject.getInt("qna_id");
                    Log.d("qnaId", String.valueOf(qna_id));
                }

            } catch (Exception e)

            {
                Log.v("ONMESSAGE", e.toString());

            }


            return null;

        }

        protected void onPostExecute(Void result) {
            pDialog.dismiss();

            if(qna_id!=0)                                                       // <------------------------------------------------
                editqid_mystoryid.setText(String.valueOf(qna_id));
            else if(my_story_id!=0)
                editqid_mystoryid.setText(String.valueOf(my_story_id));

            if(status)
            {
                   /*Snackbar snackbar = Snackbar
                           .make(findViewById(android.R.id.content), "Question Asked Successfully", Snackbar.LENGTH_LONG);

                   snackbar.show();*/
            }
            else
            {
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), "Question Not Submitted", Snackbar.LENGTH_LONG);

                snackbar.show();
            }


        }



    }
}
