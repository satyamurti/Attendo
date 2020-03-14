package com.example.attendo2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendo2.models.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpForm extends AppCompatActivity {

    EditText Sname;
    EditText Sid,spassword;
    String sname,sid,classname,spass;
    Spinner classes;
    DatabaseReference databaseStudent;
    String RollnumberPattern = "[0-9]{9}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);



            Sname = (EditText) findViewById(R.id.editText271);
            Sid = (EditText) findViewById(R.id.editText272);
            classes = (Spinner) findViewById(R.id.spinner27);
            spassword = (EditText) findViewById(R.id.editText273);

        }


    public void Register(View view) {


        if (Sname.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Student Name", Toast.LENGTH_SHORT).show();
        } else if (Sid.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Rollnumber", Toast.LENGTH_SHORT).show();
        } else if (spassword.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        } else if (Sid.getText().toString().matches(RollnumberPattern)){


            databaseStudent = FirebaseDatabase.getInstance().getReference();

            sname = Sname.getText().toString();
            sid = Sid.getText().toString();
            classname = classes.getSelectedItem().toString();
            spass = spassword.getText().toString();


            databaseStudent.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (!(dataSnapshot.child("Student").child(sid).exists())) {
                        Student student = new Student(sname, sid, classname, spass);
                        databaseStudent.child("Student").child(sid).setValue(student);
                        Toast.makeText(getApplicationContext(), "WooHoo Registered Successfully :)", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(SignUpForm.this, "This Roll Number Already Exists Plz Contact Admin ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            //String id = databaseStudent.push().getKey();


        }
        else {
            Toast.makeText(this, "Enter Valid Roll Number :)", Toast.LENGTH_SHORT).show();
        }
    }



}
