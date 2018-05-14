package ratnatech.farmerapp.AskQHire;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ratnatech.farmerapp.R;
import ratnatech.farmerapp.Utility;

public class TakeOnHire extends AppCompatActivity {


    EditText edittext;
    LinearLayout layoutimagepreview,layoutaudiopath,layoutvideopath;
    ImageView ivImage;

    TextView btnSelectPhoto,chooseAudio,filepath,chooseVideo,vfilepath;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1,RECORD_SOUND_ACTION=2,SELECT_VIDEO=3;
    private String userChoosenTask;
    String mImageName;
    File mediaFile;
    File mediaStorageDir;

    String theFilePath;

    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    private Uri fileURi_1; // file url to store image/video

    //------------Camera video recording
    VideoView vdoView;
    private Uri fileUri;
    public static final int MEDIA_TYPE_VIDEO = 2;
    static final int REQUEST_VIDEO_CAPTURE = 1;
    static String strSDCardPathName = Environment.getExternalStorageDirectory() + "/temp_video" + "/";
    static String strFileName = "";

    // DATE TIME PICKER
    Button btnStartTime,btnEndTime;
    TextView txttime,txtdate;
    Button btntimepicker,btndatepicker;
    static int hour, min;
    java.sql.Time timeValue;
    SimpleDateFormat format;
    Calendar c;
    int year, month, day;

