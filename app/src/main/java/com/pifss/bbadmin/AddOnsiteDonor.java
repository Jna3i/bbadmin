package com.pifss.bbadmin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class AddOnsiteDonor extends AppCompatActivity {

    private RadioGroup radioGenderGroup;
    private RadioButton radioGenderButton;

    public void addListenerOnButton() {
        RadioGroup RadioGenderGroup = (RadioGroup) findViewById(R.id.radioGender);
        int selectedId = radioGenderGroup.getCheckedRadioButtonId();

    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_onsite_donor);

            Toolbar toolbar = (Toolbar) findViewById(R.id.onsiteAdd_toolbarID);
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setTitle(getString(R.string.AddDonor_title));
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            final ImageView img_aPlus = (ImageView) findViewById(R.id.a_plus);
            final ImageView img_aMinus = (ImageView) findViewById(R.id.a_minus);
            final ImageView img_bPlus = (ImageView) findViewById(R.id.b_plus);
            final ImageView img_bMinus = (ImageView) findViewById(R.id.b_minus);
            final ImageView img_oPlus = (ImageView) findViewById(R.id.o_plus);
            final ImageView img_oMinus = (ImageView) findViewById(R.id.o_minus);
            final ImageView img_abPlus = (ImageView) findViewById(R.id.ab_plus);
            final ImageView img_abMinus = (ImageView) findViewById(R.id.ab_minus);

         final TextView txtname = (TextView) findViewById(R.id.txtName);
         final TextView txtcivilId = (TextView) findViewById(R.id.txtCivilID);
         final TextView txtphone = (TextView) findViewById(R.id.txtPhone);
         final TextView txtEmail = (TextView) findViewById(R.id.txtEmail);

            final Button btnShowCal = (Button) findViewById(R.id.btnShowCal);
            btnShowCal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog d=new DatePickerDialog(AddOnsiteDonor.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            btnShowCal.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                        }
                    },2017,1,1);

                    d.show();
                }
            });

            final RadioButton radMale = (RadioButton) findViewById(R.id.radMale);
            final RadioButton radFemale = (RadioButton) findViewById(R.id.radFemale);
            radFemale.setChecked(false);
            radMale.setChecked(false);


            img_aPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    img_aPlus.setBackgroundColor(Color.GRAY);
                    img_aMinus.setBackgroundColor(Color.WHITE);
                    img_abPlus.setBackgroundColor(Color.WHITE);
                    img_abMinus.setBackgroundColor(Color.WHITE);
                    img_bPlus.setBackgroundColor(Color.WHITE);
                    img_bMinus.setBackgroundColor(Color.WHITE);
                    img_oPlus.setBackgroundColor(Color.WHITE);
                    img_oMinus.setBackgroundColor(Color.WHITE);

                    img_aPlus.setImageAlpha(255);
                    img_aMinus.setImageAlpha(127);
                    img_abPlus.setImageAlpha(127);
                    img_abMinus.setImageAlpha(127);
                    img_bPlus.setImageAlpha(127);
                    img_bMinus.setImageAlpha(127);
                    img_oPlus.setImageAlpha(127);
                    img_oMinus.setImageAlpha(127);

                }
            });

            img_aMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    img_aPlus.setBackgroundColor(Color.WHITE);
                    img_aMinus.setBackgroundColor(Color.GRAY);
                     img_abPlus.setBackgroundColor(Color.WHITE);
                    img_abMinus.setBackgroundColor(Color.WHITE);
                    img_bPlus.setBackgroundColor(Color.WHITE);
                    img_bMinus.setBackgroundColor(Color.WHITE);
                    img_oPlus.setBackgroundColor(Color.WHITE);
                    img_oMinus.setBackgroundColor(Color.WHITE);

                    img_aPlus.setImageAlpha(127);
                    img_aMinus.setImageAlpha(255);
                    img_abPlus.setImageAlpha(127);
                    img_abMinus.setImageAlpha(127);
                    img_bPlus.setImageAlpha(127);
                    img_bMinus.setImageAlpha(127);
                    img_oPlus.setImageAlpha(127);
                    img_oMinus.setImageAlpha(127);

                }
            });

            img_abPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    img_aPlus.setBackgroundColor(Color.WHITE);
                    img_aMinus.setBackgroundColor(Color.WHITE);
                    img_abPlus.setBackgroundColor(Color.GRAY);
                    img_abMinus.setBackgroundColor(Color.WHITE);
                    img_bPlus.setBackgroundColor(Color.WHITE);
                    img_bMinus.setBackgroundColor(Color.WHITE);
                    img_oPlus.setBackgroundColor(Color.WHITE);
                    img_oMinus.setBackgroundColor(Color.WHITE);

                    img_aPlus.setImageAlpha(127);
                    img_aMinus.setImageAlpha(127);
                    img_abPlus.setImageAlpha(255);
                    img_abMinus.setImageAlpha(127);
                    img_bPlus.setImageAlpha(127);
                    img_bMinus.setImageAlpha(127);
                    img_oPlus.setImageAlpha(127);
                    img_oMinus.setImageAlpha(127);

                }
            });


            img_abMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    img_aPlus.setBackgroundColor(Color.WHITE);
                    img_aMinus.setBackgroundColor(Color.WHITE);
                    img_abPlus.setBackgroundColor(Color.WHITE);
                    img_abMinus.setBackgroundColor(Color.GRAY);
                     img_bPlus.setBackgroundColor(Color.WHITE);
                    img_bMinus.setBackgroundColor(Color.WHITE);
                    img_oPlus.setBackgroundColor(Color.WHITE);
                    img_oMinus.setBackgroundColor(Color.WHITE);

                    img_aPlus.setImageAlpha(127);
                    img_aMinus.setImageAlpha(127);
                    img_abPlus.setImageAlpha(127);
                    img_abMinus.setImageAlpha(255);
                    img_bPlus.setImageAlpha(127);
                    img_bMinus.setImageAlpha(127);
                    img_oPlus.setImageAlpha(127);
                    img_oMinus.setImageAlpha(127);

                }
            });

            img_bPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    img_aPlus.setBackgroundColor(Color.WHITE);
                    img_aMinus.setBackgroundColor(Color.WHITE);
                    img_abPlus.setBackgroundColor(Color.WHITE);
                    img_abMinus.setBackgroundColor(Color.WHITE);
                    img_bPlus.setBackgroundColor(Color.GRAY);
                    img_bMinus.setBackgroundColor(Color.WHITE);
                    img_oPlus.setBackgroundColor(Color.WHITE);
                    img_oMinus.setBackgroundColor(Color.WHITE);

                    img_aPlus.setImageAlpha(127);
                    img_aMinus.setImageAlpha(127);
                    img_abPlus.setImageAlpha(127);
                    img_abMinus.setImageAlpha(127);
                    img_bPlus.setImageAlpha(255);
                    img_bMinus.setImageAlpha(127);
                    img_oPlus.setImageAlpha(127);
                    img_oMinus.setImageAlpha(127);

                }
            });

            img_bMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    img_aPlus.setBackgroundColor(Color.WHITE);
                    img_aMinus.setBackgroundColor(Color.WHITE);
                    img_abPlus.setBackgroundColor(Color.WHITE);
                    img_abMinus.setBackgroundColor(Color.WHITE);
                    img_bPlus.setBackgroundColor(Color.WHITE);
                    img_bMinus.setBackgroundColor(Color.GRAY);
                    img_oPlus.setBackgroundColor(Color.WHITE);
                    img_oMinus.setBackgroundColor(Color.WHITE);

                    img_aPlus.setImageAlpha(127);
                    img_aMinus.setImageAlpha(127);
                    img_abPlus.setImageAlpha(127);
                    img_abMinus.setImageAlpha(127);
                    img_bPlus.setImageAlpha(127);
                    img_bMinus.setImageAlpha(255);
                    img_oPlus.setImageAlpha(127);
                    img_oMinus.setImageAlpha(127);

                }
            });

            img_oPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    img_aPlus.setBackgroundColor(Color.WHITE);
                    img_aMinus.setBackgroundColor(Color.WHITE);
                    img_abPlus.setBackgroundColor(Color.WHITE);
                    img_abMinus.setBackgroundColor(Color.WHITE);
                    img_bPlus.setBackgroundColor(Color.WHITE);
                    img_bMinus.setBackgroundColor(Color.WHITE);
                    img_oPlus.setBackgroundColor(Color.GRAY);
                    img_oMinus.setBackgroundColor(Color.WHITE);

                    img_aPlus.setImageAlpha(127);
                    img_aMinus.setImageAlpha(127);
                    img_abPlus.setImageAlpha(127);
                    img_abMinus.setImageAlpha(127);
                    img_bPlus.setImageAlpha(127);
                    img_bMinus.setImageAlpha(127);
                    img_oPlus.setImageAlpha(255);
                    img_oMinus.setImageAlpha(127);

                }
            });

            img_oMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    img_aPlus.setBackgroundColor(Color.WHITE);
                    img_aMinus.setBackgroundColor(Color.WHITE);
                    img_abPlus.setBackgroundColor(Color.WHITE);
                    img_abMinus.setBackgroundColor(Color.WHITE);
                    img_bPlus.setBackgroundColor(Color.WHITE);
                    img_bMinus.setBackgroundColor(Color.WHITE);
                    img_oPlus.setBackgroundColor(Color.WHITE);
                    img_oMinus.setBackgroundColor(Color.GRAY);

                    img_aPlus.setImageAlpha(127);
                    img_aMinus.setImageAlpha(127);
                    img_abPlus.setImageAlpha(127);
                    img_abMinus.setImageAlpha(127);
                    img_bPlus.setImageAlpha(127);
                    img_bMinus.setImageAlpha(127);
                    img_oPlus.setImageAlpha(127);
                    img_oMinus.setImageAlpha(255);
                 }
            });



        Button btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://34.196.107.188:8080/mHealthWS/ws/onsitedonor";
                final RequestQueue queue= NetworkRequest.getInstance().getRequestQueue(AddOnsiteDonor.this);

                String name = txtname.getText().toString().trim();
                String civilID = txtcivilId.getText().toString().trim();
                String phone = txtphone.getText().toString().trim();
                String email = txtEmail.getText().toString().trim();
              //  String gender = txt.getText().toString().trim();
