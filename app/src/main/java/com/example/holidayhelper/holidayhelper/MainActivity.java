package com.example.holidayhelper.holidayhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "HolidayHelper";
    private static final String COUNTRY = "US";
    private static final String YEAR = "2017";

    private static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_main);
        final ImageButton Search = findViewById(R.id.Search);
        final EditText editText = findViewById(R.id.EnterHoliday);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                Log.d(TAG, "Search clicked." + text);
                startAPICall(text);
            }
        });

    }


    void startAPICall(String date) {
        String month;
        String day;
        String year;
        if (date != null) {
            String[] splitDate = date.split("-");
            if (splitDate.length != 3) {
                return;
            }
            year = splitDate[0];
            month = splitDate[1];
            day = splitDate[2];
            Log.d(TAG,year + " " + month + " " + day);
        } else {
            return;
        }
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://holidayapi.com/v1/holidays?key=" + BuildConfig.API_KEY + "&country=" + COUNTRY + "&year=" + year + "&month=" + month + "&day=" + day ,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String json = response.toString();
                                JsonParser parser = new JsonParser();
                                Log.d(TAG, json);
                                JsonObject gSon = parser.parse(json).getAsJsonObject();
                                String status = gSon.get("status").getAsString();
                                Log.d(TAG,status);
                                if (!status.equals("200")) {
                                    return;
                                }
                                JsonArray holidays = gSon.get("holidays").getAsJsonArray();
                                //JsonArray date = holidays.get("2017-01-01").getAsJsonArray();
                                JsonObject holiday = holidays.get(0).getAsJsonObject();
                                String name = holiday.get("name").getAsString();
                                Log.d(TAG, name);
                                Boolean privacy = holiday.get("public").getAsBoolean();
                                final TextView date = findViewById(R.id.date);
                                final TextView privacyText = findViewById(R.id.privacy);
                                if (privacy) {
                                    privacyText.setText("Public");
                                } else {
                                    privacyText.setText("Not Public");
                                }
                                date.setText(name);
                            } catch (Exception e) {
                                final TextView date = findViewById(R.id.date);
                                final TextView privacyText = findViewById(R.id.privacy);
                                date.setText("No Holidays found");
                                privacyText.setText("Try another date");
                                Log.e(TAG, "JSON ERROR");
                            }
                        }
                    }, new Response.ErrorListener() {
                        public void onErrorResponse (final VolleyError error) {
                            Log.e(TAG, error.toString());
                        }
            });
            requestQueue.add(jsonObjectRequest);
                    } catch (Exception e) {
                        e.printStackTrace();
            }

    }

}
