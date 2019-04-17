package com.example.contactsworkingwith2screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



public class EditContactsActivity extends AppCompatActivity{

    private EditText mEdtFullname, mEdtCompany, mEdtTitle, mEdtMobile, mEdtEmail;
    private TextView mTvCreatedAt;
    private de.hdodenhof.circleimageview.CircleImageView mIvAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contacts);

        mEdtFullname = (EditText) findViewById(R.id.edt_fullname);
        mEdtCompany = (EditText) findViewById(R.id.edt_company);
        mEdtTitle = (EditText) findViewById(R.id.edt_title);
        mEdtMobile = (EditText) findViewById(R.id.edt_mobile);
        mEdtEmail = (EditText) findViewById(R.id.edt_email);

        mIvAvatar = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.iv_avatar);
        mTvCreatedAt = (TextView) findViewById(R.id.tv_created_at);

        Intent intent = getIntent();
        Bundle extra  = intent.getBundleExtra("package");
//        Log.d("quan", extra+" --extra");

        Contact contact = (Contact)extra.getSerializable("contact");
//        Log.d("quan", contact+" --contact");


        mEdtFullname.setText(contact.getmFullname());
        mEdtCompany.setText(contact.getmCompany());
        mEdtTitle.setText(contact.getmTitle());
        mEdtMobile.setText(contact.getmMobile());
        mEdtEmail.setText(contact.getmEmail());
//        mTvCreatedAt.setText(contact.getmCreatedAt());

    }
}
