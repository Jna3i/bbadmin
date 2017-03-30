package com.pifss.bbadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Notification_Send extends AppCompatActivity {

    private JsonObjectRequest objectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification__send);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final EditText etTitle = (EditText) findViewById(R.id.Notification_send_title);
        final EditText etDescrip = (EditText) findViewById(R.id.Notification_send_description);
        Button button = (Button) findViewById(R.id.notification_send_button);


        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final RequestQueue requestQueue = NetworkRequest.getInstance().getRequestQueue(this);
        final String url ="http://34.196.107.188:8081/MhealthWeb/webresources/bbnotification/";
        Calendar calendar= Calendar.getInstance();
        String dateformat = "yyyy-mm-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
        final String currentdate = sdf.format(calendar.getTime());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject jsonObject= new JSONObject();
                try {
                    jsonObject.put("date",currentdate);
                    jsonObject.put("description",etDescrip.getText());
                    jsonObject.put("status",1);
                    jsonObject.put("title",etTitle.getText());


                    objectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }

                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                requestQueue.add(objectRequest);

                finish();
            }
        });
    }
}
