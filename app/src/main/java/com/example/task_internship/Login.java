package com.example.task_internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText e3;
    EditText e4;
    Button b2;
    TextView t2;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth=FirebaseAuth.getInstance();
        e3=(EditText)findViewById(R.id.e1);
        e4=(EditText)findViewById(R.id.e2);
        b2=(Button)findViewById(R.id.b2);
        t2=(TextView)findViewById(R.id.signup);
        progressDialog=new ProgressDialog(this);
    }
    void login(View v)
    {
        Intent i= new Intent(Login.this,signup.class);
        startActivity(i);
    }
    void userlogin(View v)
    {
        String email=e3.getText().toString().trim();
        String password=e4.getText().toString().trim();
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "PLEASE ENTER EMAIL", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "PLEASE ENTER PASSWORD", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage(" LOGGING IN...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    finish(); // to end the current activity
                    Intent i= new Intent(Login.this,detailspreview.class);
                    startActivity(i);
                }

            }
        });
    }
}
