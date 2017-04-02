package com.pifss.bbadmin;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

//NEED TO SHOW LOCATION MARKER
public class CampaignInfo extends AppCompatActivity {

    private GoogleMap mMap;
    Campaign campaign;
    TextView txtCampName;
    TextView txtDateFrom;
    TextView txtDateTo;
    TextView txtDescription;
    TextView txtLocation;
    Button btnEdit;

    Double llat;
    Double llong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_info);

        // TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.campInfo_toolbarID);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle("Campaign Information");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // REFERENCING
        txtCampName = (TextView) findViewById(R.id.campInfo_txtCampNameID);
        txtDateFrom = (TextView) findViewById(R.id.campInfo_txtDateFromID);
        txtDateTo = (TextView) findViewById(R.id.campInfo_txtDateToID);
        txtDescription = (TextView) findViewById(R.id.campInfo_txtDescriptionID);
        txtLocation = (TextView) findViewById(R.id.campInfo_txtLocationID);
        btnEdit = (Button) findViewById(R.id.campInfo_btnEditID);


        // FILL THE UI ELEMENTS
        campaign = (Campaign) getIntent().getSerializableExtra("campaign");
        txtCampName.setText(campaign.getName());
        txtDateFrom.setText(campaign.getStartdate());
        txtDateTo.setText(campaign.getEnddate());
        txtDescription.setText(campaign.getBloodTypes());
        txtLocation.setText(campaign.getLocationName());


        // MAP VIEW
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.campInfo_mapID);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                 llat = Double.parseDouble(campaign.getLLat());
                 llong = Double.parseDouble(campaign.getLLong());

                LatLng location = new LatLng(llat, llong );
                mMap.addMarker(new MarkerOptions().position(location).title(campaign.getLocationName()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10.0f));
            }
        });


        // OPEN CAMPAIGN EDIT ACTIVITY + SEND CAMPAIGN OBJECT
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CampaignInfo.this, CampaignEdit.class);
                i.putExtra("campaign", campaign);
                startActivity(i);
            }
        });

    }


    @Override
    protected void onRestart() {
        super.onRestart();

        final RequestQueue queue= NetworkRequest.getInstance().getRequestQueue(CampaignInfo.this);
        String url="http://34.196.107.188:8081/MhealthWeb/webresources/callfordonation/"+campaign.getCFDId();

        final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                campaign =new Gson().fromJson(response,Campaign.class);
                txtCampName.setText(campaign.getName());
                txtDateFrom.setText(campaign.getStartdate());
                txtDateTo.setText(campaign.getEnddate());
                txtDescription.setText(campaign.getBloodTypes());
                txtLocation.setText(campaign.getLocationName());
//                llat = Double.parseDouble(campaign.getLLat());
//                llong = Double.parseDouble(campaign.getLLong());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CampaignInfo.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);



    }

}
