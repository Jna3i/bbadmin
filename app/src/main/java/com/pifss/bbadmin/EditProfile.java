package com.pifss.bbadmin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EditProfile extends AppCompatActivity {

    bbadmin profile= new bbadmin();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        SharedPreferences pref1 = getSharedPreferences("bbadmin_profile", MODE_PRIVATE);
        String S1 = pref1.getString("profile","error");

        profile= new Gson().fromJson(S1,bbadmin.class);
        final int id = profile.getBbadminId();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle("Edit Profile");
        toolbar.setTitleTextColor(Color.WHITE);

        Button save = (Button) findViewById(R.id.button3);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText phone = (EditText) findViewById(R.id.phoneText);
        final EditText civil = (EditText) findViewById(R.id.civilText);
        final EditText email = (EditText) findViewById(R.id.emailText);
        //Gender spinner
        ArrayList<String> spinnerAd = new ArrayList<>();
        spinnerAd.add("Male");
        spinnerAd.add("Female");

        final Spinner mySpinnerGender = (Spinner) findViewById(R.id.spinner3);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,spinnerAd);
        mySpinnerGender.setAdapter(adapter);

        final String selectedGender = (String) mySpinnerGender.getSelectedItem();
        //Branch spinner
        final ArrayList<String> spinnerAd2 = new ArrayList<>();
        spinnerAd2.add("Main Center");
        spinnerAd2.add("Hawaly Branch");

        final Spinner mySpinnerBranch = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,spinnerAd2);
        mySpinnerBranch.setAdapter(adapter2);
        final String selectedBranch = (String) mySpinnerBranch.getSelectedItem();



        final EditText chooseDate = (EditText) findViewById(R.id.chooseDate);
        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Calendar c = Calendar.getInstance();
                int startYear = c.get(Calendar.YEAR);
                int startMonth = c.get(Calendar.MONTH);
                int startDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog date = new DatePickerDialog(EditProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        chooseDate.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                        Toast.makeText(EditProfile.this, year+"-"+(month+1)+"-"+dayOfMonth, Toast.LENGTH_SHORT).show();

                    }
                },startYear,startMonth,startDay);

                date.show();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject o = new JSONObject();
                try {

                    profile.setEmail(email.getText().toString());
                    profile.setGender(mySpinnerGender.getSelectedItem().toString());
                    profile.setBirthDate(chooseDate.getText().toString());
                    profile.setPhone(phone.getText().toString());
                    profile.setBranchId(mySpinnerBranch.getSelectedItemPosition()+1);
                    profile.setCivilId(civil.getText().toString());

                    o.put("email",profile.getEmail());
                    o.put("gender",profile.getGender());
                    o.put("birthDate",profile.getBirthDate());
                    o.put("phone",profile.getPhone());
                    o.put("branchId",profile.getBranchId());
                    o.put("civilId",profile.getCivilId());
                    o.put("deleted",profile.getDeleted());
                    o.put("status",profile.getStatus());
                    o.put("permissions",profile.getPermissions());
                    o.put("firstName",profile.getFirstName());
                    o.put("middleName",profile.getMiddleName());
                    o.put("lastName",profile.getLastName());
                    o.put("bbadminId",profile.getBbadminId());
                    o.put("password",profile.getPassword());

                    System.out.println(o);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String url="http://34.196.107.188:8081/MhealthWeb/webresources/bbadmin/"+id;
                Toast.makeText(EditProfile.this, ""+url, Toast.LENGTH_SHORT).show();
                RequestQueue queue = NetworkRequest.getInstance().getRequestQueue(EditProfile.this);

                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.PUT, url,o, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                            Toast.makeText(EditProfile.this, "Saved", Toast.LENGTH_SHORT).show();
                              finish();
                                //Toast.makeText(LoginActivity.this, "log "+pref1.getString("profile","error"), Toast.LENGTH_SHORT).show();



                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditProfile.this, "error "+ error , Toast.LENGTH_SHORT).show();

                    }
                });
                queue.add(stringRequest);


//                Toast.makeText(EditProfile.this, "name: "+nameText.getText()  , Toast.LENGTH_SHORT).show();
//                Toast.makeText(EditProfile.this, "Gender: "+selectedGender  , Toast.LENGTH_SHORT).show();
//                Toast.makeText(EditProfile.this, "Email : "+  email.getText()  , Toast.LENGTH_SHORT).show();
//                Toast.makeText(EditProfile.this, "phone: "+ phone.getText() , Toast.LENGTH_SHORT).show();
//                Toast.makeText(EditProfile.this, "dATE : "+chooseDate.getText()  , Toast.LENGTH_SHORT).show();
//                Toast.makeText(EditProfile.this, "BRANCH : "+   selectedBranch  , Toast.LENGTH_SHORT).show();
//                Toast.makeText(EditProfile.this, "Civil : "+   civil.getText()  , Toast.LENGTH_SHORT).show();

            }
        });


        //name
        nameText.setText(profile.getFirstName()+" "+profile.getMiddleName()+" "+profile.getLastName());
        //Gender
        if(profile.getGender().equals("F")){
            mySpinnerGender.setSelection(1);
        }
        else{
            mySpinnerGender.setSelection(0);
        }
        //date
        chooseDate.setText(profile.getBirthDate());
        //email
        email.setText(profile.getEmail());
        //phone
        phone.setText(profile.getPhone());
        //branch
        if(profile.getBbadminId() == 1){
                mySpinnerBranch.setSelection(0);
        }
        else{
            mySpinnerBranch.setSelection(1);

        }
        //ID
        civil.setText(profile.getCivilId());
    }

}