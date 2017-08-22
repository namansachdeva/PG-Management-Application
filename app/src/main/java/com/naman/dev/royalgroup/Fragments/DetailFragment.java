package com.naman.dev.royalgroup.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.naman.dev.royalgroup.GuestDetailActivity;
import com.naman.dev.royalgroup.Models.Guest;
import com.naman.dev.royalgroup.R;

public class DetailFragment extends Fragment {

    private DatabaseReference mDatabase;
    private String guestKey;

    private TextView guestName;
    private TextView guestMobileNo;
    private TextView guestPGName;
    private TextView guestRoomNo;
    private TextView guestCorresAddress;
    private TextView guestPermaAddress;
    private TextView guestStartdate;
    private TextView guestSecurity;
    private TextView guestRent;
    private ImageView guestPhoto;
    private ImageView showIDbtn;
    private ImageView callIntent;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        guestName = (TextView) rootView.findViewById(R.id.guest_detail_name);
        guestMobileNo = (TextView) rootView.findViewById(R.id.guest_detail_mobile_no);
        guestPGName = (TextView) rootView.findViewById(R.id.guest_detail_pg_name);
        guestRoomNo = (TextView) rootView.findViewById(R.id.guest_detail_room_no);
        guestCorresAddress = (TextView) rootView.findViewById(R.id.guest_detail_corres_address);
        guestPermaAddress = (TextView) rootView.findViewById(R.id.guest_detail_perma_address);
        callIntent = (ImageView) rootView.findViewById(R.id.guest_detail_call_icon);
        guestPhoto = (ImageView) rootView.findViewById(R.id.guest_detail_image);
        showIDbtn = (ImageView) rootView.findViewById(R.id.showIDbtn);
        guestStartdate = (TextView) rootView.findViewById(R.id.guest_detail_start_date);
        guestSecurity = (TextView) rootView.findViewById(R.id.guest_detail_security);
        guestRent = (TextView) rootView.findViewById(R.id.guest_detail_rent);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);

        GuestDetailActivity guestDetailActivity = (GuestDetailActivity) getActivity();
        guestKey = guestDetailActivity.getGuestKey();

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mDatabase.child("guests").child(guestKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final Guest guest =  dataSnapshot.getValue(Guest.class);
                guestName.setText(guest.guestName);
                guestMobileNo.setText(guest.guestMobileNo);
                guestPGName.setText(guest.guestPGName);
                guestRoomNo.setText(guest.guestRoomNo);
                guestCorresAddress.setText(guest.guestCorrespondenceAddress);
                guestPermaAddress.setText(guest.guestPermanentAddress);
                guestSecurity.setText(guest.guestSecurity+"");
                guestStartdate.setText(guest.guestStartDate+"");
                guestRent.setText(guest.guestRent+"");
                callIntent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + guestMobileNo.getText()));
                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        getActivity().startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
