package com.example.attendo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShowAttendanceStudent2 extends AppCompatActivity {

    ListView listView;
    String sid, teacher_id,classname,message;
    Spinner subject;
    Button btViewBySub;


    EditText date;
    ArrayList Userlist = new ArrayList<>();
    ArrayList Studentlist = new ArrayList<>();

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dbAttendance;
    DatabaseReference dbStudent;
    String required_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_attendance_student2);
//        listView = (ListView) findViewById(R.id.list);
//        subject = (Spinner)findViewById(R.id.spinnerSubject);
//        btViewBySub = (Button)findViewById(R.id.btViewBySubject);

//        date = (EditText) findViewById(R.id.date);
        Bundle bundle1 = getIntent().getExtras();
        sid = bundle1.getString("sid");

        Intent intent = getIntent();
        classname = intent.getStringExtra("className");
     //   Toast.makeText(this, "Classname Declared"+classname, Toast.LENGTH_SHORT).show();


        Intent intent2 =  new Intent(ShowAttendanceStudent2.this,student_attendance_sheet.class);
        intent.putExtra("classname",classname);
        intent.putExtra("sid",sid);
        startActivity(intent2);

//
//        btViewBySub.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//            }
//        });

    }







    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}

