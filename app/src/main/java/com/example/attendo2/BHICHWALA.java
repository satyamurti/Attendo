package com.example.attendo2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class BHICHWALA extends AppCompatActivity {

    String monthhhh;
    String dayyy;
    customLoadingBar customLoadingBar = new customLoadingBar(BHICHWALA.this);
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    String classsesCRD,subjectCRD;
    Spinner spclass,spsuubject;
    Button dateCRD;
    DatabaseReference db;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    TextView tvSelectedDate;

    private static long back_pressed;
    String date ;
    String datePattern = "\\d{1,2}-\\d{1,2}-\\d{4}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhichwal);
        setTitle("GO____");

        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        spclass = findViewById(R.id.spinner2CRD);
        spsuubject = findViewById(R.id.spinner3CRD);
        dateCRD = findViewById(R.id.date3CRD);

        dateCRD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog= new DatePickerDialog(BHICHWALA.this,
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        onDateSetListener,year,month,day
                        );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month+1;

                if (day <9 && day >0 ){
                    dayyy = String.valueOf("0"+day );
                }
                else{
                    dayyy= String.valueOf(day);}

                if (month <9 && month >0 ){
                    monthhhh = String.valueOf("0"+month );
                }
                else{
                    monthhhh = String.valueOf(month);

                }
                date = dayyy+"-"+monthhhh+"-"+year;
                tvSelectedDate.setText(date);

                Toast.makeText(BHICHWALA.this, "dd  "+date, Toast.LENGTH_SHORT).show();

            }
        };

    }

    public void Gotoatt(View view) {
        customLoadingBar.startLoader();
        if (spsuubject.getSelectedItem().toString().equals("Select Subject First")) {
            customLoadingBar.dismissLoader();
            Toast.makeText(this, "Select Subject", Toast.LENGTH_SHORT).show();
        }
//        else if (!(dateCRD.getText().toString().matches(datePattern))) {
//            customLoadingBar.dismissLoader();
//            Toast.makeText(this, "Enter Valid Date Format like DD-MM-YYYY", Toast.LENGTH_SHORT).show();
//        }
        else {





            classsesCRD = spclass.getSelectedItem().toString();
            subjectCRD = spsuubject.getSelectedItem().toString();
           // date = dateCRD.getText().toString();



            db= ref.child("attendance");

            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(date).exists()){


                        Bundle basket = new Bundle();
                        basket.putString("class_selected", classsesCRD);
                        basket.putString("subject_selected", subjectCRD);
                        basket.putString("date_selected", date);



                        customLoadingBar.dismissLoader();
                        Intent intent = new Intent(BHICHWALA.this, TakeattendanceCR.class);
                        intent.putExtras(basket);
                        startActivity(intent);



                    }
                    else{
                        customLoadingBar.dismissLoader();
                        Toast.makeText(BHICHWALA.this, "Date is not Added Yet Plz Contact Admin SPD", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    customLoadingBar.dismissLoader();
                }
            });








        }

    }
}
