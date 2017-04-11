package cn.edu.scut.wweiran.fragmentpractice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MultiMediaActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_STILL_IMAGE_CAMERA = 2;
    private static final int REQUEST_VIDEO_CAMERA = 3;

    private Button mButton1, mButton2, mButton3;
    private ImageView mImageView1;

    private Uri mLocationForPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);

        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                intent1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.withAppendedPath(mLocationForPhoto, "test"));
                if (intent1.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent1, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        mImageView1 = (ImageView) findViewById(R.id.imageView1);
        mImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_STILL_IMAGE_CAMERA);
                }
            }
        });

        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_VIDEO_CAMERA);
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap bitmap = (Bitmap) extras.get("data");

            Bitmap thumbnail = data.getParcelableExtra("data");
            mImageView1.setImageBitmap(thumbnail);
        } else if (requestCode == REQUEST_STILL_IMAGE_CAMERA && resultCode == RESULT_OK) {
            Toast.makeText(this, "still_image", Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_VIDEO_CAMERA && requestCode == RESULT_OK) {
            Toast.makeText(this, "video_image", Toast.LENGTH_SHORT).show();
        }
    }


}
