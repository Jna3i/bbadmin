package com.pifss.bbadmin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView forgetPassword = (TextView) findViewById(R.id.textView);
        final AutoCompleteTextView email = (AutoCompleteTextView) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final Button login = (Button) findViewById(R.id.email_sign_in_button);
        bbadmin model;

        email.setText("de@de.com");
        password.setText("@a123");

        loggedIn();

        // TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.campInfo_toolbarID);
        toolbar.setTitle("Login");
        toolbar.setTitleTextColor(Color.WHITE);



        //Setting frag = new Setting();

        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject o = new JSONObject();
                try {
                    o.put("username",email.getText().toString());
                    o.put("password",password.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String url="http://34.196.107.188:8081/MhealthWeb/webresources/bbadmin/login";
                RequestQueue queue = NetworkRequest.getInstance().getRequestQueue(LoginActivity.this);

                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,o, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getInt("errorCode") == 0){
                                bbadmin profile = new bbadmin();
                                profile= new Gson().fromJson(response.getString("items"),bbadmin.class);
                                SharedPreferences pref1 = getSharedPreferences("bbadmin_profile",MODE_PRIVATE);
                                SharedPreferences.Editor Ed1 = pref1.edit();
                                Ed1.putString("profile",profile.toJSONString());
                                Ed1.commit();
                                Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                                //Toast.makeText(LoginActivity.this, "log "+pref1.getString("profile","error"), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this, MainDrawer.class);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Username or password is wrong!!" , Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "error "+ error , Toast.LENGTH_SHORT).show();

                    }
                });
                        queue.add(stringRequest);
//                /////////
//                boolean flag =true;
//                        flag= login(email.getText().toString(),password.getText().toString());
//                Toast.makeText(LoginActivity.this, "login is "+ flag , Toast.LENGTH_SHORT).show();
//
//                if (flag == true) {
//                    Intent i = new Intent(LoginActivity.this, MainDrawer.class);
//                    startActivity(i);
//                }
//                else {
//                    Intent i = new Intent(LoginActivity.this, LoginActivity.class);
//                    startActivity(i);
//                }
            }
        });

        forgetPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                startActivity(i);
            }
        });



    }

    public boolean login(String email, String password){
        if (email.equals("joey@gmail.com") && password.equals("123")) {

            return true;
        }
        else
            return false;
    }

    public void loggedIn(){
        SharedPreferences pref1 = getSharedPreferences("bbadmin_profile",MODE_PRIVATE);
        if(pref1.contains("profile")){
            Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this, MainDrawer.class);
            finish();
            startActivity(i);

        }

    }



}

