package com.pifss.bbadmin.BloodRequests;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import com.pifss.bbadmin.AddOnsiteDonor;
import com.pifss.bbadmin.AddOnsiteDonor2;
import com.pifss.bbadmin.Links;
import com.pifss.bbadmin.NetworkRequest;
import com.pifss.bbadmin.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ahmed on 11/04/17.
 */

public class bloodRequestsListAdapter extends BaseAdapter {

    ArrayList<DoctorsHandler> model;
    Activity context;
    String name;
    private final LayoutInflater layoutInflater;

    public bloodRequestsListAdapter(ArrayList<DoctorsHandler> model, Activity context) {
        this.model = model;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return model.size();
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

        View view = layoutInflater.inflate(R.layout.blood_request_item,null);
        final TextView tvDocId = (TextView) view.findViewById(R.id.blood_request_item_Doctor_ID);
        TextView tvBloodType = (TextView) view.findViewById(R.id.Blood_Request_Item_BloodType);

        DoctorsHandler doctorsHandler = model.get(position);

        String url = Links.DOCTOR + "/" + doctorsHandler.getDrId();

        final RequestQueue queue= NetworkRequest.getInstance().getRequestQueue(context);

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    name = response.getString("firstName") + " " + response.getString("middleName") + " " + response.getString("lastName");
                    tvDocId.setText("Dr: "+ name);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(stringRequest);

        tvBloodType.setText(doctorsHandler.getBloodType());

        return view;
    }


}
