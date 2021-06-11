package com.pulkitgangwar.weatherapp;


import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText cityInput;
    TextView cityText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout container = (ConstraintLayout) findViewById(R.id.container);


          btn = (Button) findViewById(R.id.container__button);
          cityInput = (EditText) findViewById(R.id.container__input);
          cityText = (TextView) findViewById(R.id.container__output);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("oncreate","inside the onclick");
                getWeatherInformation();

            }
        });
    }


    private void getWeatherInformation() {
        String apiKey = "d5276286f613266541840ff100599082";
        String city = cityInput.getText().toString();
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+ city +"&appid="+ apiKey;
        Log.d("oncreate",url);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject obj = response.getJSONObject("main");
                    double temp = obj.getDouble("temp");
                    DecimalFormat df = new DecimalFormat("#,###,##0.00");
                    double celcius = temp - 273.15;


                    cityText.setText(String.valueOf( df.format(celcius) + " °C"));

//                    Switch unitSwitch = findViewById(R.id.container__switch);
//
//
//                    if(unitSwitch.isChecked()) {
//                        DecimalFormat df = new DecimalFormat("#.00");
//                        double fahrenheit = ((temp - 273.15) * (9/5) + 32);
//                        cityText.setText(String.valueOf(fahrenheit));
//                    } else {
//                        double celcius = temp - 273.15;
//                        cityText.setText(String.valueOf(celcius));
//                    }



                } catch(JSONException err) {
                    Toast.makeText(getApplicationContext(),err.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError err) {
                Toast.makeText(getApplicationContext(),err.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);

    }





}