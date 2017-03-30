package com.pifss.bbadmin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Macbook on 3/29/17.
 */

public class NotificationAdapter extends BaseAdapter {

    ArrayList<Notification> model;
    Activity context;
    private final LayoutInflater layoutInflater;

    public NotificationAdapter(ArrayList<Notification> model, Activity context) {
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

        View view = layoutInflater.inflate(R.layout.notification_cell,null);
        TextView tvTitle = (TextView) view.findViewById(R.id.notification_details_title);
        TextView tvDate = (TextView) view.findViewById(R.id.notification_details_date);

        Notification notification = model.get(position);

        tvTitle.setText(notification.getTitle());
        tvDate.setText(notification.getDate());
        return view;
    }
}
