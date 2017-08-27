package com.naman.dev.royalgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.naman.dev.royalgroup.Models.Guest;

public class NewPaymentActivity extends AppCompatActivity {
    private String guestKey;
    private DatabaseReference mDatabase;

    private AutoCompleteTextView paymentAmount;
    private RadioButton rentRadioBtn;
    private RadioButton securityRadioBtn;
    private Button payNowBtn;
    private TextView guestNameTextView;
    private TextView guestPendingRentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_payment);
        Intent i = getIntent();
        guestKey = i.getStringExtra("guestKey");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        guestNameTextView = (TextView) findViewById(R.id.guest_name_text);
        guestPendingRentTextView = (TextView) findViewById(R.id.pending_rent_text);
        paymentAmount = (AutoCompleteTextView) findViewById(R.id.amount_paid);
        rentRadioBtn = (RadioButton) findViewById(R.id.rent_radio_btn);
        securityRadioBtn = (RadioButton) findViewById(R.id.security_radio_btn);
        payNowBtn = (Button) findViewById(R.id.submit_payment_btn);

        mDatabase.child("guests").child(guestKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Guest guest = dataSnapshot.getValue(Guest.class);
                guestNameTextView.setText(guest.guestName);
                guestPendingRentTextView.setText(guest.guestPendingRent);
                paymentAmount.setText(guest.guestPendingRent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
