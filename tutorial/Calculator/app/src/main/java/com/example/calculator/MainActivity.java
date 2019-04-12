package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edt;
    ListView lvResult;
    double a = Double.NaN;
    double b = Double.NaN;
    double c = Double.NaN;

    private boolean mAddition, mSubtraction, mMultiply, mDivision;

    ArrayList<String> arrayResults; //store list of results
    ArrayAdapter<String> arrayAdapter; //adapter to map result to list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt = (EditText) findViewById(R.id.edt_input);
        lvResult = (ListView) findViewById(R.id.lv_history);
        arrayResults = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                arrayResults);

        lvResult.setAdapter(arrayAdapter);
    }

//    double add(double a, double b) {
//        return a + b;
//    }
//
//    double sub(double a, double b) {
//        return a - b;
//    }
//
//    double multiply(double a, double b){
//        return a * b;
//    }
//
//    double divide(double a, double b) {
//        return a / b;
//    }

    double plus_minus(double a) {
        return a * (-1);
    }

    double sqrt(double a) {
        return Math.sqrt(a);
    }

    double power(double a) {
        return Math.pow(a, 2);
    }

    double reciprocal(double a) {
        return (1 / a);
    }

    double percent(double a) {
        return (a / 100);
    }
    

    public void onClick(View v) {
        Toast toast = Toast.makeText(MainActivity.this, "Re-enter", Toast.LENGTH_SHORT);
        try {
            switch (v.getId()) {
                case R.id.btn_0:
                    edt.append("0");
                    break;

                case R.id.btn_1:
                    edt.append("1");
                    break;

                case R.id.btn_2:
                    edt.append("2");
                    break;

                case R.id.btn_3:
                    edt.append("3");
                    break;

                case R.id.btn_4:
                    edt.append("4");
                    break;

                case R.id.btn_5:
                    edt.append("5");
                    break;

                case R.id.btn_6:
                    edt.append("6");
                    break;

                case R.id.btn_7:
                    edt.append("7");
                    break;

                case R.id.btn_8:
                    edt.append("8");
                    break;

                case R.id.btn_9:
                    edt.append("9");
                    break;

                case R.id.btn_dot:
                    edt.append(".");
                    break;

                case R.id.btn_back:
                    StringBuilder str = new StringBuilder(edt.getText().toString());

                    if (str.length() > 0) {
                        str = str.deleteCharAt(str.length() - 1);
                        edt.setText(str);
                        break;
                    } else {
                        break;
                    }
            }
        }
        catch(NumberFormatException e){
            toast.show();
        }
    }

    public void doClear(View v) {
        arrayResults.clear();
        arrayAdapter.notifyDataSetChanged();

        a = Double.NaN;
        b = Double.NaN;
        c = Double.NaN;

        edt.getText().clear();
    }

    public void doClearEnter(View v) {
        edt.getText().clear();
    }

    public void doCalculate(View v) {
        double number = 0;

        if (mAddition == true) {

            if(!Double.isNaN(a)) {
                c = Double.parseDouble(edt.getText().toString());
                number = a + c;
            }

            String stringResult = Double.toString((number));
            arrayResults.add(0,stringResult);
            arrayAdapter.notifyDataSetChanged();
            edt.setText(stringResult);

            mAddition = false;

            a = Double.NaN;
            b = Double.NaN;
            c = Double.NaN;
        }

        if (mSubtraction == true) {

            if(!Double.isNaN(a)) {
                c = Double.parseDouble(edt.getText().toString());
                number = a - c;
            }

            String stringResult = Double.toString((number));
            arrayResults.add(0,stringResult);
            arrayAdapter.notifyDataSetChanged();
            edt.setText(stringResult);

            mSubtraction = false;

            a = Double.NaN;
            b = Double.NaN;
            c = Double.NaN;
        }

        if (mMultiply == true) {

            if(!Double.isNaN(a)) {
                c = Double.parseDouble(edt.getText().toString());
                number = a * c;
            }

            String stringResult = Double.toString((number));
            arrayResults.add(0,stringResult);
            arrayAdapter.notifyDataSetChanged();
            edt.setText(stringResult);

            mMultiply = false;

            a = Double.NaN;
            b = Double.NaN;
            c = Double.NaN;
        }

        if (mDivision == true) {

            if(!Double.isNaN(a)) {
                c = Double.parseDouble(edt.getText().toString());
                number = a / c;
            }

            String stringResult = Double.toString((number));
            arrayResults.add(0,stringResult);
            arrayAdapter.notifyDataSetChanged();
            edt.setText(stringResult);

            mDivision = false;

            a = Double.NaN;
            b = Double.NaN;
            c = Double.NaN;
        }
    }

    public void doAddition(View v) {
        mAddition = true;

        if(!Double.isNaN(a)) {
            b = Double.parseDouble(edt.getText().toString());
            a = a + b;
        } else {
            a = Double.parseDouble(edt.getText().toString());
        }

        String stringResult = a + " + ";
        arrayResults.add(0,stringResult);
        arrayAdapter.notifyDataSetChanged();

        edt.getText().clear();
    }

    public void doSubtraction(View v) {
        mSubtraction = true;

        if(!Double.isNaN(a)) {
            b = Double.parseDouble(edt.getText().toString());
            a = a - b;
        } else {
            a = Double.parseDouble(edt.getText().toString());
        }

        String stringResult = a + " - ";
        arrayResults.add(0,stringResult);
        arrayAdapter.notifyDataSetChanged();

        edt.getText().clear();

    }

    public void doMultiply(View v) {
        mMultiply = true;

        if(!Double.isNaN(a)) {
            b = Double.parseDouble(edt.getText().toString());
            a = a * b;
        } else {
            a = Double.parseDouble(edt.getText().toString());
        }

        String stringResult = a + " * ";
        arrayResults.add(0,stringResult);
        arrayAdapter.notifyDataSetChanged();

        edt.getText().clear();

    }

    public void doDivision(View v) {
        mDivision = true;

        if(!Double.isNaN(a)) {
            b = Double.parseDouble(edt.getText().toString());
            a = a / b;
        } else {
            a = Double.parseDouble(edt.getText().toString());
        }

        String stringResult = a + " / ";
        arrayResults.add(0,stringResult);
        arrayAdapter.notifyDataSetChanged();

        edt.getText().clear();

    }

    public void doPlusMinus(View v) {
        a = Double.parseDouble(edt.getText().toString());

        String stringResult = "" + plus_minus(a);
        arrayResults.add(0,stringResult);
        arrayAdapter.notifyDataSetChanged();

        edt.setText(Double.toString(plus_minus(a)));
        a = plus_minus(a);
    }

    public void doSquareRoot(View v) {
        a = Double.parseDouble(edt.getText().toString());

        String stringResult = "" + sqrt(a);
        arrayResults.add(0,stringResult);
        arrayAdapter.notifyDataSetChanged();

        edt.setText(Double.toString(sqrt(a)));
        a = sqrt(a);
    }

    public void doPower(View v) {
        a = Double.parseDouble(edt.getText().toString());

        String stringResult = "" + power(a);
        arrayResults.add(0,stringResult);
        arrayAdapter.notifyDataSetChanged();

        edt.setText(Double.toString(power(a)));
        a = power(a);
    }

    public void doReciprocal(View v) {
        a = Double.parseDouble((edt.getText().toString()));

        String stringResult = "" + reciprocal(a);
        arrayResults.add(0,stringResult);
        arrayAdapter.notifyDataSetChanged();

        edt.setText(Double.toString(reciprocal(a)));
        a = reciprocal(a);
    }

    public void doPercent(View v) {
        a = Double.parseDouble((edt.getText().toString()));

        String stringResult = "" + percent(a);
        arrayResults.add(0,stringResult);
        arrayAdapter.notifyDataSetChanged();

        edt.setText(Double.toString(percent(a)));
        a = percent(a);
    }

    public void doReset(View v) {
        arrayResults.removeAll(arrayResults);
        arrayAdapter.notifyDataSetChanged();

        if (arrayResults.isEmpty()) {
            arrayResults.add("There's nothing in history yet");
            arrayAdapter.notifyDataSetChanged();
        }
    }

}
