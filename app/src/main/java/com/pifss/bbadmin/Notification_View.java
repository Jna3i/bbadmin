package com.pifss.bbadmin;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Notification_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification__view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.Notification_Toolbar_View);

        toolbar.setTitle("Notification Details");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView tvTitle = (TextView) findViewById(R.id.notification_details_title);
        TextView tvDate = (TextView) findViewById(R.id.notification_details_date);
        TextView tvDescrip = (TextView) findViewById(R.id.notification_details_description);

        Notification notification = (Notification) getIntent().getSerializableExtra("notification");
        tvTitle.setText(notification.getTitle());
        tvDate.setText(notification.getDate());
        tvDescrip.setText(notification.getDescription());
    }
}
