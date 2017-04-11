package com.pifss.bbadmin;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddOnsiteDonor2 extends AppCompatActivity {

    int dayOftheMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_onsite_donor2);

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


        Button btnAdd = (Button) findViewById(R.id.btnAdd);

        final int id = getIntent().getIntExtra("id",0);

        System.out.print(id);

        final TextView txtDate = (TextView) findViewById(R.id.txtDate);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog d = new DatePickerDialog(AddOnsiteDonor2.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtDate.setText(year + "-" + (month+1) + "-" + dayOfMonth);
                        String month2 = ""+month;
                        String dayOfMonth2 = ""+dayOfMonth;
                        if (month<10){
                            month2 = "0"+(month+1);
                        }else {
                            month2 = ""+(month+1);
                        }
                        if (dayOfMonth<10){
                            dayOfMonth2 = "0"+dayOfMonth;
                        }else {
                            dayOfMonth2 = ""+dayOfMonth;
                        }

                        txtDate.setText(year + "-" + month2 + "-" + dayOfMonth2);

                    }
                }, 2017, 1, 1);
                d.show();
            }
        });

        ArrayList<String> spinnerArr = new ArrayList<>();
        spinnerArr.add("Period 1");
        spinnerArr.add("Period 2");
        spinnerArr.add("Period 3");
        spinnerArr.add("Period 4");
        spinnerArr.add("Period 5");

        final Spinner SpinnerPeriods = (Spinner) findViewById(R.id.spinnerPeriods);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, spinnerArr);
        SpinnerPeriods.setAdapter(adapter);

       // final String selectedPeriod = (String) SpinnerPeriods.getSelectedItem();

        //

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
                    onsiteSched.put("day", txtDate.getText().toString()+"T00:00:00Z");
                    onsiteSched.put("period", SpinnerPeriods.getSelectedItemPosition());

                    Toast.makeText(AddOnsiteDonor2.this, ""+onsiteSched, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, onsiteSched, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Toast.makeText(AddOnsiteDonor2.this, "Donor Added Successfuly!", Toast.LENGTH_LONG).show();
                        //Toast.makeText(AddOnsiteDonor2.this, "Donor  Successfuly! id = " +onsiteDonorInfo.getId(), Toast.LENGTH_LONG).show();


                        System.out.print(response);
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
}
