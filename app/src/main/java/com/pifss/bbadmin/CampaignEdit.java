package com.pifss.bbadmin;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CampaignEdit extends AppCompatActivity {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_edit);

        // TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.campEdit_toolbarID);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle("Edit Campaign");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // REFERENCING
        final TextView txtCampName = (TextView) findViewById(R.id.campEdit_txtCampNameID);
        final TextView txtDateFrom = (TextView) findViewById(R.id.campEdit_txtDateFromID);
        final TextView txtDateTo = (TextView) findViewById(R.id.campEdit_txtDateToID);
        final TextView txtDescription = (TextView) findViewById(R.id.campEdit_txtDescriptionID);
        final TextView txtLocation = (TextView) findViewById(R.id.campEdit_txtLocationID);
        Button btnSave = (Button) findViewById(R.id.campEdit_btnSaveID);


        // FILL THE UI ELEMENTS
        final Campaign campaign = (Campaign) getIntent().getSerializableExtra("campaign");
        txtCampName.setText(campaign.getName());
        txtDateFrom.setText(campaign.getStartdate());
        txtDateTo.setText(campaign.getEnddate());
        txtDescription.setText(campaign.getBloodTypes());
        txtLocation.setText(campaign.getLocationName());


        // MAP
        MapView mapFragment = (MapView) findViewById(R.id.campEdit_mapID);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                Double llat = Double.parseDouble(campaign.getLLat());
                Double llong = Double.parseDouble(campaign.getLLong());

                LatLng location = new LatLng(llat, llong );
                mMap.addMarker(new MarkerOptions().position(location).title(campaign.getLocationName()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            }
        });


        // CHOOSE THE DATES
        txtDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog d=new DatePickerDialog(CampaignEdit.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtDateFrom.setText(year+"-"+month+"-"+dayOfMonth);
                    }
                },2017,1,1);

                d.show();
            }
        });

        txtDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog d=new DatePickerDialog(CampaignEdit.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtDateTo.setText(year+"-"+month+"-"+dayOfMonth);
                    }
                },2017,0,1);

                d.show();
            }
        });


        // BUTTON SAVE TO UPDATE THE ITEM
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url="http://34.196.107.188:8081/MhealthWeb/webresources/callfordonation/"+campaign.getCFDId();
                final RequestQueue queue= NetworkRequest.getInstance().getRequestQueue(CampaignEdit.this);

                JSONObject campaignJson=new JSONObject();
                try {
                    campaignJson.put("CFDId", campaign.getCFDId() );
                    campaignJson.put("LLat", campaign.getLLat());
                    campaignJson.put("LLong", campaign.getLLong());
                    campaignJson.put("bloodTypes", txtDescription.getText().toString());
                    campaignJson.put("enddate", txtDateTo.getText().toString());
                    campaignJson.put("locationName", txtLocation.getText().toString());
                    campaignJson.put("name", txtCampName.getText().toString());
                    campaignJson.put("startdate", txtDateFrom.getText().toString());
                    campaignJson.put("status",campaign.getStatus());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request=new JsonObjectRequest(Request.Method.PUT, url, campaignJson, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //System.out.println(error);
                        finish();

                    }
                });

                queue.add(request);

            }
        });

     }
}
