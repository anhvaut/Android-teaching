package com.example.contactsworkingwith2screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



public class EditContactsActivity extends AppCompatActivity{

    private EditText mEdtFullname, mEdtCompany, mEdtTitle, mEdtMobile, mEdtEmail;
    private TextView mTvCreatedAt;
    private de.hdodenhof.circleimageview.CircleImageView mIvAvatar;
    private Button mBtnSave, mBtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contacts);

        mEdtFullname = (EditText) findViewById(R.id.edt_fullname);
        mEdtCompany = (EditText) findViewById(R.id.edt_company);
        mEdtTitle = (EditText) findViewById(R.id.edt_title);
        mEdtMobile = (EditText) findViewById(R.id.edt_mobile);
        mEdtEmail = (EditText) findViewById(R.id.edt_email);
        mBtnSave = (Button) findViewById(R.id.btn_save);
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);
        mIvAvatar = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.iv_avatar);
        mTvCreatedAt = (TextView) findViewById(R.id.tv_created_at);

        //Lay du lieu dem tu ben Ham Main Qua va hien thi ben man hinh Edit nay
        Intent intent = getIntent();
        Bundle extra  = intent.getBundleExtra("package");
        Contact contact = (Contact)extra.getSerializable("contact");


        //Hien thi du lieu len cac truong tuong ung
        mEdtFullname.setText(contact.getmFullname());
        mEdtCompany.setText(contact.getmCompany());
        mEdtTitle.setText(contact.getmTitle());
        mEdtMobile.setText(contact.getmMobile());
        mEdtEmail.setText(contact.getmEmail());

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Viet code de dem du lieu thay doi ve lai
                finish();
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
