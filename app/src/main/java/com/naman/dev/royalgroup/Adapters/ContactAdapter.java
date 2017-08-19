package com.naman.dev.royalgroup.Adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.naman.dev.royalgroup.Models.Contact;
import com.naman.dev.royalgroup.R;

import java.util.ArrayList;

/**
 * Created by naman on 8/19/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private ArrayList<Contact> contactsArray;
    private Context context;

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);

        return new ContactViewHolder(itemView);
    }

    public ContactAdapter(Context context,ArrayList<Contact> arrayList) {
        this.context = context;
        this.contactsArray = arrayList;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        final Contact contact = contactsArray.get(position);
        holder.name.setText(contact.name);
        holder.number.setText(contact.number);
        holder.callImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact.number));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (contactsArray == null)
            return 0;
        return contactsArray.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        public TextView name, number;
        public ImageView callImage;

        public ContactViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.contact_name);
            number = (TextView) view.findViewById(R.id.contact_number);
            callImage = (ImageView) view.findViewById(R.id.contact_call_icon);
        }
    }
}
