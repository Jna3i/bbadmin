package com.pifss.bbadmin.BloodRequests;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pifss.bbadmin.Links;
import com.pifss.bbadmin.NetworkRequest;
import com.pifss.bbadmin.R;

import org.json.JSONException;
import org.json.JSONObject;

public class BloodRequestInfo extends AppCompatActivity {

    private JsonObjectRequest objectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_request_info);

        // TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.BloodRequestInfo_toolbarID);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle(getString(R.string.Info_BloodRequest));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView tvdrid = (TextView) findViewById(R.id.docID);
        TextView tvbloodtype = (TextView) findViewById(R.id.BloodType);
        final TextView tvstatus = (TextView) findViewById(R.id.requeststatus);
        TextView tvtime = (TextView) findViewById(R.id.TimeStamp);
        TextView tvQuantity = (TextView) findViewById(R.id.Quantity);
        TextView tvreason = (TextView) findViewById(R.id.Reason);
        TextView tvreqid = (TextView) findViewById(R.id.requestsid);

        Button acceptbtn = (Button) findViewById(R.id.AcceptBtn);
        Button declinebtn = (Button) findViewById(R.id.DeclineBtn);

        final DoctorsHandler doctorsHandler = (DoctorsHandler) getIntent().getSerializableExtra("doctorhandler");
        tvdrid.setText(""+ doctorsHandler.getDrId());
        tvbloodtype.setText(doctorsHandler.getBloodType());
        tvstatus.setText(""+doctorsHandler.getStatus());
        tvtime.setText(doctorsHandler.getTimestamp());
        tvQuantity.setText(""+doctorsHandler.getQuantity());
        tvreason.setText(doctorsHandler.getReason());
        int requestid = doctorsHandler.getRequestsId();
        tvreqid.setText(""+requestid);

        final RequestQueue requestQueue = NetworkRequest.getInstance().getRequestQueue(BloodRequestInfo.this);
        final String url = Links.BLOODREQUEST_SETSTATUS + requestid;
        System.out.println(url);
        acceptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject= new JSONObject();
                try {
                    jsonObject.put("status",1);

                    objectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject,new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    System.out.println(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                requestQueue.add(objectRequest);

                finish();

            }
        });




        declinebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject jsonObject= new JSONObject();
                try {
                    jsonObject.put("status",-1);

                    objectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject,new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println(error.getMessage());
                        }
                    });
                    System.out.println(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                requestQueue.add(objectRequest);

                finish();

            }
        });
    }
}
