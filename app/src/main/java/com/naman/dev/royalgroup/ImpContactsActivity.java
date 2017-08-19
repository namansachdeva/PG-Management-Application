package com.naman.dev.royalgroup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.naman.dev.royalgroup.Models.Contact;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ImpContactsActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ArrayList<Contact> contactsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imp_contacts);
        // get name and number directly from firebase

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("contacts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contactsArray.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    contactsArray.add(new Contact(d.getKey(), d.getValue().toString()));
                }
                writeToSharedPrefs();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        readFromSharedPrefs();

    }

    private void writeToSharedPrefs() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(contactsArray);

        editor.putString("ImpContactsActivity", json);
        editor.commit();
    }

    private void readFromSharedPrefs() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("ImpContactsActivity", null);
        Type type = new TypeToken<ArrayList<Contact>>() {
        }.getType();
        ArrayList<Contact> arrayList = gson.fromJson(json, type);
    }
}
