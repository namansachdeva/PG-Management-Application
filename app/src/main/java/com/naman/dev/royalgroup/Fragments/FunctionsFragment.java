package com.naman.dev.royalgroup.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.naman.dev.royalgroup.GuestDetailActivity;
import com.naman.dev.royalgroup.NewPaymentActivity;
import com.naman.dev.royalgroup.R;

/**
 * Created by naman on 8/20/2017.
 */

public class FunctionsFragment extends Fragment {
    private DatabaseReference mDatabase;
    private String guestKey;

    private Button uploadPhotoBtn;
    private Button uploadIDBtn;
    private Button newPaymentBtn;
    private Button changeRoomBtn;
    private Button leavePGButton;

    public FunctionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_functions, container, false);
        uploadIDBtn = (Button) rootView.findViewById(R.id.uploadIDbtn);
        uploadPhotoBtn = (Button) rootView.findViewById(R.id.uploadPhotobtn);
        newPaymentBtn = (Button) rootView.findViewById(R.id.newPaymentbtn);
        changeRoomBtn = (Button) rootView.findViewById(R.id.changeRoombtn);
        leavePGButton = (Button) rootView.findViewById(R.id.leavePGbtn);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);

        GuestDetailActivity guestDetailActivity = (GuestDetailActivity) getActivity();
        guestKey = guestDetailActivity.getGuestKey();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        newPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newPaymentIntent = new Intent(getActivity(), NewPaymentActivity.class);
                newPaymentIntent.putExtra("guestKey", guestKey);
                startActivity(newPaymentIntent);
            }
        });


    }
}
