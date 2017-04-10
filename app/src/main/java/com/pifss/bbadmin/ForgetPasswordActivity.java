package com.pifss.bbadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb);

        EditText forget_email = (EditText) findViewById(R.id.insertEmail);
        EditText forget_civil = (EditText) findViewById(R.id.forget_insertCivilId);
        forget_email.setText("z@z.com");
        forget_civil.setText("294011302209");

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final EditText forgetpassword = (EditText) findViewById(R.id.insertEmail);
        Button send = (Button) findViewById(R.id.button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                forgetRequest();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public JSONObject createObjectToSend(){
        EditText forget_email = (EditText) findViewById(R.id.insertEmail);
        EditText forget_civil = (EditText) findViewById(R.id.forget_insertCivilId);

        JSONObject objSend = new JSONObject();
        try {
            objSend.put("username",forget_email.getText().toString());
            objSend.put("civilid",forget_civil.getText().toString());


            return objSend;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return objSend;
    }

    //Send
    public void forgetRequest(){

        String url = Links.BBADMIN_RESET;
        RequestQueue queue = NetworkRequest.getInstance().getRequestQueue(ForgetPasswordActivity.this);

        JSONObject objSend = createObjectToSend();

        JsonObjectRequest JsonRequest = new JsonObjectRequest(Request.Method.POST, url,objSend, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt("errorCode") == 0){
                        Toast.makeText(ForgetPasswordActivity.this, "new password sent to your email", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ForgetPasswordActivity.this, "wrong information", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(JsonRequest);


    }
}
