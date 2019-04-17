package com.example.contactsworkingwith2screens;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private MyDatabase db;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MyDatabase(this);

        mBtnAdd = (FloatingActionButton) findViewById(R.id.fab);

        listObjectContacts = new ArrayList<Contact>();
        getData();
//        Contact contact = new Contact();
//        contact.setmFullname("Quan");
//        contact.setmTitle("Mr");
//        contact.setmCompany("abc");
//        listObjectContacts.add(contact);


        setReyclerVIew();
        onClickAddBtn();
//        setOnClickOnItem();

        DividerItemDecoration itemDecor = new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecor);

        adapter.notifyDataSetChanged();

    }

    public Contact findContact(String name) {
        for (Contact contact:listObjectContacts) {
            if (name.equals(contact.getmFullname())) {
                return contact;
            }
        }

        return null;
    }

    public void setReyclerVIew() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_contacts);
        recyclerView.setHasFixedSize(true);

        adapter = new ContactAdapter(listObjectContacts, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecycleTouchListener(this, recyclerView,
                new RecycleTouchListener.OnItemClickListner() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getBaseContext(), EditContactsActivity.class);
                        Contact getContact = listObjectContacts.get(position);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("contact", getContact);
                        intent.putExtra("package", bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Contact getContact = listObjectContacts.get(position);
                        db.deleteContact(getContact);
                        listObjectContacts.remove(position);
                        Toast.makeText(MainActivity.this, "Deleted",
                                    Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                }));
    }


    public void onClickAddBtn() {
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewContactsActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Contact contact = (Contact) data.getExtras().getSerializable("RETURN");
            listObjectContacts.add(contact);
            adapter.notifyDataSetChanged();
        }
    }

    public void getData() {
        listObjectContacts.clear();
        listObjectContacts = db.getAllContacts();
        db.close();
    }
}
