package com.pifss.bbadmin;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by PIFSS on 3/28/2017.
 */

public class PeriodAdapter extends BaseAdapter {

    private final LayoutInflater inflater;

    Activity context;
    String ddate;
    ArrayList<String> arrayDates;

    public PeriodAdapter(Activity context) {

        this.context = context;
        arrayDates = new ArrayList<>();
        Calendar cal=Calendar.getInstance();
        String DATE_FORMAT = "yyyy-MM-dd";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);


        final String dateFrom=sdf.format(cal.getTime());

        //arrayDates.add(ddate);
        cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH));
        arrayDates.add(sdf.format(cal.getTime()));
        for (int i = 0; i <= 20 ; i++) {

            cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+1);
            arrayDates.add(sdf.format(cal.getTime()));

        }



            inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }






    @Override
    public int getCount() {
        return arrayDates.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        final String date = arrayDates.get(position);

        String url="http://34.196.107.188:8081/MhealthWeb/webresources/schedule/branch/1/day/"+date;

        final RequestQueue queue= NetworkRequest.getInstance().getRequestQueue(this.context);

        final View v;

        v = inflater.inflate(R.layout.sched_five_periods, null);


        final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                final ArrayList<Period> day = new Gson().fromJson(response, new TypeToken<ArrayList<Period>>() {
                   }.getType());


                TextView txtDate = (TextView) v.findViewById(R.id.txtDate);
                txtDate.setText(date);


            TextView txtPeriod1 = (TextView) v.findViewById(R.id.txtPeriod1);
            TextView txtTotal1 = (TextView) v.findViewById(R.id.txtTotal1);

            TextView txtPeriod2 = (TextView) v.findViewById(R.id.txtPeriod2);
            TextView txtTotal2 = (TextView) v.findViewById(R.id.txtTotal2);

            TextView txtPeriod3 = (TextView) v.findViewById(R.id.txtPeriod3);
            TextView txtTotal3 = (TextView) v.findViewById(R.id.txtTotal3);

            TextView txtPeriod4 = (TextView) v.findViewById(R.id.txtPeriod4);
            TextView txtTotal4 = (TextView) v.findViewById(R.id.txtTotal4);

            TextView txtPeriod5 = (TextView) v.findViewById(R.id.txtPeriod5);
            TextView txtTotal5 = (TextView) v.findViewById(R.id.txtTotal5);

//            TextView txtDate = (TextView) v.findViewById(R.id.txtDate);
//            txtDate.setText(date);

            final Period p0=day.get(0);
            txtTotal1.setText(p0.getTotal().toString()+"/5");

            Period p1=day.get(1);
            txtTotal2.setText(p1.getTotal().toString()+"/5");

            Period p2=day.get(2);
            txtTotal3.setText(p2.getTotal().toString()+"/5");

            Period p3=day.get(3);
            txtTotal4.setText(p3.getTotal().toString()+"/5");

            Period p4=day.get(4);
            txtTotal5.setText(p4.getTotal().toString()+"/5");


                TextView txtTotalAll = (TextView) v.findViewById(R.id.txtTotalRegistered);
                Integer TotalDonors =  p0.getTotal()+p1.getTotal()+p2.getTotal()+p3.getTotal()+p4.getTotal();
                txtTotalAll.setText(TotalDonors.toString());



                LinearLayout group1 = (LinearLayout) v.findViewById(R.id.group1);
                LinearLayout group2 = (LinearLayout) v.findViewById(R.id.group2);
                LinearLayout group3 = (LinearLayout) v.findViewById(R.id.group3);
                LinearLayout group4 = (LinearLayout) v.findViewById(R.id.group4);
                LinearLayout group5 = (LinearLayout) v.findViewById(R.id .group5);


                group1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(), PeriodInfo.class);
                        String s=new Gson().toJson(day.get(0));
                        System.out.println(s);
                        i.putExtra("period",s);
                        v.getContext().startActivity(i);
                    }
                });

                group2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(), PeriodInfo.class);
                        String s=new Gson().toJson(day.get(1));
                        System.out.println(s);
                        i.putExtra("period",s);
                        v.getContext().startActivity(i);
                    }
                });

                group3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(), PeriodInfo.class);
                        String s=new Gson().toJson(day.get(2));
                        System.out.println(s);
                        i.putExtra("period",s);
                        v.getContext().startActivity(i);
                    }
                });

                group4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(), PeriodInfo.class);
                        String s=new Gson().toJson(day.get(3));
                        System.out.println(s);
                        i.putExtra("period",s);
                        v.getContext().startActivity(i);
                    }
                });

                group5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(), PeriodInfo.class);
                        String s=new Gson().toJson(day.get(4));
                        System.out.println(s);
                        i.putExtra("period",s);
                        v.getContext().startActivity(i);
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("hello JError"+error.toString());
            }
        });
        queue.add(stringRequest);
        return v;
    }
}
