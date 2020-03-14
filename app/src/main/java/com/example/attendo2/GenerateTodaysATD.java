package com.example.attendo2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GenerateTodaysATD extends AppCompatActivity {

    Spinner spSubject,spClass;
    Button btcreate;
    EditText etDate;
    DatabaseReference dbStudent;
    DatabaseReference dbAttendance;
    DatabaseReference ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_todays_atd);



        spSubject = findViewById(R.id.spinnerSubject4);

        spClass = findViewById(R.id.spinnerSubject5);
        btcreate = findViewById(R.id.btCreate);

        etDate = findViewById(R.id.date3);
        ref = FirebaseDatabase.getInstance().getReference();
        dbStudent = ref.child("Student");

        dbAttendance = ref.child("attendance");




    }

    public void Create(View view) {

       final String date = etDate.getText().toString();
       final String classes = spClass.getSelectedItem().toString();
        final String subjects = spSubject.getSelectedItem().toString();


        dbStudent.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String sid,P1="-";
                Attendance_sheet a = new Attendance_sheet(P1);

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    sid=dsp.child("sid").getValue().toString();
                    dbAttendance.child(date).child(classes).child(subjects).child(sid).setValue(a);

                }
                Toast.makeText(getApplicationContext(),"successfully created "+date+" db", Toast.LENGTH_LONG).show();
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "something wrong", Toast.LENGTH_LONG).show();
            }

        });
    }
}
