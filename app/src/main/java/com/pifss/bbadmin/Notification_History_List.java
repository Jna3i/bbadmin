package com.pifss.bbadmin;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Notification_History_List extends AppCompatActivity {

    private static String url;
    ArrayList<Notification> model;
    private ListView listView;
    private Notification notification;
    private NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification__history__list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.Notification_Toolbar_List);
        toolbar.setTitle("Notifications");
        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.Notification_history_list);

        final RequestQueue requestQueue = NetworkRequest.getInstance().getRequestQueue(Notification_History_List.this);
        url ="http://34.196.107.188:8081/MhealthWeb/webresources/bbnotification/";

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                model=new Gson().fromJson(response,new TypeToken<ArrayList<Notification>>(){}.getType());

                NotificationAdapter adapter=new NotificationAdapter(model,Notification_History_List.this);
                listView.setAdapter(adapter);





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Notification_History_List.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                notification = model.get(position);
                Intent i = new Intent(Notification_History_List.this,Notification_View.class);

                i.putExtra("notification",notification);
                startActivity(i);
            }
        });

    }
}
