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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

public class CampaignAdd extends AppCompatActivity {
    private GoogleMap mMap;

    EditText txtCampName;
    TextView txtDateFrom;
    TextView txtDateTo;
    EditText txtDescription;
    EditText txtLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.campAdd_toolbarID);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle("Add Campaign");
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




        // ADD NEW CAMPAGIN
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url="http://34.196.107.188:8081/MhealthWeb/webresources/callfordonation/";
                final RequestQueue queue= NetworkRequest.getInstance().getRequestQueue(CampaignAdd.this);

                JSONObject campaignJson=new JSONObject();
                try {
                    campaignJson.put("CFDId", 0);
                    campaignJson.put("LLat", "24.093798");
                    campaignJson.put("LLong", "32.886887");
                    campaignJson.put("bloodTypes", txtDescription.getText().toString());
                    campaignJson.put("enddate", txtDateTo.getText().toString());
                    campaignJson.put("locationName", txtLocation.getText().toString());
                    campaignJson.put("name", txtCampName.getText().toString());
                    campaignJson.put("startdate", txtDateFrom.getText().toString());
                    campaignJson.put("status", "Active");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, url, campaignJson, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(CampaignAdd.this, "Campaign Added Successfuly!", Toast.LENGTH_LONG).show();
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
