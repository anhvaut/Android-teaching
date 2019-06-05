package com.example.midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText mEdtFullname,mEdtAge, mEdtAddress;
    Button mBtnAdd;
    ListView lvResult;
    ArrayList<String> arrayResults;
    ArrayAdapter<String> adapter;
    String age, name, address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdtFullname =(EditText)findViewById(R.id.edt_fullname);
        mEdtAddress = (EditText) findViewById(R.id.edt_address);
        mEdtAge = (EditText) findViewById(R.id.edt_age);
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        lvResult = (ListView) findViewById(R.id.lv_result);
        arrayResults = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayResults);
        lvResult.setAdapter(adapter);

    }

    public void add(View v){
        name = mEdtFullname.getText().toString();
        address = mEdtAddress.getText().toString();
        age = mEdtAge.getText().toString();
        adapter.notifyDataSetChanged();
        ArrayList<String> result = new ArrayList<>();
        adapter.notifyDataSetChanged();
        mEdtAddress.setText(null);
        mEdtAge.setText(null);
        mEdtFullname.setText(null);

        String stringResult = "name: " + name + "\n" + "Age: " + age + "\n" + "Address: " + address;
        arrayResults.add(0, stringResult);
        adapter.notifyDataSetChanged();


        arrayResults = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayResults);

    }
}
