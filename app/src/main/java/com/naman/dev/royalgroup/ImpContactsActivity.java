package com.naman.dev.royalgroup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.naman.dev.royalgroup.Adapters.ContactAdapter;
import com.naman.dev.royalgroup.Models.Contact;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ImpContactsActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ArrayList<Contact> contactsArray;
    private RecyclerView recyclerView;
    private ContactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imp_contacts);
        // get name and number directly from firebase
        contactsArray = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        recyclerView = (RecyclerView) findViewById(R.id.imp_contacts_recyclerView);
        mAdapter = new ContactAdapter(this,contactsArray);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.child("contacts").getChildren()) {
                    String name = d.getKey(), number;
                    if (d.getValue() != null) {
                        number = d.getValue(Long.class).toString();
                    } else
                        number = "99999999";
                    Log.e("qwerty", name + number);
                    contactsArray.add(new Contact(name, number));
                    recyclerView.setAdapter(mAdapter);
                    Toast.makeText(ImpContactsActivity.this, "Done", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    private void writeToSharedPrefs() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(contactsArray);

        editor.putString("ImpContacts", json);
        editor.commit();
    }

    private void readFromSharedPrefs() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("ImpContacts", null);
        Type type = new TypeToken<ArrayList<Contact>>() {
        }.getType();
        ArrayList<Contact> arrayList = gson.fromJson(json, type);
    }
}
