package com.grazactech.jsonparse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JSONParseActivity extends AppCompatActivity {
    private Button button_getJSONObject;
    private Button button_getJSONArray;
    private final String EXTRA_JSON_OBJECT_INDEX = "com.grazactech.jsonparse";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonparse);


        button_getJSONArray = findViewById(R.id.JSONArray);
        button_getJSONObject =  findViewById(R.id.JSONObject);

        button_getJSONObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ParseJSONObject.class);
                intent.putExtra(EXTRA_JSON_OBJECT_INDEX,0);
                startActivity(intent);
            }
        });

        button_getJSONArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),ParseJSONArray.class);
                startActivity(intent);
            }
        });

    }
}
