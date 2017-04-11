package com.pifss.bbadmin.BloodRequests;

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
import com.pifss.bbadmin.Links;
import com.pifss.bbadmin.NetworkRequest;
import com.pifss.bbadmin.R;

import java.util.ArrayList;

public class bloodRequest_List extends AppCompatActivity {

    ArrayList<DoctorsHandler> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_request__list);

        // TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.bloodRequestsList_toolbarID);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle(getString(R.string.activity_bloodRequests));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        final ListView BloodRequestsList = (ListView) findViewById(R.id.bloodRequestList);
        String url = Links.BLOODREQUEST;

        final RequestQueue queue= NetworkRequest.getInstance().getRequestQueue(bloodRequest_List.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                model=new Gson().fromJson(response,new TypeToken<ArrayList<DoctorsHandler>>(){}.getType());

                bloodRequestsListAdapter adapter = new bloodRequestsListAdapter(model,bloodRequest_List.this);
                BloodRequestsList.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(bloodRequest_List.this, "Failed to Load Data", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);

        BloodRequestsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DoctorsHandler doctorhandler = model.get(position);
                Intent i = new Intent(bloodRequest_List.this,BloodRequestInfo.class);

                i.putExtra("doctorhandler",doctorhandler);
                startActivity(i);
            }
        });
    }
}
