package com.quangtd.middletest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText mEtName;
    private EditText mEtAge;
    private EditText mEtAddress;

    private RecyclerView mRvStudent;
    private List<Student> mStudents;
    private StudentAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        mStudents = new ArrayList<>();
        mAdapter = new StudentAdapter(this, mStudents);
        mRvStudent.setAdapter(mAdapter);
        mRvStudent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    private void findView() {
        mEtName = (EditText) findViewById(R.id.edName);
        mEtAge = (EditText) findViewById(R.id.edAge);
        mEtAddress = (EditText) findViewById(R.id.edAddress);
        mRvStudent = (RecyclerView) findViewById(R.id.rvInfor);
    }

    public void clickButtonPlus(View view) {
        String name = mEtName.getText().toString();
        String age_s = mEtAge.getText().toString();
        String address = mEtAddress.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, "Nhập chưa đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            mEtName.setError("Tên ko hợp lệ");
            return;
        }
        if (age_s.isEmpty()) {
            Toast.makeText(this, "Nhập chưa đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            mEtAge.setError("Tuổi không hợp lệ");
            return;
        }
        if (address.isEmpty()) {
            Toast.makeText(this, "Nhập chưa đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            mEtAddress.setError("Địa chỉ không hợp lệ");
            return;
        }
        try {
            int age_i = Integer.parseInt(age_s);
            if (age_i < 1 || age_i > 90) {
                Toast.makeText(this, "Tuổi không hợp lệ", Toast.LENGTH_SHORT).show();
                mEtAge.setError("Tuổi không hợp lệ");
                return;
            }
            Student student = new Student(name, age_i, address);
            mStudents.add(student);
            mAdapter.notifyItemInserted(mStudents.size() - 1);
            mRvStudent.scrollToPosition(mStudents.size() - 1);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, "Tuổi sai định dạng", Toast.LENGTH_SHORT).show();
        }

    }
}
