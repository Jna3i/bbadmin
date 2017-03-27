package com.pifss.bbadmin;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        TabLayout tab = (TabLayout) findViewById(R.id.language);

        tab.addTab(tab.newTab().setText("English"));
        tab.addTab(tab.newTab().setText("عربي"));

    }
}
