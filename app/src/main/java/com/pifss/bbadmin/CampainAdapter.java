package com.pifss.bbadmin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ios on 3/28/17.
 */

public class CampainAdapter extends BaseAdapter {

    ArrayList<Campaign> model;
    Activity context;
    private final LayoutInflater inflater;

    public CampainAdapter(ArrayList<Campaign> model, Activity context) {
        this.model = model;
        this.context = context;
        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
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

        View v=inflater.inflate(R.layout.campaign_list_item,null);

        TextView campName= (TextView) v.findViewById(R.id.campListItem_txtCampNameID);
        TextView campDateFrom= (TextView) v.findViewById(R.id.campListItem_dateFromID);
        TextView campDateTo= (TextView) v.findViewById(R.id.campListItem_dateToID);

        Campaign campaign = model.get(position);

        campName.setText(campaign.getName());
        campDateFrom.setText(campaign.getStartdate());
        campDateTo.setText(campaign.getEnddate());


        return v;
    }
}
