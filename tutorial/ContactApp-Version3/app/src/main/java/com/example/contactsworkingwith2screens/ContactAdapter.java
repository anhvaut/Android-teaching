package com.example.contactsworkingwith2screens;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{
    private ArrayList<Contact> mContacts;
    private Context mContext;
    private LayoutInflater mInflater;

    public ContactAdapter(ArrayList<Contact> mContacts, Context mContext) {
        this.mContacts = mContacts;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.item_contact,
                                            viewGroup, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ContactViewHolder holder, int i) {
        Contact contact = mContacts.get(i);
        holder.tvFullname.setText(contact.getmFullname());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView tvFullname;

        public ContactViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            tvFullname = (TextView) itemView.findViewById(R.id.tv_fullname);
        }
    }
}
