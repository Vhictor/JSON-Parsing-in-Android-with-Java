package com.grazactech.jsonparse;


import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class ParseJSONObjectOriginal extends AppCompatActivity {


    private static final String TAG = "ParseJSONObject";
    private final String EXTRA_JSON_OBJECT_INDEX = "com.grazactech.jsonparse";
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
    String url = "https://androidtutorialpoint.com/api/MobileJSONObject.json";

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_jsonobject_original);

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            //Do this online
            nameTextView = (TextView) findViewById(R.id.edit_text_name);
            companyNameTextView = (TextView) findViewById(R.id.edit_text_company_name);
            operatingSystemTextView = (TextView) findViewById(R.id.edit_text_operating_system);
            processorTextView = (TextView) findViewById(R.id.edit_text_processor);
            ramTextView = (TextView) findViewById(R.id.edit_text_ram);
            romTextView = (TextView) findViewById(R.id.edit_text_rom);
            frontCameraTextView = (TextView) findViewById(R.id.edit_text_front_camera);
            backCameraTextView = (TextView) findViewById(R.id.edit_text_back_camera);
            screenSizeTextView = (TextView) findViewById(R.id.edit_text_screen_size);
            batteryTextView = (TextView) findViewById(R.id.edit_text_battery);
            photoImageView = (ImageView) findViewById(R.id.image_view_mobile_picture);

            final ProgressDialog progressDialog = new ProgressDialog(ParseJSONObjectOriginal.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    mMobile = JSONParser.parseFeed(response);
                    nameTextView.setText("Name :" + mMobile.getmName());
                    companyNameTextView.setText("Company :" + mMobile.getmCompanyName());
                    operatingSystemTextView.setText(" OS :" + mMobile.getmOperatingSystem());
                    processorTextView.setText("Processor :" + mMobile.getmProcessor());
                    ramTextView.setText("RAM :" + mMobile.getmRam());
                    romTextView.setText("Memory :" + mMobile.getmRom());
                    frontCameraTextView.setText("Front Camera :" + mMobile.getmFrontCamera());
                    backCameraTextView.setText("Rear Camera :" + mMobile.getmBackCamera());
                    screenSizeTextView.setText("Screen Size :" + mMobile.getmScreenSize());
                    batteryTextView.setText("Battery :" + mMobile.getmBattery());
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
// load image into imageview
                                photoImageView.setImageBitmap(response.getBitmap());
                                progressDialog.hide();
                            }
                        }
                    });
                    Log.d(TAG, response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    VolleyLog.d(TAG, "Error: " + error.getMessage());
// hide the progress dialog
                    progressDialog.hide();
                }
            });
            Volley.newRequestQueue(getApplicationContext()).add(request);

        } else {
            Toast.makeText(ParseJSONObjectOriginal.this, "Network lost, Please try again later", Toast.LENGTH_LONG).show();
        }
    }
}

