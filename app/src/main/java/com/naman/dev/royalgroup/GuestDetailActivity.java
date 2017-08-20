package com.naman.dev.royalgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GuestDetailActivity extends AppCompatActivity {

    private String guestKey;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_detail);
        Intent i = getIntent();
        guestKey = i.getStringExtra("guestkey");
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }
}