//                String bloodtype = txt.getText().toString().trim();

                if (name.isEmpty() || name.length() == 0 || name.equals("") || name == null) {
                    Toast.makeText(AddOnsiteDonor.this, getString(R.string.Enter)+" "+getString(R.string.one_cell_period_info_fnameLname), Toast.LENGTH_LONG).show();

                    return;
                }
                if (civilID.isEmpty() || civilID.length() == 0 || civilID.equals("") || civilID == null) {
                    Toast.makeText(AddOnsiteDonor.this, getString(R.string.Enter)+" "+getString((R.string.view_ID)), Toast.LENGTH_LONG).show();

                    return;
                }
                if (phone.isEmpty() || phone.length() == 0 || phone.equals("") || phone == null) {
                    Toast.makeText(AddOnsiteDonor.this, getString(R.string.Enter)+" "+ getString((R.string.view_Phone)), Toast.LENGTH_LONG).show();

                    return;
                }
                if (email.isEmpty() || email.length() == 0 || email.equals("") || email == null) {
                    Toast.makeText(AddOnsiteDonor.this,getString(R.string.Enter)+" "+ getString((R.string.view_Email)), Toast.LENGTH_LONG).show();

                    return;
                }
//                if (gender.isEmpty() || gender.length() == 0 || gender.equals("") || gender == null) {
//                    return;
//                }
//                if (bloodtype.isEmpty() || bloodtype.length() == 0 || bloodtype.equals("") || bloodtype == null) {
//                    return;
//                }
                final JSONObject onsiteJson = new JSONObject();
                try {
                    //onsiteJson.put("bloodType", "B-");
                    onsiteJson.put("birthDate", btnShowCal.getText().toString());
                    onsiteJson.put("name", txtname.getText().toString());
                    onsiteJson.put("civilId", txtcivilId.getText().toString());
                    onsiteJson.put("phone", txtphone.getText().toString());
                    onsiteJson.put("email", txtEmail.getText().toString());

                    if (radMale.isChecked()){
                        onsiteJson.put("gender", "M");
                        radFemale.setChecked(false);
                    }
                    else if (radFemale.isChecked()){
                        onsiteJson.put("gender", "F");
                        radMale.setChecked(false);
                    }
                    else{
                        onsiteJson.put("gender", "M");
                    }




                    if (img_aPlus.isClickable()){
                        onsiteJson.put("bloodType", "A+");
                    }
                    else if (img_aMinus.isClickable()){
                        onsiteJson.put("bloodType", "A-");
                    }
                    else if (img_bPlus.isClickable()){
                        onsiteJson.put("bloodType", "B+");
                    }
                    else if (img_bMinus.isClickable()){
                        onsiteJson.put("bloodType", "B-");
                    }
                    else if (img_oPlus.isClickable()){
                        onsiteJson.put("bloodType", "O+");
                    }
                    else if (img_oMinus.isClickable()){
                        onsiteJson.put("bloodType", "O-");
                    }
                    else if (img_abPlus.isClickable()){
                        onsiteJson.put("bloodType", "AB+");
                    }
                    else if (img_abMinus.isClickable()){
                        onsiteJson.put("bloodType", "AB-");
                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, url, onsiteJson, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(AddOnsiteDonor.this, getString((R.string.Toast_AddDonor_Successfully)), Toast.LENGTH_LONG).show();

                        OnSiteDonor onsiteDonorInfo = new OnSiteDonor();
                        onsiteDonorInfo = new Gson().fromJson(String.valueOf(response), OnSiteDonor.class);

                        Intent i = new Intent(AddOnsiteDonor.this, AddOnsiteDonor2.class);
                        i.putExtra("id", onsiteDonorInfo.getId());
                       // Toast.makeText(AddOnsiteDonor.this,getString((R.string.Toast_Add_Successfully_ID)) +onsiteDonorInfo.getId(), Toast.LENGTH_LONG).show();
                        startActivity(i);


                        System.out.print(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddOnsiteDonor.this, "NO, but added", Toast.LENGTH_LONG).show();




                    }
                });

                queue.add(request);


//                String urlFS = "http://34.196.107.188:8080/mHealthWS/ws/schedule/freeslots/1";
//                final RequestQueue queueFS= NetworkRequest.getInstance().getRequestQueue(AddOnsiteDonor.this);
              //  JsonObjectRequest


    }
});

    }
}

