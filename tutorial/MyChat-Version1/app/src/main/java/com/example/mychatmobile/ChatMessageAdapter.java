package com.example.mychatmobile;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ChatMessageAdapter extends ArrayAdapter<ChatMessage> {

    private int resourceLayout;
    private Context mContext;

    public ChatMessageAdapter(Context context, int resource, List<ChatMessage> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        ChatMessage p = getItem(position);

        if (p != null) {
            TextView tvMessageUser = (TextView) v.findViewById(R.id.message_user);
            TextView tvMessageTime = (TextView) v.findViewById(R.id.message_time);
            TextView tvMessageText = (TextView) v.findViewById(R.id.message_text);

            if (tvMessageUser!= null) {
                tvMessageUser.setText(p.getMessageUser());
            }

            if (tvMessageTime  != null) {
                tvMessageTime.setText("" + DateFormat.format("dd-MM-yyyy (HH:mm:ss)", p.getMessageTime()));
            }

            if (tvMessageText != null) {
                tvMessageText.setText(p.getMessageText());
            }
        }

        return v;
    }

}
