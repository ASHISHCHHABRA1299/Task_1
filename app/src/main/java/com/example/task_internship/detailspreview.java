package com.example.task_internship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class detailspreview extends AppCompatActivity {

    TextView e1,e2,e3,e4,e5;
    Button b;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailspreview);
        e1=(TextView) findViewById(R.id.e1);
        e2=(TextView)findViewById(R.id.e2);
        e3=(TextView)findViewById(R.id.e3);
        e4=(TextView)findViewById(R.id.e4);
        e5=(TextView)findViewById(R.id.e5);
        b=(Button)findViewById(R.id.b);
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
