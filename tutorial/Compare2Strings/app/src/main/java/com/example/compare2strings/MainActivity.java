package com.example.compare2strings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    EditText edt1, edt2;
    ListView lvResult;
    String stringA, stringB;
    ArrayList<String> arrayResults; //store list of results
    ArrayAdapter<String> arrayAdapter; //adapter to map result to list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1 = (EditText) findViewById(R.id.edt_st1);
        edt2 = (EditText) findViewById(R.id.edt_st2);
        lvResult = (ListView) findViewById(R.id.lv_result);

        arrayResults = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                arrayResults);

        lvResult.setAdapter(arrayAdapter);
    }

    public int compare(String a, String b) {
        a = edt1.getText().toString();
        b = edt2.getText().toString();

        if (a.length() > b.length()) {
            return 1;
        }

        else if (a.length() == b.length()) {
            return 0;
        }

        else {
            return -1;
        }
    }


    public void doCompare(View v) {
        stringA = edt1.getText().toString();
        stringB = edt2.getText().toString();

        String stringResult = "String 1: " + stringA + "\n" + "String 2: " + stringB;
        if(compare(stringA, stringB) > 0) {
            stringResult += "\n" + "String 1" + " > " + "String 2";
        }

        else if (compare(stringA, stringB) == 0) {
            stringResult += "\n" + "String 1" + " = " + "String 2";
        }

        else {
            stringResult += "\n" + "String 1" + " < " + "String 2";
        }

        arrayResults.add(0,stringResult);
        arrayAdapter.notifyDataSetChanged();
    }

    public void doCount(View v) {
        stringA = edt1.getText().toString();
        stringB = edt2.getText().toString();
        StringTokenizer strTokA = new StringTokenizer(stringA, " ");
        StringTokenizer strTokB = new StringTokenizer(stringB, " ");
        ArrayList<String> results = new ArrayList<>();

        while (strTokA.hasMoreElements()) {
            String wordA = strTokA.nextElement().toString();

            strTokB = new StringTokenizer(stringB, " ");

            while(strTokB.hasMoreElements()) {
                String wordB = strTokB.nextElement().toString();
                if (wordB.equals(wordA)) {
                    results.add(wordB);
                }
            }
        }

        arrayResults.add(0, " " + "There are " + results.size() + " common words");
        arrayAdapter.notifyDataSetChanged();
    }
}
