package com.example.task_internship;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class detailsactivity extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    TextView t1;
    Button b;
    String UId;
    FirebaseFirestore fstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsactivity);
        fstore=FirebaseFirestore.getInstance();
     e1=(EditText)findViewById(R.id.e1);
     e2=(EditText)findViewById(R.id.e2);
     e3=(EditText)findViewById(R.id.e3);
     e4=(EditText)findViewById(R.id.e4);
     t1=(TextView)findViewById(R.id.t1);
     UId=getIntent().getStringExtra("uid");
     final Calendar calendar=Calendar.getInstance();
     final SimpleDateFormat sdf=new SimpleDateFormat("EEEE,dd-MMM-yyyy hh:mm:ss a");
     b=(Button)findViewById(R.id.b);
     b.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {


             String name=e1.getText().toString();
             String age=e2.getText().toString();
             String gender=e3.getText().toString();
             String country=e4.getText().toString();
             String datetime=sdf.format(calendar.getTime());
             DocumentReference documentReference=fstore.collection("users").document(UId);
             Map<String,Object> user=new HashMap<>();
             user.put("Name",name);
             user.put("Age",age);
             user.put("Gender",gender);
             user.put("Country",country);
             user.put("DateTime",datetime);
             documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                 @Override
                 public void onSuccess(Void aVoid) {
                     Toast.makeText(detailsactivity.this, "User Created", Toast.LENGTH_SHORT).show();
                 }
             });


         }
     });
    }
}
