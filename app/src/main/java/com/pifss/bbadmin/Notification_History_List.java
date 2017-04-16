package com.pifss.bbadmin;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
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
    Notification notification;
    static NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification__history__list);

        //TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.Notification_Toolbar_List);
        toolbar.setTitle(getString(R.string.activity_notificationHistory));
        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //REFERENCING TO LAYOUT
        listView = (ListView) findViewById(R.id.Notification_history_list);


        // FILL THE LIST
        showNotificationList();

        //GO TO DETAILS PAGE
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                notification = model.get(position);
                Intent i = new Intent(Notification_History_List.this, Notification_View.class);

                i.putExtra("notification", notification);
                startActivity(i);
            }
        });

        //delete
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Notification_History_List.this);
                builder.setTitle("Delete")
                        .setMessage("Are you sure you want to delete this notification?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notification = model.get(position);
                                String url = "http://34.196.107.188:8081/MhealthWeb/webresources/bbnotification/"+notification.getNotifId();
                                RequestQueue queue = NetworkRequest.getInstance().getRequestQueue(Notification_History_List.this);
                                StringRequest request = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        model.remove(position);
                                        adapter.notifyDataSetChanged();
                                        Toast.makeText(Notification_History_List.this, "Deleted", Toast.LENGTH_SHORT).show();
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {


                                       Toast.makeText(Notification_History_List.this, "fail", Toast.LENGTH_SHORT).show();

                                    }

                                });
                                queue.add(request);
                            }
                        });
                final Dialog dConfirm = builder.create();
                dConfirm.show();
                return true;
            }


        });
    }
        void showNotificationList(){
            final RequestQueue requestQueue = NetworkRequest.getInstance().getRequestQueue(Notification_History_List.this);
            url = "http://34.196.107.188:8081/MhealthWeb/webresources/bbnotification/";

            final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    model = new Gson().fromJson(response, new TypeToken<ArrayList<Notification>>() {
                    }.getType());

                    adapter = new NotificationAdapter(model, Notification_History_List.this);
                    listView.setAdapter(adapter);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Notification_History_List.this, "Failed", Toast.LENGTH_SHORT).show();

                }
            });
            requestQueue.add(stringRequest);

        }

    @Override
    protected void onRestart() {
        super.onRestart();
        showNotificationList();
    }
}


