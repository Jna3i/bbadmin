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
import android.widget.Button;
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

public class CampaignList extends AppCompatActivity {
    ArrayList<Campaign>  model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.campList_toolbarID);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle("Campaigns");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final ListView listView= (ListView) findViewById(R.id.campList_listViewID);


        String url="http://34.196.107.188:8080/mHealthWS/ws/newcallfordonation";
        final RequestQueue queue= NetworkRequest.getInstance().getRequestQueue(this);

        final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                model=new Gson().fromJson(response,new TypeToken<ArrayList<Campaign>>(){}.getType());

                CampainAdapter adapter=new CampainAdapter(model,CampaignList.this);
                listView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CampaignList.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Campaign campaign= model.get(position);
                Intent i = new Intent(CampaignList.this, CampaignInfo.class);
                i.putExtra("campaign", campaign);
                startActivity(i);
            }
        });



        // complete the delete feature

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder=new AlertDialog.Builder(CampaignList.this);

                builder.setTitle("Delete")
                        .setMessage("Are you sure you want to delete this campaign?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });



                final Dialog dConfirm=builder.create();
                dConfirm.show();

                return false;
            }
        });



    }

}
