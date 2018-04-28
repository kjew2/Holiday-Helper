package com.example.holidayhelper.holidayhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.util.Log;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.android.volley.toolbox.JsonObjectRequest;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "HolidayHelper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageButton Search = findViewById(R.id.Search);
        final EditText editText = findViewById(R.id.EnterHoliday);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                Log.d(TAG, "Search clicked." + text);
            }
        });

    }

}
