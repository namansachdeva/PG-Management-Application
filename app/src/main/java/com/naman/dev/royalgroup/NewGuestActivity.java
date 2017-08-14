package com.naman.dev.royalgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.naman.dev.royalgroup.Models.Guest;

import java.util.HashMap;
import java.util.Map;

public class NewGuestActivity extends AppCompatActivity {

    private AutoCompleteTextView guestNameView;
    private AutoCompleteTextView guestMobileView;
    private AutoCompleteTextView guestPermaAddressView;
    private AutoCompleteTextView guestCorresAddressView;
    private DatePicker guestStartDatePicker;
    private AutoCompleteTextView guestRoomNoView;
    private AutoCompleteTextView guestPGNameView;

    private Button proceedNewGuestbtn;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_guest);
        setTitle("Add New Guest");

        guestNameView = (AutoCompleteTextView) findViewById(R.id.guest_name);
        guestMobileView = (AutoCompleteTextView) findViewById(R.id.guest_mobile_no);
        guestPermaAddressView = (AutoCompleteTextView) findViewById(R.id.guest_perma_address);
        guestCorresAddressView = (AutoCompleteTextView) findViewById(R.id.guest_corres_address);
        guestStartDatePicker = (DatePicker) findViewById(R.id.guest_start_date);
        guestPGNameView = (AutoCompleteTextView) findViewById(R.id.guest_pg_name);
        guestRoomNoView = (AutoCompleteTextView) findViewById(R.id.guest_roomno);

        proceedNewGuestbtn = (Button) findViewById(R.id.proceedbtn);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        proceedNewGuestbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAllFields()) {
                    // setEditingEnabled(false);

                    final String name = guestNameView.getText().toString();
                    final String mobile = guestMobileView.getText().toString();
                    final String paddress = guestPermaAddressView.getText().toString();
                    final String caddress = guestCorresAddressView.getText().toString();
                    final long date = guestStartDatePicker.getMaxDate();
                    final String pgname = guestPGNameView.getText().toString();
                    final String roomno = guestRoomNoView.getText().toString();

                    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child("pg").child(pgname).getValue() != null ||
                                    dataSnapshot.child("pg").child(pgname).child("rooms").child(roomno) != null) {

                                String key = mDatabase.child("pg").child(pgname).child("rooms").child(roomno).push().getKey();
                                Guest guest = new Guest(name, mobile, paddress, caddress, date, pgname, roomno,true);
                                Map<String, Object> guestValues = guest.toMap();
                                Map<String, Object> childUpdates = new HashMap<>();
                                childUpdates.put("/pg/" + pgname + "/rooms/" + roomno + "/guests/"+key,name);
                                childUpdates.put("/guests/" + key, guestValues);
                                int pgRemainingSeats = Integer.parseInt(dataSnapshot.child("pg").child(pgname).child("pgRemainingSeats").getValue().toString());
                                int roomRemainingSeats = Integer.parseInt(dataSnapshot.child("pg").child(pgname).child("rooms").child(roomno).child("roomRemainingSeats").getValue().toString());
                                if(roomRemainingSeats==0){
                                    Toast.makeText(NewGuestActivity.this,"No seats available in this room.",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if(pgRemainingSeats==0){
                                    Toast.makeText(NewGuestActivity.this,"No seats available in this PG.",Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                pgRemainingSeats -= 1;
                                roomRemainingSeats -= 1;
                                childUpdates.put("/pg/" + pgname + "/pgRemainingSeats", pgRemainingSeats);
                                childUpdates.put("/pg/" + pgname + "/rooms/" + roomno + "/roomRemainingSeats", roomRemainingSeats);
                                mDatabase.updateChildren(childUpdates);

                                Intent intentToPayment = new Intent(NewGuestActivity.this,NewPaymentActivity.class);
                                intentToPayment.putExtra("key",key);
                                startActivity(intentToPayment);
                                finish();
                            } else {
                                Toast.makeText(NewGuestActivity.this, "Error adding the guest.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

    private boolean checkAllFields() {
        guestNameView.setError(null);
        guestMobileView.setError(null);
        guestPermaAddressView.setError(null);
        guestCorresAddressView.setError(null);
        guestPGNameView.setError(null);
        guestRoomNoView.setError(null);

        View focusView = null;
        boolean cancel = false;

        if (TextUtils.isEmpty(guestNameView.getText().toString())) {
            guestNameView.setError("Enter Name");
            focusView = guestNameView;
            cancel = true;
        }
        if (TextUtils.isEmpty(guestMobileView.getText().toString()) || guestMobileView.getText().toString().length() != 10) {
            guestMobileView.setError("Enter Correct Mobile Number");
            focusView = guestMobileView;
            cancel = true;
        }
        if (TextUtils.isEmpty(guestPermaAddressView.getText().toString())) {
            guestPermaAddressView.setError("Enter Permanent Address");
            focusView = guestPermaAddressView;
            cancel = true;
        }
        if (TextUtils.isEmpty(guestCorresAddressView.getText().toString())) {
            guestCorresAddressView.setError("Enter Correspondence Address");
            focusView = guestCorresAddressView;
            cancel = true;
        }
        if (TextUtils.isEmpty(guestPGNameView.getText().toString())) {
            guestPGNameView.setError("Enter Correct PG Name");
            focusView = guestPGNameView;
            cancel = true;
        }
        if (TextUtils.isEmpty(guestRoomNoView.getText().toString())) {
            guestRoomNoView.setError("Enter Correct Room No");
            focusView = guestRoomNoView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        }

        return !cancel;

    }
}
