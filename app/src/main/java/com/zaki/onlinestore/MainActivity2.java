package com.zaki.onlinestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;


import com.android.volley.RequestQueue;

import com.android.volley.toolbox.StringRequest;


public class MainActivity2 extends AppCompatActivity {
    private RequestQueue RequestQueue;
    private StringRequest StringRequest;
    private String url = "https://83f4wd4i79.execute-api.ap-south-1.amazonaws.com/login/email/password";
    Button BACK ,SignUp ,LOGIN;
    TextView Email , Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

         BACK = findViewById(R.id.BACKBTN);

         LOGIN = findViewById(R.id.LOGINBUYERCREDENTIALS);
         Email = findViewById(R.id.EMAILBPT);
         Password = findViewById(R.id.PASSBPT);


        BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(i);
            }
        });

        LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });


        }
    private void getData() {
        // RequestQueue initialized
        RequestQueue = Volley.newRequestQueue(this);

        // String Request initialized
        StringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>()    {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity2.this, last.class);
                startActivity(i);
                //display the response on screen
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"PLEASE ENTER INFO CORRECTLY",Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue.add(StringRequest);
    }
}


