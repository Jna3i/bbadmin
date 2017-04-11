package com.pifss.bbadmin.BloodRequests;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pifss.bbadmin.R;

import java.util.ArrayList;

/**
 * Created by ahmed on 11/04/17.
 */

public class bloodRequestsListAdapter extends BaseAdapter {

    ArrayList<DoctorsHandler> model;
    Activity context;
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
        TextView tvDocId = (TextView) view.findViewById(R.id.blood_request_item_Doctor_ID);
        TextView tvtime = (TextView) view.findViewById(R.id.TimeStamp);
        TextView tvBloodType = (TextView) view.findViewById(R.id.Blood_Request_Item_BloodType);

        DoctorsHandler doctorsHandler = model.get(position);

        tvDocId.setText("" + doctorsHandler.getDrId());
        tvtime.setText(doctorsHandler.getTimestamp());
        tvBloodType.setText(doctorsHandler.getBloodType());

        return view;
    }
}
