package com.example.holidayhelper.holidayhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "HolidayHelper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageButton SearchHoliday = findViewById(R.id.SearchHoliday);
        final ImageButton SearchDate = findViewById(R.id.SearchDate);
        SearchHoliday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "SearchHoliday clicked.");
            }
        });
        SearchDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "SearchDate clicked.");
            }
        });
    }

}
