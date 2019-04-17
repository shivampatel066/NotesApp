package com.shivam.notes2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import java.util.HashSet;

import static com.shivam.notes2.MainActivity.notes;
import static com.shivam.notes2.MainActivity.set;

public class EditNote extends AppCompatActivity implements TextWatcher {

    int noteId;
    boolean deleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText editText = findViewById(R.id.editText);

        Intent i = getIntent();

        noteId = i.getIntExtra("noteId",-1);

        if(noteId != -1) {

            String fillerText = notes.get(noteId);
            editText.setText(fillerText);

        }

        editText.addTextChangedListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {



    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

            notes.set(noteId, String.valueOf(s));
            MainActivity.arrayAdapter.notifyDataSetChanged();

            SharedPreferences sharedPreferences = this.getSharedPreferences("com.shivam.notes2", Context.MODE_PRIVATE);

            if(set == null) {

                set = new HashSet<>();


            } else {
                set.clear();


            }
            set.clear();


            set.addAll(notes);
            sharedPreferences.edit().remove("notes").apply();
            sharedPreferences.edit().putStringSet("notes",set).apply();








    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}
