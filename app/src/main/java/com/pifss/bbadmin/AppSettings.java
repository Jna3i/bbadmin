package com.pifss.bbadmin;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class AppSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final TabLayout tab = (TabLayout) findViewById(R.id.language);

        tab.addTab(tab.newTab().setText("English"));
        tab.addTab(tab.newTab().setText("عربي"));

        Toolbar toolbar = (Toolbar) findViewById(R.id.Settingstb);



        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                DisplayMetrics dm = res.getDisplayMetrics();
                android.content.res.Configuration conf = res.getConfiguration();
                if(tab.getSelectedTabPosition() == 1) {
                    conf.setLocale(new Locale("ar"));
                    res.updateConfiguration(conf, dm);
                    Intent refresh = new Intent(AppSettings.this, MainDrawer.class);
                    startActivity(refresh);
                    finish();
                    //startActivity(i);
                }
                else{
                    conf.setLocale(new Locale("en"));

                    res.updateConfiguration(conf, dm);
                    Intent refresh = new Intent(AppSettings.this, MainDrawer.class);
                    startActivity(refresh);
                    finish();
                }

            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
