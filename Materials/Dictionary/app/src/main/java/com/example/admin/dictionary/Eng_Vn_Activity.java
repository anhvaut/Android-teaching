package com.example.admin.dictionary;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Eng_Vn_Activity extends AppCompatActivity {

    private EditText edtSearch;
    private ListView lvWord;

    private ArrayList<String> listOfWords;
    private ArrayAdapter<String> adapterOfWords;

    private ArrayList<String> dataOfWords;
    private DatabaseAccess dbAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eng__vn_);

        //Khởi tạo các View item cần dùng
        edtSearch = findViewById(R.id.edt_search);
        lvWord = findViewById(R.id.lv_word);
        listOfWords = new ArrayList<String>();



        //Khởi tạo adapter, đổ dữ liệu từ danh sách qua listView thông qua adapter
        adapterOfWords = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,listOfWords);
        lvWord.setAdapter(adapterOfWords);

        loadWordFromDatabase();
        addDataToList();

        //Lắng nghe sự kiện khi click vào một từ trên Listview
        lvWord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedWord = lvWord.getItemAtPosition(position).toString();
                //Gọi màn hình định nghĩa từ
                Intent intent = new Intent(Eng_Vn_Activity.this,DefinitionActivity.class);
                //Đóng gói giá trị word và gởi qua màn hình kia
                intent.putExtra("word",selectedWord);

                startActivity(intent);
            }
        });


    }
    public void loadWordFromDatabase(){
        //Mở file cơ sở dữ liệu đọc và load vào mảng
        dbAccess = DatabaseAccess.getInstance(this);
        dbAccess.open();
        dataOfWords = dbAccess.getWords("a");
        dbAccess.close();
    }

    public void addDataToList(){

        for(String word:dataOfWords){
            listOfWords.add(word);
        }
        adapterOfWords.notifyDataSetChanged();
    }

    public void search(){
        //lấy từ cần search
        String textSearch = edtSearch.getText().toString();
        //search trong dữ liệu
        dbAccess.open();
        dataOfWords = dbAccess.getWords(textSearch);
        dbAccess.close();

        listOfWords.clear();
        adapterOfWords.clear();
        //cập nhập dữ liệu mới search lên lại listview
        addDataToList();

    }

    public void clickOnSearch(View v){
        search();
    }


}
