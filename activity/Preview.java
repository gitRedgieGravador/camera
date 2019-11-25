package com.example.mrclay;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class Preview extends AppCompatActivity {
    private Button takePictureButton;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview);
        takePictureButton = (Button) findViewById(R.id.button_image);
        imageView = (ImageView) findViewById(R.id.imageview);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePictureButton.setEnabled(true);
            }
        }
    }
    private static Uri file;
    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(getOutputMediaFile());
        Intent intent1 = intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
        startActivityForResult(intent1, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(file);
                //imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        }
    }
    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "DailyFaces");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
    }
    public void PostNow(View view){

    }
    public void takeFromGallery(View view){

    }
//    @Override
//    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
//        super.onActivityResult(reqCode, resultCode, data);
//
//
//        if (resultCode == RESULT_OK) {
//            try {
//                final Uri imageUri = data.getData();
//                final Bitmap selectedImage;
//                try (InputStream imageStream = getContentResolver().openInputStream(imageUri)) {
//                    selectedImage = BitmapFactory.decodeStream(imageStream);
//                }
//                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), selectedImage);
//
//                circularBitmapDrawable.setCircular(true);
//                imageView.setImageDrawable(circularBitmapDrawable);
//            } catch (Exception e) {
//                e.printStackTrace();
//                //Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
//            }
//
//        }else {
//            //Toast.makeText(MainActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
//        }
//    }
}
