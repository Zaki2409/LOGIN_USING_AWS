package com.zaki.onlinestore;

import androidx.appcompat.app.AppCompatActivity;
import static android.content.ContentValues.TAG;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private String url = "https://83f4wd4i79.execute-api.ap-south-1.amazonaws.com/register";
    private Button LOGIN, REGISTER;
    private TextView BUYERNAME, BUYEREMAIL, BUYERMOBILE, BUYERPASS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        LOGIN = findViewById(R.id.SELLERLOGIN);
        REGISTER = findViewById(R.id.REGISTERSELLER);
        BUYEREMAIL = findViewById(R.id.SELLEREMAIL);
        BUYERPASS = findViewById(R.id.SELLERPASSWORD);
        BUYERMOBILE = findViewById(R.id.SELLERPHONE);
        BUYERNAME = findViewById(R.id.SELLERNAME);

        LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(i);
            }
        });

        REGISTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });
    }

    public class MyRequestData {
        public String email;
        public String buyername;
        public String password;
        public String mobile;
    }

    private void postData() {
        MyRequestData requestData = new MyRequestData();
        requestData.email = BUYEREMAIL.getText().toString().trim();
        requestData.buyername = BUYERNAME.getText().toString().trim();
        requestData.password = BUYERPASS.getText().toString().trim();
        requestData.mobile = BUYERMOBILE.getText().toString().trim();

        requestQueue = Volley.newRequestQueue(MainActivity3.this);

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Response: " + response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error: " + error.toString());
                if (error.networkResponse != null) {
                    Log.e(TAG, "Status Code: " + error.networkResponse.statusCode);
                    Log.e(TAG, "Response Data: " + new String(error.networkResponse.data));
                }
            }
        }) {
            @Override
            public byte[] getBody() {
                try {
                    // Convert MyRequestData to JSON
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("email", requestData.email);
                    jsonBody.put("buyername", requestData.buyername);
                    jsonBody.put("password", requestData.password);
                    jsonBody.put("mobile", requestData.mobile);
                    return jsonBody.toString().getBytes("utf-8");
                } catch (JSONException e) {
                    Log.e(TAG, "Error converting MyRequestData to JSON", e);
                    return null;
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        requestQueue.add(stringRequest);
    }

}

