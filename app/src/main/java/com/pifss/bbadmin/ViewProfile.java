package com.pifss.bbadmin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class ViewProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);


        Toolbar toolbar = (Toolbar) findViewById(R.id.viewProfile_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle(getString(R.string.activity_Profile));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        Button editB = (Button) findViewById(R.id.button2);
        editB.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewProfile.this,EditProfile.class);
                startActivity(i);
            }
        });


        setProfileData();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setProfileData();
        
    }
    
    //show user proifle data
    public void setProfileData(){

        //Get user data
        SharedPreferences pref1 = getSharedPreferences("bbadmin_profile", MODE_PRIVATE);
        String S1 = pref1.getString("profile","error");
        bbadmin profile;
        profile = new Gson().fromJson(S1,bbadmin.class);

        TextView name   = (TextView) findViewById(R.id.textViewName);
        TextView gender = (TextView) findViewById(R.id.Gender2);
        TextView bdate  = (TextView) findViewById(R.id.textViewDate);
        TextView email  = (TextView) findViewById(R.id.textViewEmail);
        TextView phone  = (TextView) findViewById(R.id.textViewPhone);
        TextView branch = (TextView) findViewById(R.id.textViewBranch);
        TextView civil  = (TextView) findViewById(R.id.textViewID);

        //Name
        name.setText(profile.getFirstName() +" "+profile.getLastName());
        //Gender
        if(profile.getGender().equals("F")){
            gender.setText("Female");
        }
        else{
            gender.setText("Male");
        }

        //bdate
        bdate.setText(profile.getBirthDate());

        //email
        email.setText(profile.getEmail());

        //phone
        phone.setText(profile.getPhone());

        //branch
        if(profile.getBranchId()== 1){
            branch.setText("Main Center");
        }
        else{
            branch.setText("Hawaly Branch");
        }

        //civil
        civil.setText(profile.getCivilId());

    }

}