    int monthOfYear=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_on_hire);

        edittext = (EditText)findViewById(R.id.edittext);
        btnSelectPhoto=(TextView)findViewById(R.id.btnSelectPhoto);
        chooseAudio=findViewById(R.id.chooseAudio);
        chooseVideo=findViewById(R.id.chooseVideo);
        layoutimagepreview=(LinearLayout)findViewById(R.id.layoutimagepreview);
        layoutaudiopath=findViewById(R.id.layoutaudiopath);
        layoutvideopath=findViewById(R.id.layoutvideopath);
        filepath=findViewById(R.id.filepath);
        ivImage=(ImageView)findViewById(R.id.ivImage);
        vfilepath=findViewById(R.id.vfilepath);

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
        getSupportActionBar().setTitle("Hire/Take On Hire");

        // date time code
        txttime=findViewById(R.id.txttime);
        txtdate=findViewById(R.id.txtdate);
        btntimepicker=findViewById(R.id.btntimepicker);
        btndatepicker=findViewById(R.id.btndatepicker);
        c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE);

        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        txtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date

                DatePickerDialog dd = new DatePickerDialog(TakeOnHire.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                try {
                                    String dateInString="";
                                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                    dateInString= dayOfMonth + "/" + (month + 1) + "/" + year;
                                    Date date = formatter.parse(dateInString);

                                   /* txtdate.setText(formatter.format(date).toString());

                                    formatter = new SimpleDateFormat("dd/MMM/yyyy");

                                    txtdate.setText(txtdate.getText().toString()+"\n"+formatter.format(date).toString());*/

                                    formatter = new SimpleDateFormat("dd-MM-yyyy");


                                    txtdate.setText(formatter.format(date).toString());// txtdate.getText().toString()+ "/n"

                                  /*  formatter = new SimpleDateFormat("dd.MMM.yyyy");

                                    txtdate.setText(txtdate.getText().toString()+"\n"+formatter.format(date).toString());*/

                                } catch (Exception ex) {

                                }
                            }


                        }, year, month, day);
                dd.show();
            }
        });
        txttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TimePickerDialog td = new TimePickerDialog(TakeOnHire.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                try {
                                    String strmin="";
                                    String dtStart = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
                                    format = new SimpleDateFormat("HH:mm");

                                    timeValue = new java.sql.Time(format.parse(dtStart).getTime());
                                    // txttime.setText(String.valueOf(timeValue));
                                    int HOUR= hourOfDay % 12;
                                    if(HOUR==0)
                                    {
                                        HOUR=12;
                                    }
                                    if (String.valueOf(minute).length()<2)
                                    {
                                        strmin="0"+String.valueOf(minute);

                                    }
                                    else {
                                        strmin=String.valueOf(minute);
                                    }
                                    String amPm = HOUR+ ":" + strmin + " " + ((hourOfDay >= 12) ? "PM" : "AM");//minute
                                    txttime.setText(amPm );// + "\n"+ String.valueOf(timeValue)
                                } catch (Exception ex) {
                                    txttime.setText(ex.getMessage().toString());
                                }
                            }
                        },
                        hour, min,
                        DateFormat.is24HourFormat(TakeOnHire.this)
                );
                td.show();
            }
        });


        btnSelectPhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                layoutimagepreview.setVisibility(View.GONE);
                selectImage();
            }
        });

        chooseAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutaudiopath.setVisibility(View.GONE);
               /* Intent recordIntent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                startActivityForResult(recordIntent, RECORD_SOUND_ACTION);//RESULT_OK*/
                selectAudio();
            }
        });

        chooseVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutvideopath.setVisibility(View.GONE);
                selectVideo();
            }
        });

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // *** Create Folder
        createFolder();
        // *** VideoView
        vdoView = (VideoView) findViewById(R.id.vdoView);
        // *** Camera Recorder
        final Button btnRecorder = (Button) findViewById(R.id.btnRecorder);
        // Perform action on click
        btnRecorder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                    fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
                    takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    takeVideoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                    startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
                }
            }
        });

        // *** Play
        final Button btnPlay = (Button) findViewById(R.id.btnPlay);
        // Perform action on click
        btnPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vdoView.setVideoURI(Uri.parse(strSDCardPathName + strFileName));
                vdoView.setMediaController(new MediaController(TakeOnHire.this));
                vdoView.requestFocus();
                vdoView.start();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                    Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(TakeOnHire.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result= Utility.checkPermission(TakeOnHire.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                    layoutimagepreview.setVisibility(View.GONE);
                }
            }
        });
        builder.show();
    }

    private void selectAudio() {
        final CharSequence[] items = { "Record Audio", "Choose from File",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(TakeOnHire.this);
        builder.setTitle("Add Audio!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result= Utility.checkPermission(TakeOnHire.this);

                if (items[item].equals("Record Audio")) {
                    userChoosenTask ="Record Audio";
                    if(result)
                    {
                        // cameraIntent();
                        Intent recordIntent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                        startActivityForResult(recordIntent, RECORD_SOUND_ACTION);
                    }


                } else if (items[item].equals("Choose from File")) {
                    userChoosenTask ="Choose from File";
                    if(result)
                    {
                        // galleryIntent();
                        Intent intent = new Intent();
                        intent.setType("audio/*");// video/*
                        intent.setAction(Intent.ACTION_GET_CONTENT);//
                        startActivityForResult(Intent.createChooser(intent, "Select File"),RECORD_SOUND_ACTION);
                    }

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                    layoutaudiopath.setVisibility(View.GONE);
                }
            }
        });
        builder.show();
    }

    private void selectVideo() {

        final CharSequence[] items = { "Choose from File",
                "Cancel" };//"Record Video",

        AlertDialog.Builder builder = new AlertDialog.Builder(TakeOnHire.this);
        builder.setTitle("Add Video!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result= Utility.checkPermission(TakeOnHire.this);

                if (items[item].equals("Record Video")) {
                    userChoosenTask ="Record Video";
                    if(result)
                    {
                        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                        fileURi_1 = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);//change to

                        // set video quality
                        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

                        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileURi_1); // set the image file
                        // name

                        // start the video capture Intent
                        startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);

                    }


                } else if (items[item].equals("Choose from File")) {
                    userChoosenTask ="Choose from File";
                    if(result)
                    {
                        Intent intent = new Intent();
                        intent.setType("video/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select a Video "), SELECT_VIDEO);
                    }

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                    layoutvideopath.setVisibility(View.GONE);
                }
            }
        });
        builder.show();
    }

    /**
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * returning image / video
     */
    private  File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"+ getPackageName()+ "/Files");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("TAG", "Oops! Failed create "
                        + Environment.getExternalStorageDirectory()
                        + "/Android/data/"+ getPackageName()+ "/Files" + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");// video/*
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
            {
                onSelectFromGalleryResult(data);
                layoutimagepreview.setVisibility(View.VISIBLE);
            }
            else if (requestCode == REQUEST_CAMERA)
            {
                onCaptureImageResult(data);
                layoutimagepreview.setVisibility(View.VISIBLE);
            }
            else if(requestCode==RECORD_SOUND_ACTION)
            {
                onSelectFromAudioResult(data);
                layoutaudiopath.setVisibility(View.VISIBLE);
            }

            else if (requestCode == SELECT_VIDEO) {

                System.out.println("SELECT_VIDEO");
                Uri selectedImageUri = data.getData();
                String selectedPath = getPath(selectedImageUri);
                vfilepath.setText(selectedPath);
                layoutvideopath.setVisibility(View.VISIBLE);
            }
            else if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE)
            {

                Uri selectedImageUri = data.getData();
                String selectedPath = getPath(selectedImageUri);
                vfilepath.setText(selectedPath);
                layoutvideopath.setVisibility(View.VISIBLE);
            }
        }


    }
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
        cursor.close();

        return path;
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");


        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d("TAG",
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            thumbnail.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
            Log.d("TAG2", "img dir: " + pictureFile);
        } catch (FileNotFoundException e) {
            Log.d("TAG3", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("TAG4", "Error accessing file: " + e.getMessage());
        }

        ivImage.setImageBitmap(thumbnail);

    }
    private  File getOutputMediaFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files");

        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }




        mImageName  = "IMG-"+System.currentTimeMillis() + ".png";

        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!(bm==null))
            ivImage.setImageBitmap(bm);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromAudioResult(Intent data) {


        if (data != null) {
            try {

                theFilePath = data.getData().toString();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        filepath.setText(theFilePath);
    }
    /**
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putParcelable("file_uri", fileURi_1);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileURi_1 = savedInstanceState.getParcelable("file_uri");
    }

    // for camera recording
    public static void createFolder() {
        File folder = new File(strSDCardPathName);
        try {
            // Create folder
            if (!folder.exists()) {
                folder.mkdir();
            }
        } catch (Exception ex) {
        }
    }

    private static Uri getOutputMediaFileUri_1(int type) {
        return Uri.fromFile(getOutputMediaFile_1(type));
    }

    private static File getOutputMediaFile_1(int type) {
// Generate File Name
        java.util.Date date = new java.util.Date();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date.getTime());

        strFileName = "Clip_" + timeStamp + ".mp4";

        File mediaFile;

        if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(strSDCardPathName + strFileName);
        } else {
            return null;
        }

        return mediaFile;
    }
}
