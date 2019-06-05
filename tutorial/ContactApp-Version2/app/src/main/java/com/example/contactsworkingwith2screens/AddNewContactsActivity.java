package com.example.contactsworkingwith2screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNewContactsActivity extends AppCompatActivity {

    private EditText mEdtFullname, mEdtCompany, mEdtTitle, mEdtMobile, mEdtEmail;
    private TextView mTvCreatedAt;
    private de.hdodenhof.circleimageview.CircleImageView mIvAvatar;
    private Button mBtnSave, mBtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contacts);


        mEdtFullname = (EditText) findViewById(R.id.edt_fullname);
        mEdtCompany = (EditText) findViewById(R.id.edt_company);
        mEdtTitle = (EditText) findViewById(R.id.edt_title);
        mEdtMobile = (EditText) findViewById(R.id.edt_mobile);
        mEdtEmail = (EditText) findViewById(R.id.edt_email);
        mBtnSave = (Button) findViewById(R.id.btn_save);
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);

        mIvAvatar = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.iv_avatar);
        mTvCreatedAt = (TextView) findViewById(R.id.tv_created_at);

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Khi click nut save thi mang du lieu ve lai ben main
                //Du lieu nay se chay vao ham onActivityResult
                //Ma tro ve la Return
                Contact contact = addNewContact();
                Intent intent = new Intent();

                //dong du lieu trong buddle de dem het ve
                Bundle bundle = new Bundle();
                bundle.putSerializable("RETURN", contact);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);

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

    public Contact addNewContact() {

        Contact newContact = new Contact();

        newContact.setmFullname(mEdtFullname.getText().toString());
        newContact.setmCompany(mEdtCompany.getText().toString());
        newContact.setmEmail(mEdtEmail.getText().toString());
        newContact.setmMobile(mEdtMobile.getText().toString());
        newContact.setmTitle(mEdtTitle.getText().toString());

        String dateInString = new SimpleDateFormat("yyyy-mm-dd",
                Locale.getDefault()).format(new Date());
        newContact.setmCreatedAt(dateInString);


        return newContact;
    }


}
