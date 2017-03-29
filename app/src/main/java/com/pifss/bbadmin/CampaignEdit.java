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

public class CampaignEdit extends AppCompatActivity {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_edit);

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

        TextView txtCampName = (TextView) findViewById(R.id.campEdit_txtCampNameID);
        TextView txtDateFrom = (TextView) findViewById(R.id.campEdit_txtDateFromID);
        TextView txtDateTo = (TextView) findViewById(R.id.campEdit_txtDateToID);
        TextView txtDescription = (TextView) findViewById(R.id.campEdit_txtDescriptionID);
        TextView txtLocation = (TextView) findViewById(R.id.campEdit_txtLocationID);

        final Campaign campaign = (Campaign) getIntent().getSerializableExtra("campaign");

        txtCampName.setText(campaign.getName());
        txtDateFrom.setText(campaign.getStartdate());
        txtDateTo.setText(campaign.getEnddate());
        txtDescription.setText(campaign.getBloodTypes());
        txtLocation.setText(campaign.getLocationName());

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.campEdit_mapID);
//        mapFragment.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(GoogleMap googleMap) {
//                mMap = googleMap;
//
//                Double llat = Double.parseDouble(campaign.getLLat());
//                Double llong = Double.parseDouble(campaign.getLLong());
//
//                LatLng location = new LatLng(llat, llong );
//                mMap.addMarker(new MarkerOptions().position(location).title(campaign.getLocationName()));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
//            }
//        });



        //button save
        //location address geocoder

    }
}
