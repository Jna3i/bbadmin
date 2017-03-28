package com.pifss.bbadmin;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CampaignInfo extends AppCompatActivity {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_info);

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

        TextView txtCampName = (TextView) findViewById(R.id.campInfo_txtCampNameID);
        TextView txtDateFrom = (TextView) findViewById(R.id.campInfo_txtDateFromID);
        TextView txtDateTo = (TextView) findViewById(R.id.campInfo_txtDateToID);
        TextView txtDescription = (TextView) findViewById(R.id.campInfo_txtDescriptionID);
        TextView txtLocation = (TextView) findViewById(R.id.campInfo_txtLocationID);

        final Campaign campaign = (Campaign) getIntent().getSerializableExtra("campaign");

        txtCampName.setText(campaign.getName());
        txtDateFrom.setText(campaign.getStartdate());
        txtDateTo.setText(campaign.getEnddate());
        txtDescription.setText(campaign.getBloodTypes());
        txtLocation.setText(campaign.getLocationName());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.campInfo_mapID);
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


    }
}
