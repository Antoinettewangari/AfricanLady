package com.example.user.africanlady;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView image,btnCamera,btnGallery;

    private final int REQUEST_IMAGE_CAPTURE=1, REQUEST_IMAGE_GALLERY=2;
    Button button;
    BottomSheetBehavior bottomSheetBehavior;
    ImageView share,save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        share=(ImageView)findViewById(R.id.share);
        save=(ImageView)findViewById(R.id.save);
        image=(ImageView)findViewById(R.id.image);
        btnCamera=(ImageView) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(this);
        btnGallery=(ImageView)findViewById(R.id.btnGallery);
        btnGallery.setOnClickListener(this);


        View bottomsheet=findViewById(R.id.bottom_sheet);
        bottomSheetBehavior=BottomSheetBehavior.from(bottomsheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bottomSheetBehavior.getState()!=BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    button.setText("Collapse sheet");
                }
                else{
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    button.setText("Expand sheet");
                }
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent= new Intent(Intent.ACTION_SEND);
                myintent.setType("text/plain");
                String sharebody="Your body here";
                String sharesub="Subject here";
                myintent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
                myintent.putExtra(Intent.EXTRA_TEXT,sharebody);
                startActivity(Intent.createChooser(myintent,"Share using"));

            }
        });



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCamera:
                Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,REQUEST_IMAGE_CAPTURE);

                break;
            case R.id.btnGallery:
                Intent iGallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                iGallery.setType("image/*");
                startActivityForResult(iGallery,REQUEST_IMAGE_GALLERY);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (requestCode==REQUEST_IMAGE_CAPTURE){
                Bitmap bitmap=(Bitmap)data.getExtras().get("data");
                image.setImageBitmap(bitmap);
            }else {
                if (requestCode == REQUEST_IMAGE_GALLERY) {
                    Uri uri=data.getData();
                    Bitmap bitmap= null;
                    try{
                        bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                        image.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        }



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_main,menu);
        return true;
    }
}
