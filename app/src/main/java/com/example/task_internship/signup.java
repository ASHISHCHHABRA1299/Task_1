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

public class signup extends AppCompatActivity {
    EditText e1;
    EditText e2;
    Button b1;
    TextView t1;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth=FirebaseAuth.getInstance();
        e1=(EditText) findViewById(R.id.editText);
        e2=(EditText) findViewById(R.id.editText2);
        b1=(Button) findViewById(R.id.b1);
        t1=(TextView)findViewById(R.id.signin);
        progressDialog=new ProgressDialog(this);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(signup.this,Login.class);
                startActivity(in);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = e1.getText().toString().trim();
                String password = e2.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(signup.this, "PLease Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(signup.this, "PLease Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setMessage("REGISTERING USER...");
                progressDialog.show();

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(signup.this, "REGISTERED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                            UID=firebaseAuth.getCurrentUser().getUid();
                            Intent i = new Intent(signup.this,detailsactivity.class );
                            i.putExtra("uid",UID);
                            startActivity(i);

                        }else
                        {
                            Toast.makeText(signup.this, "COULD NOT REGISTER, TRY AGAIN!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
            }
        });
    }


}
