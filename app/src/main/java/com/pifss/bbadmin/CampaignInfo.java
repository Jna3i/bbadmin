package com.pifss.bbadmin;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
//NEED TO SHOW LOCATION MARKER
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

        System.out.println(campaign.getBloodTypes());
        System.out.println(campaign.getLLat());
        System.out.println(campaign.getLLong());

        txtCampName.setText(campaign.getName());
        txtDateFrom.setText(campaign.getStartdate());
        txtDateTo.setText(campaign.getEnddate());
        txtDescription.setText(campaign.getBloodTypes());
        txtLocation.setText(campaign.getLocationName());

        MapView mapFragment = (MapView) findViewById(R.id.campInfo_mapID);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                Double llat = Double.parseDouble("23.123");
                Double llong = Double.parseDouble("22.123");

                LatLng location = new LatLng(llat, llong );
                mMap.addMarker(new MarkerOptions().position(location).title("test test"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            }
        });


        // button to the other activity
        Button btnEdit = (Button) findViewById(R.id.campInfo_btnEditID);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Intent i = new Intent(CampaignInfo.this, CampaignEdit.class);
                i.putExtra("campaign", campaign);
                startActivity(i);

            }
        });



    }
}
