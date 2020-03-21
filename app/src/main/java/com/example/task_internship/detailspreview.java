package com.example.task_internship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class detailspreview extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    TextView e5;
    Button b,edit,update;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailspreview);
        e1=(EditText) findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        e4=(EditText)findViewById(R.id.e4);
        e5=(TextView)findViewById(R.id.e5);
        b=(Button)findViewById(R.id.b);
        edit=(Button)findViewById(R.id.edit);
        update=(Button)findViewById(R.id.update);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        userid=fAuth.getCurrentUser().getUid();
        DocumentReference documentReference=fStore.collection("users").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                 e1.setText(documentSnapshot.getString("Name"));
                 e2.setText(documentSnapshot.getString("Age"));
                 e3.setText(documentSnapshot.getString("Gender"));
                 e4.setText(documentSnapshot.getString("Country"));
                 e5.setText(documentSnapshot.getString("DateTime"));


            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setEnabled(true);
                e2.setEnabled(true);
                e3.setEnabled(true);
                e4.setEnabled(true);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=e1.getText().toString();
                String age=e2.getText().toString();
                String gender=e3.getText().toString();
                String country=e4.getText().toString();
                String datetime=e5.getText().toString();
                DocumentReference documentReference=fStore.collection("users").document(userid);
                Map<String,Object> user=new HashMap<>();
                user.put("Name",name);
                user.put("Age",age);
                user.put("Gender",gender);
                user.put("Country",country);
                user.put("DateTime",datetime);
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(detailspreview.this, "Updated", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(detailspreview.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
