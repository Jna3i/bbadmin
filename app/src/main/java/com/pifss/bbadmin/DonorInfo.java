package com.pifss.bbadmin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DonorInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_info);

        String Name = getIntent().getStringExtra("name");
        String CivilID = getIntent().getStringExtra("civilID");
        String Gender = getIntent().getStringExtra("gender");
        String BloodType = getIntent().getStringExtra("bloodType");
        String phone = getIntent().getStringExtra("phone");
        String Email = getIntent().getStringExtra("email");


        TextView txtName = (TextView) findViewById(R.id.txtName);
        TextView txtCivilID = (TextView) findViewById(R.id.txtCivilID);
        TextView txtBloodType = (TextView) findViewById(R.id.txtBloodType);
        TextView txtPhone = (TextView) findViewById(R.id.txtPhone);
        TextView txtGender = (TextView) findViewById(R.id.txtGender);
        TextView txtEmail = (TextView) findViewById(R.id.txtEmail);



        txtName.setText(Name);
        txtCivilID.setText(CivilID);
        //txtGender.setText(Gender);
        txtBloodType.setText(BloodType);
        txtPhone.setText(phone);
        txtEmail.setText(Email);


        if (Gender == "m" || Gender == "M"){
            txtGender.setText("Male");
        }
        else if (Gender == "f" || Gender == "F"){
            txtGender.setText("Female");
        }

    }
}
