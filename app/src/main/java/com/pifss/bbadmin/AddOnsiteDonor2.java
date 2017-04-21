package com.pifss.bbadmin;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddOnsiteDonor2 extends AppCompatActivity {

    int dayOftheMonth;
    ArrayList AllDates = new ArrayList();
    ArrayList AllItems = new ArrayList();




    public void loadSpinners(){


        //dates
        final Spinner SpinnerDates = (Spinner) findViewById(R.id.spinnerDates);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, AllDates);
        SpinnerDates.setAdapter(adapter2);

        //periods
        final ArrayList<String> spinnerArr = getFreePeriods((Slots)AllItems.get(0));


        final Spinner SpinnerPeriods = (Spinner) findViewById(R.id.spinnerPeriods);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, spinnerArr);
        SpinnerPeriods.setAdapter(adapter);

        SpinnerDates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Slots selectedSlot = (Slots) AllItems.get(position);
                ArrayList<String> newspinnerArr = getFreePeriods(selectedSlot);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, newspinnerArr);
                SpinnerPeriods.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        TextView txtDate = (TextView) findViewById(R.id.txtDate);
//        txtDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_onsite_donor2);
        TextView txtDate = (TextView) findViewById(R.id.txtDate);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        SharedPreferences pref1 = getSharedPreferences("bbadmin_profile", MODE_PRIVATE);
        String S1 = pref1.getString("profile","error");
        bbadmin profile;
        profile = new Gson().fromJson(S1,bbadmin.class);

        String url="http://34.196.107.188:8081/MhealthWeb/webresources/schedule/freeslots/"+profile.getBranchId();

        final RequestQueue queue= NetworkRequest.getInstance().getRequestQueue(AddOnsiteDonor2.this);

        final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                final ArrayList<Slots> freeslots = new Gson().fromJson(response, new TypeToken<ArrayList<Slots>>() {
                }.getType());

                for (Slots s: freeslots) {
                   String date = s.getDay();
//                    Toast.makeText(AddOnsiteDonor2.this, ""+date, Toast.LENGTH_SHORT).show();
                    AllDates.add(date);
                }
                    System.out.println(AllDates);
                AllItems = freeslots;
                loadSpinners();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddOnsiteDonor2.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);



        Toolbar toolbar = (Toolbar) findViewById(R.id.onsiteAdd2_toolbarID);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle("Add Donor");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final Button btnAdd = (Button) findViewById(R.id.btnAdd);

        final int id = getIntent().getIntExtra("id",0);



        final Spinner SpinnerDates = (Spinner) findViewById(R.id.spinnerDates);
        final Spinner SpinnerPeriods = (Spinner) findViewById(R.id.spinnerPeriods);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://34.196.107.188:8081/MhealthWeb/webresources/schedule";
                final RequestQueue queue = NetworkRequest.getInstance().getRequestQueue(AddOnsiteDonor2.this);

                final JSONObject onsiteSched = new JSONObject();
                try {
                    onsiteSched.put("isActive", 1);
                    onsiteSched.put("branchId", 1);
                    onsiteSched.put("isPast", 0);
                    onsiteSched.put("isRegisteredUser", 0);
                    onsiteSched.put("regUserId", 0);
                    onsiteSched.put("siteUserId", id);
                    onsiteSched.put("period", SpinnerPeriods.getSelectedItemPosition());
                    onsiteSched.put("day", SpinnerDates.getSelectedItem().toString()+"T00:00:00Z");

                   // Toast.makeText(AddOnsiteDonor2.this, ""+onsiteSched, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, onsiteSched, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Toast.makeText(AddOnsiteDonor2.this, "Donor Added Successfuly!", Toast.LENGTH_LONG).show();
                        //Toast.makeText(AddOnsiteDonor2.this, "Donor  Successfuly! id = " +onsiteDonorInfo.getId(), Toast.LENGTH_LONG).show();


                       // System.out.print(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(AddOnsiteDonor2.this, "NO, but added", Toast.LENGTH_LONG).show();


                    }


                });

                queue.add(request);
            }


        });

    }


    public ArrayList<String> getFreePeriods(Slots slot){

        String slotTitle = "";
        ArrayList slots = slot.getSlots();
        ArrayList<String> availableSlotTitles = new ArrayList<String>();
        for (int i =0; i< slots.size() ; i++){

            if ((Double)slots.get(i) != 0){
                availableSlotTitles.add("Period "+(i+1));
            }

        }
        return availableSlotTitles;

    }
}
