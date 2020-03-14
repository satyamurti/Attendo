package com.example.attendo2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendo2.adapters.CustomListAdapterForEachSub;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Collections;

public class student_attendance_sheet extends AppCompatActivity {
  public static int count,P,A;
    float average= (float) 0.0;
    TextView t,tvPresent,tvAttendance;
    String avg,p1,p2,p3,p4;
    String student_id,teacher_id,classname;
    ArrayList<attendance_of_each_subject> dates = new ArrayList<>();
    private CustomListAdapterForEachSub customListAdapter;
    ArrayList subjectsArray = new ArrayList<>();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dbAttendance,dbsubjects;
    ListView listView;
    Button GetAtd;
    Spinner Subject;
    customLoadingBar customLoadingBar = new customLoadingBar(student_attendance_sheet.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance_sheet);




        t = (TextView) findViewById(R.id.textView3);
        tvPresent = findViewById(R.id.tvPresent);
        tvAttendance = findViewById(R.id.tvAttendance);
        GetAtd = findViewById(R.id.btGetAtd);
       // Subject = findViewById(R.id.spinnerSubject2);
        final MaterialSpinner Subject = (MaterialSpinner) findViewById(R.id.spinnerSubject2);
        customListAdapter= new CustomListAdapterForEachSub(dates,this);

        listView = (ListView) findViewById(R.id.list3);


        Intent intent = getIntent();
        classname = intent.getStringExtra("classname");

        student_id = intent.getStringExtra("sid");

        Toast.makeText(student_attendance_sheet.this, classname, Toast.LENGTH_SHORT).show();

        t.setText(student_id);
        dbsubjects = ref.child("Teacher").child("Subjects");
        dbsubjects.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()){

                    subjectsArray.add(dsp.child("tid").getValue().toString());
                   // Subject.setItems(dsp.child("tid").getValue().toString());
                }

                Toast.makeText(student_attendance_sheet.this, "Subjects Added Bidu ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(student_attendance_sheet.this, "Kuch to Gadabd Hai Bidu", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, subjectsArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Subject.setAdapter(adapter);
        Subject.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                teacher_id = item.toString();
            }
        });






        GetAtd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dates.clear();


               customLoadingBar.startLoader();


                count = 0;
                P = 0;
                A = 0;


              //  teacher_id = Subject.getSelectedItem().toString();

                if (!(teacher_id.equals("Select Subject"))) {

//
//                dates.clear();
//                dates.add("       Date          " + "Attendance");

                Toast.makeText(student_attendance_sheet.this, teacher_id, Toast.LENGTH_SHORT).show();


                dbAttendance = ref.child("attendance");
                dbAttendance.addListenerForSingleValueEvent(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            p1 = dsp.child(classname).child(teacher_id).child(student_id).child("p1").getValue().toString();


                           // dates.add(dsp.getKey() + "    " + p1);
                            // result add karat aahe into array list


                            //  Toast.makeText(getApplicationContext(),dsp.child(student_id).child("p1").getValue().toString(),Toast.LENGTH_LONG).show();
                            if (p1.equals("P")) {
                                dates.add(new attendance_of_each_subject(dsp.getKey(),p1));

                                P = P +1;
                                count++;
                            }
                            if (p1.equals("A")) {
                                dates.add(new attendance_of_each_subject(dsp.getKey(),p1));
                                A = A+1;
                                count++;
                            }


                            average = ((float) P / count) * 100;



                        }
                       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//                        Collections.sort(dates, Comparator.comparing(attendance_of_each_subject :: getDates).
//                                thenComparing(attendance_of_each_subject :: getPresenty));


                        Collections.sort(dates);
                        list(dates);


                        // Toast.makeText(getApplicationContext(), dates.toString(), Toast.LENGTH_LONG).show();


                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
                    }
                });


            }


                else {
                   customLoadingBar.dismissLoader();
                    Toast.makeText(student_attendance_sheet.this, "Plz Select Subject", Toast.LENGTH_SHORT).show();
                }
        }
        });

    }
    public void list(ArrayList studentlist){
       //Toast.makeText(this,NOP+TOC,Toast.LENGTH_LONG).show();
       customLoadingBar.dismissLoader();

        tvPresent.setText("Present = "+String.valueOf(P) );
        tvAttendance.setText(String.valueOf("Your Attendance is "+average+ "%"));

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, studentlist);
//        listView.setAdapter(adapter);

        customListAdapter=new CustomListAdapterForEachSub(studentlist,this);
        listView.setAdapter(customListAdapter);
    }

    public void getAllSub(View view) {

  Intent intent = new Intent(this,KafleSubjects.class);

  intent.putExtra("sid", student_id);
  intent.putExtra("classname", classname);
  startActivity(intent);





    }
}
