package com.pifss.bbadmin;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

public class CampaignAdd extends AppCompatActivity {
    private GoogleMap mMap;
    MarkerOptions markerOptions;

    Double llat;
    Double llong;

    EditText txtCampName;
    TextView txtDateFrom;
    TextView txtDateTo;
    EditText txtDescription;
    EditText txtLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_add);

        Toast.makeText(CampaignAdd.this, getString(R.string.AddCampaign_title), Toast.LENGTH_SHORT).show();


        Toolbar toolbar = (Toolbar) findViewById(R.id.campAdd_toolbarID);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle(getString(R.string.AddCampaign_title));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // REFERENCING
       txtCampName = (EditText) findViewById(R.id.campAdd_txtCampNameID);
       txtDateFrom = (TextView) findViewById(R.id.campAdd_txtDateFromID);
       txtDateTo = (TextView) findViewById(R.id.campAdd_txtDateToID);
       txtDescription = (EditText) findViewById(R.id.campAdd_txtDescriptionID);
       txtLocation = (EditText) findViewById(R.id.campAdd_txtLocationID);
        Button btnAdd = (Button) findViewById(R.id.campAdd_btnAddID);


        // CHOOSE THE DATES
        txtDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog d=new DatePickerDialog(CampaignAdd.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog d=new DatePickerDialog(CampaignAdd.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtDateTo.setText(year+"-"+month+"-"+dayOfMonth);
                    }
                },2017,0,1);

                d.show();
            }
        });


        // MAP
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.campAdd_mapID);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                LatLng location = new LatLng(29.3117, 47.4818);
                markerOptions = new MarkerOptions().position(location).title("Kuwait").draggable(true);
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10.0f));

                mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

                    @Override
                    public void onMarkerDragStart(Marker marker) {
                    }
                    @Override
                    public void onMarkerDrag(Marker marker) {
                    }
                    @Override
                    public void onMarkerDragEnd(Marker marker) {

                        llat = marker.getPosition().latitude;
                        llong = marker.getPosition().longitude;

                    }
                });

            }
        });




        // ADD NEW CAMPAGIN
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url="http://34.196.107.188:8081/MhealthWeb/webresources/callfordonation/";
                final RequestQueue queue= NetworkRequest.getInstance().getRequestQueue(CampaignAdd.this);

                JSONObject campaignJson=new JSONObject();
                try {
                    campaignJson.put("CFDId", 0);
                    campaignJson.put("LLat", llat);
                    campaignJson.put("LLong", llong);
                    campaignJson.put("bloodTypes", txtDescription.getText().toString());
                    campaignJson.put("enddate", txtDateTo.getText().toString());
                    campaignJson.put("locationName", txtLocation.getText().toString());
                    campaignJson.put("name", txtCampName.getText().toString());
                    campaignJson.put("startdate", txtDateFrom.getText().toString());

// needs if statement for langauge
                    campaignJson.put("status", "Active");



                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, url, campaignJson, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(CampaignAdd.this, (getString(R.string.Toast_Camp_Added)), Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CampaignAdd.this, "NO, but added", Toast.LENGTH_LONG).show();
                        clearAll();
                         finish();

                    }
                });

                queue.add(request);

            }
        });

    }

    void clearAll(){
         txtCampName.setText("");
         txtDateFrom.setText("From");
         txtDateTo.setText("To");
         txtDescription.setText("");
         txtLocation.setText("");

    }

}
