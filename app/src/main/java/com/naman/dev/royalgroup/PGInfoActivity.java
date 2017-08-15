package com.naman.dev.royalgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.naman.dev.royalgroup.Models.Room;

public class PGInfoActivity extends AppCompatActivity {

    private String PG_KEY;
    private DatabaseReference mDatabase;
    private RecyclerView pginfoRecyclerView;
    private FirebaseRecyclerAdapter<Room, RoomViewHolder> mAdapter;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pginfo);
        Intent i = getIntent();
        PG_KEY = i.getStringExtra("pgname");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);

        pginfoRecyclerView = (RecyclerView) findViewById(R.id.pginforecylerview);
        mManager = new LinearLayoutManager(PGInfoActivity.this);
        pginfoRecyclerView.setLayoutManager(mManager);

        Query query = mDatabase.child("pg").child(PG_KEY).child("rooms");

        mAdapter = new FirebaseRecyclerAdapter<Room, RoomViewHolder>(Room.class, R.layout.room_item, RoomViewHolder.class, query) {

            @Override
            protected void populateViewHolder(RoomViewHolder viewHolder, Room model, int position) {
                final DatabaseReference roomsRef = getRef(position);
                final String roomKey = roomsRef.getKey();

                viewHolder.setRoomno(roomKey);
                viewHolder.setSeats(model.roomCapacity - model.roomRemainingSeats, model.roomCapacity);

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(PGInfoActivity.this, RoomInfoActivity.class);
                        i.putExtra("roomno", roomKey);
                        i.putExtra("pgname", PG_KEY);
                        startActivity(i);
                    }
                });
            }
        };
        pginfoRecyclerView.setAdapter(mAdapter);
    }

    private static class RoomViewHolder extends RecyclerView.ViewHolder {

        private TextView roomno;
        private RatingBar seats;

        public RoomViewHolder(View itemView) {
            super(itemView);
            roomno = (TextView) itemView.findViewById(R.id.room_item_roomno);
            seats = (RatingBar) itemView.findViewById(R.id.room_item_seats);
        }

        public void setRoomno(String roomno) {
            this.roomno.setText(roomno);
        }

        public void setSeats(int seats, int capacity) {
            this.seats.setRating(seats);
            this.seats.setNumStars(capacity);
        }
    }
}
