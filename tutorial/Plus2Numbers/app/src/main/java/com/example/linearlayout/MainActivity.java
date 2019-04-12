package com.example.linearlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtA, edtB;
    ListView lvResult;
    int a, b;
    ArrayList<String> arrayResults; //store list of results
    ArrayAdapter<String> arrayAdapter; //adapter to map result to list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtA = (EditText) findViewById(R.id.edt_a);
        edtB = (EditText) findViewById(R.id.edt_b);
        lvResult = (ListView) findViewById(R.id.lv_result);

        arrayResults = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                arrayResults);

        lvResult.setAdapter(arrayAdapter);
    }

    int add(int a, int b) {
        return a + b;
    }

    int sub(int a, int b) {
        return a - b;
    }

    int multiply(int a, int b){
        return a * b;
    }

    float divide(float a, float b) {
        return a / b;
    }

    public void doAddition(View v) {
        a = Integer.parseInt(edtA.getText().toString());
        b = Integer.parseInt(edtB.getText().toString());

        String stringResult = a + " + " + b + " = " + add(a, b);
        arrayResults.add(0,stringResult);
        arrayAdapter.notifyDataSetChanged();
    }

    public void doSubtraction(View v) {
        a = Integer.parseInt(edtA.getText().toString());
        b = Integer.parseInt(edtB.getText().toString());

        String stringResult = a + " - " + b + " = " + sub(a, b);
        arrayResults.add(0,stringResult);
        arrayAdapter.notifyDataSetChanged();
    }

    public void doMultiply(View v) {
        a = Integer.parseInt(edtA.getText().toString());
        b = Integer.parseInt(edtB.getText().toString());

        String stringResult = a + " * " + b + " = " + multiply(a, b);
        arrayResults.add(0,stringResult);
        arrayAdapter.notifyDataSetChanged();
    }

    public void doDivision(View v) {
        a = Integer.parseInt(edtA.getText().toString());
        b = Integer.parseInt(edtB.getText().toString());

        String stringResult = a + " / " + b + " = " + divide(a, b);
        arrayResults.add(0,stringResult);
        arrayAdapter.notifyDataSetChanged();
    }
}
