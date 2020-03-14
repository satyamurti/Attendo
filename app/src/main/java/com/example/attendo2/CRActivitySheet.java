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

public class CRActivitySheet extends AppCompatActivity {
    String dayyy,monthhhh;

    TextView tvSelectedDate;

    private DatePickerDialog.OnDateSetListener onDateSetListener;
    String classsesCRD,subjectCRD;
    Spinner spclass,spsuubject;
    Button dateCRD;

    private static long back_pressed;
    String  date1;
    String datePattern = "\\d{1,2}-\\d{1,2}-\\d{4}";

    DatabaseReference db;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crsheet);

        tvSelectedDate = findViewById(R.id.tvSelectedDate);

        spclass = findViewById(R.id.spinner2CRDA);
        spsuubject = findViewById(R.id.spinner3CRDA);
        dateCRD = findViewById(R.id.date3CRDA);
//        date1 = new SimpleDateFormat("dd-MM-yyyy").format(new Date());




        dateCRD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog= new DatePickerDialog(CRActivitySheet.this,
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


                month= month+1;

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
                date1 = dayyy+"-"+monthhhh+"-"+year;
                tvSelectedDate.setText(date1);
                Toast.makeText(CRActivitySheet.this, "dd  "+date1, Toast.LENGTH_SHORT).show();

            }
        };

        dateCRD.setText(date1);

    }

    public void Getoatt(View view) {
        if (spsuubject.getSelectedItem().toString().equals("Select Subject First")) {
            Toast.makeText(this, "Select Subject", Toast.LENGTH_SHORT).show();
        }
//        } else if (!(dateCRD.getText().toString().matches(datePattern))) {
//            Toast.makeText(this, "Enter Valid Date Format like DD-MM-YYYY", Toast.LENGTH_SHORT).show();
//        }
        else {





            classsesCRD = spclass.getSelectedItem().toString();
            subjectCRD = spsuubject.getSelectedItem().toString();
         //   date = dateCRD.getText().toString();



            db= ref.child("attendance");

            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(date1).exists()){


                        Bundle basket = new Bundle();
                        basket.putString("class_selected", classsesCRD);
                        basket.putString("subject_selected", subjectCRD);
                        basket.putString("date_selected", date1);



                        Intent intent = new Intent(CRActivitySheet.this, CRActivity2.class);
                        intent.putExtras(basket);
                        startActivity(intent);



                    }
                    else{
                        Toast.makeText(CRActivitySheet.this, "Attendance on this date is not created yet", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });








        }

    }
}
