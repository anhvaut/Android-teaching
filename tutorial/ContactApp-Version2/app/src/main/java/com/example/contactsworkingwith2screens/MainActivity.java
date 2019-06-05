package com.example.contactsworkingwith2screens;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contact> listObjectContacts;
    ContactAdapter adapter;
    FloatingActionButton mBtnAdd;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnAdd = (FloatingActionButton) findViewById(R.id.fab);

        listObjectContacts = new ArrayList<Contact>();
        recyclerView = (RecyclerView) findViewById(R.id.rv_contacts);
        recyclerView.setHasFixedSize(true);

        adapter = new ContactAdapter(listObjectContacts, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration itemDecor = new DividerItemDecoration(MainActivity.this,
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecor);



        //Them
        Contact contact1 = new Contact();
        contact1.setmId(1);
        contact1.setmFullname("Quan");
        contact1.setmTitle("Mr");
        contact1.setmCompany("abc");
        contact1.setmEmail("a@gmail.com");
        contact1.setmMobile("094522555");
        listObjectContacts.add(contact1);

        Contact contact2 = new Contact();
        contact1.setmId(2);
        contact2.setmFullname("Huong");
        contact2.setmTitle("Ms");
        contact2.setmCompany("def");
        contact2.setmEmail("b@gmail.com");
        contact2.setmMobile("023111982");
        listObjectContacts.add(contact2);

        adapter.notifyDataSetChanged();

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewContactsActivity.class);

                //Chay qua class AddNewContactsActivity voi ma la  123, de sau ve minh check ma nay
                // 123 la so tu dat
                // Du lieu tra ve se don trong ham onActivityResult  ben duoi
                startActivityForResult(intent, 123);
            }
        });

        //Su kien click tren moi Item
        recyclerView.addOnItemTouchListener(new RecycleTouchListener(this, recyclerView,
                new RecycleTouchListener.OnItemClickListner() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //Khi click vao moi item chuyen qua man hinh Edit Contact
                        Intent intent = new Intent(getBaseContext(), EditContactsActivity.class);


                        //Dung Bunddle de dong goi du lieu va goi di
                        //Truyen du lieu qua lai giong nhu goi thu buu dien
                        Contact getContact = listObjectContacts.get(position);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("contact", getContact);
                        intent.putExtra("package", bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                        //Khi bam vao giu thi xoa du lieu di
                        Contact getContact = listObjectContacts.get(position);

                        listObjectContacts.remove(position);
                        Toast.makeText(MainActivity.this, "Deleted",
                                Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                }));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK) {

            //Don du lieu tra ve la 123 va add vao list danh sach
            Contact contact = (Contact) data.getExtras().getSerializable("RETURN");
            listObjectContacts.add(contact);
            adapter.notifyDataSetChanged();
        }
    }

}
