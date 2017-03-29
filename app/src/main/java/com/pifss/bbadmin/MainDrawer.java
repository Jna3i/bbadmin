package com.pifss.bbadmin;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.entypo_typeface_library.Entypo;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;

public class MainDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //new Jna3i
        //Drawable Schedule = new IconicsDrawable(this).icon(FontAwesome.Icon.faw_calendar);
        //navigationView.getMenu().getItem(0).setIcon(Schedule);
        setUpNavMenuIcons(navigationView.getMenu());

        setUpFloatingBoomButtton();

        navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int0 = new Intent(MainDrawer.this,ViewProfile.class);
                startActivity(int0);
            }
        });
    }

    public void setUpFloatingBoomButtton(){
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        bmb.setButtonEnum(ButtonEnum.SimpleCircle);
        bmb.setNormalColor(Color.rgb(220,0,0));
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            SimpleCircleButton.Builder builder = new SimpleCircleButton.Builder();
            //.normalImageRes(R.drawable.ic_menu_send);
            /*
            0 : Send Notification
            1 : add onsite Donor
            2 : add Campaign
             */
            switch (i){
                case 0: builder.normalImageDrawable(new IconicsDrawable(this).icon(Entypo.Icon.ent_sound))
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                //send notif
                                Toast.makeText(MainDrawer.this, "Send Notification", Toast.LENGTH_SHORT).show();
                                Intent int0 = new Intent(MainDrawer.this,Notification_Send.class);
                                startActivity(int0);
                            }
                        });
                    break;

                case 1: builder.normalImageDrawable(new IconicsDrawable(this).icon(Entypo.Icon.ent_drop))
                        .normalColor(Color.rgb(153,0,0))
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                //onsite Donor
                                Toast.makeText(MainDrawer.this, "Add onSite Donor", Toast.LENGTH_SHORT).show();
                            }
                        });
                    break;

                case 2: builder.normalImageDrawable(new IconicsDrawable(this).icon(Entypo.Icon.ent_blackboard))
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                //add campaign
                                Intent int0 = new Intent(MainDrawer.this,CampaignAdd.class);
                                startActivity(int0);
                            }
                        });
                    break;

                default: builder.normalImageRes(R.drawable.ic_menu_send);
                    break;
            }
            bmb.addBuilder(builder);
        }
    }

    //change Material Drawer Icons
    public void setUpNavMenuIcons(Menu menu){
        Drawable Schedule = new IconicsDrawable(this).icon(FontAwesome.Icon.faw_calendar);
        menu.getItem(0).setIcon(Schedule);

        Drawable Campaign = new IconicsDrawable(this).icon(FontAwesome.Icon.faw_comments);
        menu.getItem(1).setIcon(Campaign);

        Drawable notiHistory = new IconicsDrawable(this).icon(FontAwesome.Icon.faw_bell);
        menu.getItem(2).setIcon(notiHistory);

        Drawable addOnSite = new IconicsDrawable(this).icon(Entypo.Icon.ent_drop);
        menu.getItem(3).setIcon(addOnSite);

        Drawable addCampaign = new IconicsDrawable(this).icon(Entypo.Icon.ent_blackboard);
        menu.getItem(4).setIcon(addCampaign);

        Drawable sendNoti = new IconicsDrawable(this).icon(Entypo.Icon.ent_sound);
        menu.getItem(5).setIcon(sendNoti);

        Drawable Settings = new IconicsDrawable(this).icon(FontAwesome.Icon.faw_wrench);
        menu.getItem(6).setIcon(Settings);

        Drawable logout = new IconicsDrawable(this).icon(FontAwesome.Icon.faw_sign_out);
        menu.getItem(7).setIcon(logout);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_schedule) {
            // Handle the camera action
        } else if (id == R.id.nav_campaign) {
            Intent int0 = new Intent(MainDrawer.this,CampaignList.class);
            startActivity(int0);
        } else if (id == R.id.nav_notificationHistory) {
            Intent int0 = new Intent(MainDrawer.this,Notification_History_List.class);
            startActivity(int0);
        } else if (id == R.id.nav_addOnSiteDonor) {

        } else if (id == R.id.nav_addCampaign) {
            Intent int0 = new Intent(MainDrawer.this,CampaignAdd.class);
            startActivity(int0);
        } else if (id == R.id.nav_sendNotification) {
            Intent int0 = new Intent(MainDrawer.this,Notification_Send.class);
            startActivity(int0);
        } else if (id == R.id.nav_settings) {
            Intent int0 = new Intent(MainDrawer.this,AppSettings.class);
            startActivity(int0);

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
