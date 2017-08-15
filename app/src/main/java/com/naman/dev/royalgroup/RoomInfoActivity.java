package com.naman.dev.royalgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.naman.dev.royalgroup.Models.Guest;

public class RoomInfoActivity extends AppCompatActivity {

    private String PG_KEY;
    private String ROOM_KEY;
    private DatabaseReference mDatabase;
    private RecyclerView roominfoRecyclerView;
    private FirebaseRecyclerAdapter<Guest, GuestViewHolder> mAdapter;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_info);
        Intent i = getIntent();
        PG_KEY = i.getStringExtra("pgname");
        ROOM_KEY = i.getStringExtra("roomno");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);

        roominfoRecyclerView = (RecyclerView) findViewById(R.id.roominforecylerview);
        mManager = new LinearLayoutManager(RoomInfoActivity.this);
        roominfoRecyclerView.setLayoutManager(mManager);

        Query query = mDatabase.child("pg").child(PG_KEY).child("rooms").child(ROOM_KEY).child("guests");

        mAdapter = new FirebaseRecyclerAdapter<Guest, GuestViewHolder>(Guest.class, R.layout.guest_item, GuestViewHolder.class, query) {

            @Override
            protected void populateViewHolder(GuestViewHolder viewHolder, Guest model, int position) {
                final DatabaseReference roomsRef = getRef(position);
                final String guestKey = roomsRef.getKey();

                viewHolder.setRoomno(model.guestName);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(RoomInfoActivity.this, GuestDetailActivity.class);
                        i.putExtra("guestkey", guestKey);
                        startActivity(i);
                    }
                });
            }
        };
        roominfoRecyclerView.setAdapter(mAdapter);

    }

    private static class GuestViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public GuestViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.guest_item_name);
        }

        public void setRoomno(String roomno) {
            this.name.setText(roomno);
        }

    }
}
