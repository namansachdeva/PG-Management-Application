package com.naman.dev.royalgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {

    private CardView pge38card;
    private CardView pge114card;
    private CardView pge1124card;
    private CardView pgbansalcard;

    private TextView impContactsbtn;
    private TextView newGuestbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        pge38card = (CardView) findViewById(R.id.pge38_card);
        pge114card = (CardView) findViewById(R.id.pge114_card);
        pge1124card = (CardView) findViewById(R.id.pge1124_card);
        pge38card =(CardView) findViewById(R.id.pge38_card);

        impContactsbtn = (TextView) findViewById(R.id.imp_contacts_btn);
        newGuestbtn = (TextView) findViewById(R.id.new_guest_btn);

        impContactsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,ImpContactsActivity.class));
            }
        });

        newGuestbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,NewGuestActivity.class));
            }
        });
    }
}
