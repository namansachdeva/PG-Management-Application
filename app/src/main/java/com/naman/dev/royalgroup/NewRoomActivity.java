package com.naman.dev.royalgroup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.naman.dev.royalgroup.Models.Room;

import java.util.HashMap;
import java.util.Map;

public class NewRoomActivity extends AppCompatActivity {

    private AutoCompleteTextView mRoomNo;
    private AutoCompleteTextView mCapacity;
    private AutoCompleteTextView mPGName;

    private Button mAddRoombtn;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_room);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mRoomNo = (AutoCompleteTextView) findViewById(R.id.roomno);
        mCapacity = (AutoCompleteTextView) findViewById(R.id.roomcapacity);
        mPGName = (AutoCompleteTextView) findViewById(R.id.pg_name);

        mAddRoombtn = (Button) findViewById(R.id.addroombtn);
        mAddRoombtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRoom();
            }
        });
    }

    private void addRoom() {
        mRoomNo.setError(null);
        mCapacity.setError(null);
        mPGName.setError(null);

        View focusView = null;
        boolean cancel = false;

        final String roomno =mRoomNo.getText().toString();
        final int capacity = Integer.parseInt(mCapacity.getText().toString());
        final String pgname = mPGName.getText().toString();

        if (TextUtils.isEmpty(mRoomNo.getText().toString())) {
            mRoomNo.setError("Enter Room Number");
            focusView = mRoomNo;
            cancel = true;
        }
        if (TextUtils.isEmpty(mCapacity.getText().toString())) {
            mCapacity.setError("Enter Correct Username");
            focusView = mCapacity;
            cancel = true;
        }
        if (TextUtils.isEmpty(pgname)) {
            mPGName.setError("Enter Correct Username");
            focusView = mPGName;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            setEditingEnabled(false);
            Toast.makeText(NewRoomActivity.this,"Adding New Room...",Toast.LENGTH_SHORT).show();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int pgRemainingSeats;
                    if(dataSnapshot.child("pg").child(pgname).child("pgRemainingSeats").getValue()!=null)
                        pgRemainingSeats = Integer.parseInt(dataSnapshot.child("pg").child(pgname).child("pgRemainingSeats").getValue().toString());
                    else
                        pgRemainingSeats = 0;

                    Room newRoom = new Room(capacity);
                    pgRemainingSeats += capacity;
                    Map<String, Object> roomValues = newRoom.toMap();

                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("/pg/" + pgname + "/rooms/" + roomno, roomValues);
                    childUpdates.put("/pg/"+pgname+"/pgRemainingSeats",pgRemainingSeats);
                    mDatabase.updateChildren(childUpdates);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w("NewRoomActivity", "addRoom:onCancelled", databaseError.toException());
                }
            });
            mRoomNo.setText("");
            mCapacity.setText("");
            mPGName.setText("");
            setEditingEnabled(true);

        }
    }
    private void setEditingEnabled(boolean enabled) {
        mRoomNo.setEnabled(enabled);
        mCapacity.setEnabled(enabled);
        mPGName.setEnabled(enabled);
        if (enabled) {
            mAddRoombtn.setVisibility(View.VISIBLE);
        } else {
            mAddRoombtn.setVisibility(View.GONE);
        }
    }
}
