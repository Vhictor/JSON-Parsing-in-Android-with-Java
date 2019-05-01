package com.grazactech.jsonparse;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class ParseJSONArrayObject extends AppCompatActivity {
    private static final String TAG ="ParseJSONObject";
    private Mobile mMobile;
    private TextView nameTextView;
    private TextView companyNameTextView;
    private TextView operatingSystemTextView;
    private TextView processorTextView;
    private TextView ramTextView;
    private TextView romTextView;
    private TextView frontCameraTextView;
    private TextView backCameraTextView;
    private TextView screenSizeTextView;
    private TextView batteryTextView;
    private ImageView photoImageView;
    private String photoUrl;
    private final String EXTRA_JSON_OBJECT = "mobileObject";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_jsonobject_original);
        mMobile = (Mobile)getIntent().getSerializableExtra(EXTRA_JSON_OBJECT);
        nameTextView =(TextView)findViewById(R.id.edit_text_name);
        companyNameTextView =(TextView)findViewById(R.id.edit_text_company_name);
        operatingSystemTextView =(TextView)findViewById(R.id.edit_text_operating_system);
        processorTextView = (TextView)findViewById(R.id.edit_text_processor);
        ramTextView = (TextView)findViewById(R.id.edit_text_ram);
        romTextView = (TextView)findViewById(R.id.edit_text_rom);
        frontCameraTextView = (TextView)findViewById(R.id.edit_text_front_camera);
        backCameraTextView = (TextView)findViewById(R.id.edit_text_back_camera);
        screenSizeTextView = (TextView)findViewById(R.id.edit_text_screen_size);
        batteryTextView = (TextView)findViewById(R.id.edit_text_battery);
        photoImageView = (ImageView)findViewById(R.id.image_view_mobile_picture);
        final ProgressDialog pDialog = new ProgressDialog(ParseJSONArrayObject.this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        nameTextView.setText("Name :" + mMobile.getmName());
        companyNameTextView.setText("Company :" + mMobile.getmCompanyName());
        operatingSystemTextView.setText(" OS :" + mMobile.getmOperatingSystem());
        processorTextView.setText("Processor :" + mMobile.getmProcessor());
        ramTextView.setText("RAM :"+mMobile.getmRam());
        romTextView.setText("Memory :"+mMobile.getmRom());
        frontCameraTextView.setText("Front Camera :"+mMobile.getmFrontCamera());
        backCameraTextView.setText("Rear Camera :"+mMobile.getmBackCamera());
        screenSizeTextView.setText("Screen Size :"+mMobile.getmScreenSize());
        batteryTextView.setText("Battery :"+mMobile.getmBattery());
        photoUrl = (mMobile.getmUrl());
        ImageLoader imageLoader = new ImageLoader(Volley.newRequestQueue(getApplicationContext()),
                new LruBitmapCache());
        imageLoader.get(photoUrl, new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Image Load Error: " + error.getMessage());
            }
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                  //  load image into imageview
                    photoImageView.setImageBitmap(response.getBitmap());
                    pDialog.hide();
                }
            }
        });
    }
}

