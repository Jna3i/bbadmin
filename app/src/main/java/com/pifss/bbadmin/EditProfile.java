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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EditProfile extends AppCompatActivity {

    bbadmin profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        final SharedPreferences pref1 = getSharedPreferences("bbadmin_profile", MODE_PRIVATE);
        String S1 = pref1.getString("profile","error");

        profile= new Gson().fromJson(S1,bbadmin.class);
        final int id = profile.getBbadminId();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle(getString(R.string.EditProfile_title_activity_edit_profile));
        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button save = (Button) findViewById(R.id.button3);
//        final EditText nameText = (EditText) findViewById(R.id.nameText);
//        final EditText phone = (EditText) findViewById(R.id.phoneText);
//        final EditText civil = (EditText) findViewById(R.id.civilText);
//        final EditText email = (EditText) findViewById(R.id.emailText);
//        final EditText password = (EditText) findViewById(R.id.Edit_password);

        //Gender spinner
//        ArrayList<String> spinnerAd = new ArrayList<>();
//        spinnerAd.add("Male");
//        spinnerAd.add("Female");
//
//        final Spinner mySpinnerGender = (Spinner) findViewById(R.id.spinner3);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,spinnerAd);
//        mySpinnerGender.setAdapter(adapter);
//
//        final String selectedGender = (String) mySpinnerGender.getSelectedItem();

        //Branch spinner
//        final ArrayList<String> spinnerAd2 = new ArrayList<>();
//        spinnerAd2.add("Main Center");
//        spinnerAd2.add("Hawaly Branch");
//
//        final Spinner mySpinnerBranch = (Spinner) findViewById(R.id.spinner4);
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,spinnerAd2);
//        mySpinnerBranch.setAdapter(adapter2);
//        final String selectedBranch = (String) mySpinnerBranch.getSelectedItem();



        final TextView chooseDate = (TextView) findViewById(R.id.chooseDate);

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
                JSONObject objToSend = createObjectToSend();


                String url=Links.BBADMIN+"/"+id;
                //Toast.makeText(EditProfile.this, ""+url, Toast.LENGTH_SHORT).show();
                RequestQueue queue = NetworkRequest.getInstance().getRequestQueue(EditProfile.this);

                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.PUT, url,objToSend, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                            Toast.makeText(EditProfile.this, "Saved", Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor Ed1 = pref1.edit();
                        Ed1.putString("profile",profile.toJSONString());
                        Ed1.commit();
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


            }
        });


        setProfileData();
    }

    public void setProfileData(){

        EditText nameText   = (EditText) findViewById(R.id.nameText);
        EditText phone      = (EditText) findViewById(R.id.phoneText);
        EditText civil      = (EditText) findViewById(R.id.civilText);
        EditText email      = (EditText) findViewById(R.id.emailText);
        EditText password   = (EditText) findViewById(R.id.Edit_password);

        TextView chooseDate = (TextView) findViewById(R.id.chooseDate);

        //name
        nameText.setText(profile.getFirstName()+" "+profile.getLastName());

        //date
        chooseDate.setText(profile.getBirthDate());

        //email
        email.setText(profile.getEmail());

        //phone
        phone.setText(profile.getPhone());


        //ID
        civil.setText(profile.getCivilId());

        password.setText(profile.getPassword());

        setUserGender();
        setUserBranch();
    }
    //set user Gender
    public void setUserGender(){

        //Gender spinner
        ArrayList<String> spinnerAd = new ArrayList<>();
        spinnerAd.add("Male");
        spinnerAd.add("Female");

        Spinner mySpinnerGender = (Spinner) findViewById(R.id.spinner3);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,spinnerAd);
        mySpinnerGender.setAdapter(adapter);

        //Gender
        if(profile.getGender().equals("F")){
            mySpinnerGender.setSelection(1);
        }
        else{
            mySpinnerGender.setSelection(0);
        }

    }
    //set user branch
    public void setUserBranch(){

        //Branch spinner
        final ArrayList<String> spinnerAd2 = new ArrayList<>();
        spinnerAd2.add("Main Center");
        spinnerAd2.add("Hawaly Branch");

        Spinner mySpinnerBranch = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,spinnerAd2);
        mySpinnerBranch.setAdapter(adapter2);

        //branch
        if(profile.getBbadminId() == 1){
            mySpinnerBranch.setSelection(0);
        }
        else{
            mySpinnerBranch.setSelection(1);

        }

    }

    //profile to send
    public JSONObject createObjectToSend(){

        JSONObject objToSend = new JSONObject();
        EditText nameText   = (EditText) findViewById(R.id.nameText);
        EditText phone      = (EditText) findViewById(R.id.phoneText);
        EditText civil      = (EditText) findViewById(R.id.civilText);
        EditText email      = (EditText) findViewById(R.id.emailText);
        EditText password   = (EditText) findViewById(R.id.Edit_password);

        String[] fullName = nameText.getText().toString().split("\\s+");

        TextView chooseDate = (TextView) findViewById(R.id.chooseDate);

        Spinner mySpinnerGender = (Spinner) findViewById(R.id.spinner3);
        Spinner mySpinnerBranch = (Spinner) findViewById(R.id.spinner4);

        profile.setEmail(email.getText().toString());
        if (mySpinnerGender.getSelectedItem().toString().equalsIgnoreCase("Male")){
            profile.setGender("M");
        } else {
            profile.setGender("F");
        }

        profile.setBirthDate(chooseDate.getText().toString());
        profile.setPhone(phone.getText().toString());
        profile.setBranchId(mySpinnerBranch.getSelectedItemPosition()+1);
        profile.setCivilId(civil.getText().toString());
        profile.setPassword(password.getText().toString());
        profile.setFirstName(fullName[0]);
        profile.setLastName(fullName[1]);

        try {

            objToSend.put("email",profile.getEmail());
            objToSend.put("gender",profile.getGender());
            objToSend.put("birthDate",profile.getBirthDate());
            objToSend.put("phone",profile.getPhone());
            objToSend.put("branchId",profile.getBranchId());
            objToSend.put("civilId",profile.getCivilId());
            objToSend.put("deleted",profile.getDeleted());
            objToSend.put("status",profile.getStatus());
            objToSend.put("permissions",profile.getPermissions());
            objToSend.put("firstName",profile.getFirstName());
            objToSend.put("middleName",profile.getMiddleName());
            objToSend.put("lastName",profile.getLastName());
            objToSend.put("bbadminId",profile.getBbadminId());
            objToSend.put("password",profile.getPassword());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  objToSend;
    }
}