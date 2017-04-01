package com.pifss.bbadmin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

public class PeriodInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_info);

        String pStr=getIntent().getStringExtra("period");

        Period p=new Gson().fromJson(pStr,Period.class);

        System.out.println(p.getOnSiteDonors().size());

        TextView txtPeriodName = (TextView) findViewById(R.id.txtPeriodName) ;
        TextView txtTotalDonors = (TextView) findViewById(R.id.txtTotal);

        int priNumber = Integer.parseInt(p.getPeriod());
        txtPeriodName.setText("Period "+(priNumber+1));
        txtTotalDonors.setText(p.getTotal()+"/5");


        ListView lv= (ListView) findViewById(R.id.lstPeriodInfo);

        DonorsAdapter dd=new DonorsAdapter(this,p.getDonors(),p.getOnSiteDonors());

        lv.setAdapter(dd);



    }
}
