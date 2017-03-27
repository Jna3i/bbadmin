package com.pifss.bbadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        final EditText forgetpassword = (EditText) findViewById(R.id.insertEmail);
        Button send = (Button) findViewById(R.id.button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ForgetPasswordActivity.this, "sent to "+ forgetpassword.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ForgetPasswordActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
