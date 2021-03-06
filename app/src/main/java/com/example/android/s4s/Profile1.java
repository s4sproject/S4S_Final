package com.example.android.s4s;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class Profile1 extends MainActivity {

    SharedPreferences sp1;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String value = bundle.getString("vasu");


        sp1 = getSharedPreferences("uid", MODE_PRIVATE);
        sp1.getString("getuid", null);
        String id = sp1.getString("getuid", null);


        DatabaseReference rootRef, userRef;
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();
        assert value != null;
        userRef = rootRef.child("User").child(Objects.requireNonNull(mAuth.getCurrentUser()).getUid());

        //String uid = "GWHlbQ0OhOhoV8SQ45JPKiZfqLR2";



        /*try {

        //assert id != null;
        userRef = FirebaseDatabase.getInstance().getReference("User").child();
        }
        catch (NullPointerException ignored)
        {
            Toast.makeText(this, "Null User ID",
                    Toast.LENGTH_LONG).show();
        }*/
       // String id = sp1.getString("getuid", null);



            userRef.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    try {
                        String st = Objects.requireNonNull(dataSnapshot.child("name").getValue()).toString();
                        TextView s = findViewById(R.id.profile_name);
                        s.setText(st);


                        String st1 = Objects.requireNonNull(dataSnapshot.child("email").getValue()).toString();
                        TextView s1 = findViewById(R.id.profile_email);
                        s1.setText(st1);


                        String st2 = Objects.requireNonNull(dataSnapshot.child("phone").getValue()).toString();
                        TextView s2 = findViewById(R.id.profile_phone);
                        s2.setText(st2);
                    }
                    catch (NullPointerException ignored)
                    {

                    }

                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



       /* String uid = user.getUid();
        String name = user.getDisplayName();
        String email = user.getEmail();
        String phone = user.getPhoneNumber();
        TextView t = (TextView)findViewById(R.id.profile_name);
        t.setText(name);
        TextView t1 = (TextView)findViewById(R.id.profile_email);
        t1.setText(email);
        TextView t2 = (TextView)findViewById(R.id.profile_phone);
        t2.setText(phone);*/


                // TextView t3 = (TextView)findViewById(R.id.profile_name);
                //t3.setText(name);


                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }


    }

            public void onBackPressed() {
                Intent i = new Intent(Profile1.this, MainActivity.class);
                startActivity(i);
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                if (item.getItemId() == android.R.id.home)
                    finish();
                return super.onOptionsItemSelected(item);
            }
}








