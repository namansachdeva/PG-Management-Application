package com.naman.dev.royalgroup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class NewGuestActivity extends AppCompatActivity {

    private AutoCompleteTextView guestNameView;
    private AutoCompleteTextView guestMobileView;
    private AutoCompleteTextView guestPermaAddressView;
    private AutoCompleteTextView guestCorresAddressView;
    private AutoCompleteTextView guestStartDateView;
    private AutoCompleteTextView guestRoomNoView;
    private AutoCompleteTextView guestPGNameView;

    private Button proceedNewGuestbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_guest);
        setTitle("Add New Guest");

        guestNameView = (AutoCompleteTextView) findViewById(R.id.guest_name);
        guestMobileView = (AutoCompleteTextView) findViewById(R.id.guest_mobile_no);
        guestPermaAddressView = (AutoCompleteTextView) findViewById(R.id.guest_perma_address);
        guestCorresAddressView = (AutoCompleteTextView) findViewById(R.id.guest_corres_address);
        guestStartDateView = (AutoCompleteTextView) findViewById(R.id.guest_start_date);
        guestPGNameView = (AutoCompleteTextView) findViewById(R.id.guest_pg_name);
        guestRoomNoView = (AutoCompleteTextView) findViewById(R.id.guest_roomno);

        proceedNewGuestbtn = (Button) findViewById(R.id.proceedbtn);

        proceedNewGuestbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAllFields();
            }
        });
    }

    private boolean checkAllFields() {
        guestNameView.setError(null);
        guestMobileView.setError(null);
        guestPermaAddressView.setError(null);
        guestCorresAddressView.setError(null);
        guestStartDateView.setError(null);
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
        if (TextUtils.isEmpty(guestStartDateView.getText().toString())) {
            guestStartDateView.setError("Enter Start Date");
            focusView = proceedNewGuestbtn;
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
