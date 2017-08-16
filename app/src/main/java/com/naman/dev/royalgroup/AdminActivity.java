package com.naman.dev.royalgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminActivity extends AppCompatActivity {

    private CardView pge38card;
    private CardView pge114card;
    private CardView pge1124card;
    private CardView pgbansalcard;

    private TextView impContactsbtn;
    private TextView newGuestbtn;

    private TextView remainingSeats38;
    private TextView remainingSeats124;
    private TextView remainingSeats114;
    private TextView remainingSeats786;

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        pge38card = (CardView) findViewById(R.id.pge38_card);
        pge114card = (CardView) findViewById(R.id.pge114_card);
        pge1124card = (CardView) findViewById(R.id.pge1124_card);
        pgbansalcard = (CardView) findViewById(R.id.pgbansal_card);

        remainingSeats38 = (TextView) findViewById(R.id.e38seats);
        remainingSeats124 = (TextView) findViewById(R.id.e124seats);
        remainingSeats114 = (TextView) findViewById(R.id.e114seats);
        remainingSeats786 = (TextView) findViewById(R.id.bansalseats);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("pg").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d:dataSnapshot.getChildren()){
                    String pgname = d.getKey();
                    String seats_remaining = d.child("pgRemainingSeats").getValue().toString();
                    switch (pgname){
                        case "38" : remainingSeats38.setText(seats_remaining + " seats"); break;
                        case "124": remainingSeats124.setText(seats_remaining+ " seats"); break;
                        case "114": remainingSeats114.setText(seats_remaining + " seats"); break;
                        case "786": remainingSeats786.setText(seats_remaining + " seats"); break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        pge38card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, PGInfoActivity.class);
                i.putExtra("pgname", "38");
                startActivity(i);

            }
        });
        pge1124card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, PGInfoActivity.class);
                i.putExtra("pgname", "124");
                startActivity(i);

            }
        });
        pge114card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, PGInfoActivity.class);
                i.putExtra("pgname", "114");
                startActivity(i);

            }
        });
        pgbansalcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, PGInfoActivity.class);
                i.putExtra("pgname", "786");
                startActivity(i);

            }
        });
        impContactsbtn = (TextView) findViewById(R.id.imp_contacts_btn);
        newGuestbtn = (TextView) findViewById(R.id.new_guest_btn);

        impContactsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, ImpContactsActivity.class));
            }
        });

        newGuestbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, NewGuestActivity.class));
            }
        });
    }

    private void logOut() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(AdminActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.controlmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logoutbtn:
                logOut();
                return true;
            case R.id.newroombtn:
                startActivity(new Intent(AdminActivity.this, NewRoomActivity.class));
                return true;
            case R.id.visitormodebtn:
                startActivity(new Intent(AdminActivity.this, VisitorActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
