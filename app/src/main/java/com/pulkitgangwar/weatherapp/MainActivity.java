package com.pulkitgangwar.weatherapp;


import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout container = findViewById(R.id.container);
        this.getData(container);


    }

    private void getData(LinearLayout container) {
        String url = "https://jsonplaceholder.typicode.com/posts";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONArray jsonArray = response;
                try {
                    for(int i = 0;i<jsonArray.length();i++){
                        TextView postTextView = new TextView(getApplicationContext());
                        postTextView.setTextColor(R.color.white);
                        JSONObject post = jsonArray.getJSONObject(i);
                        String title = post.getString("title");
                        String body = post.getString("body");
                        postTextView.setText(title);
                        container.addView(postTextView);
                    }


                } catch (Exception err) {
                    Toast.makeText(getApplicationContext(),err.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError err) {
                Toast.makeText(getApplicationContext(),err.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(objectRequest);

    }



}