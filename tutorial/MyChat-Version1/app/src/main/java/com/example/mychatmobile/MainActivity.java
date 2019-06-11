package com.example.mychatmobile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ArrayList<ChatMessage> arrayList;
    private ChatMessageAdapter arrayAdapter;
    private DatabaseReference mReference;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FirebaseApp.initializeApp(this);
        auth=FirebaseAuth.getInstance();
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
//        Log.d("debug1", "User " + auth.getUid());

        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText input=(EditText)findViewById(R.id.input);


                FirebaseDatabase.getInstance()
                        .getReference()
                        .push()
                        .setValue(new ChatMessage(input.getText().toString(),auth.getCurrentUser().getDisplayName()));
                input.setText("");
            }
        });


        ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);

        arrayList = new ArrayList<ChatMessage>();
        arrayAdapter = new ChatMessageAdapter(this,R.layout.message, arrayList);
        listOfMessages.setAdapter(arrayAdapter);

        mReference = FirebaseDatabase.getInstance().getReference();

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i = 0;
                for(DataSnapshot item : dataSnapshot.getChildren()) {
                    ChatMessage data = item.getValue(ChatMessage.class);

                    if (i >= arrayList.size()) {
                        arrayList.add(data);
                    }

                    i++;
                }

                arrayAdapter.notifyDataSetChanged();

                Log.d("CHAT","SUCCESS!");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("CHAT","ERROR: " + databaseError.getMessage());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_sign_out) {
            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(MainActivity.this,
                                    "You have been signed out.",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // Close activity
                            finish();
                        }
                    });
        }
        return true;
    }
}
