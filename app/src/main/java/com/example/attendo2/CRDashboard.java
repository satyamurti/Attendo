package com.example.attendo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class CRDashboard extends AppCompatActivity {

    String classsesCRD,subjectCRD;
    Spinner spclass,spsuubject;
    EditText dateCRD;

    private static long back_pressed;
    String date ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crdashboard);

        setTitle("CR DashBoard");








    }

    public void takeAttendanceButtonCRD(View view) {




        Intent intent = new Intent(this, BHICHWALA.class);

        startActivity(intent);





    }

    public void previous_records_CRD(View view) {


        Intent intent = new Intent(this, CRActivitySheet.class);

        startActivity(intent);




        //Toast.makeText(this, "Option Not ADded Plz contact Admin", Toast.LENGTH_SHORT).show();
    }

    public void logoutCRD(View view) {
        Intent logoutCR=new Intent(CRDashboard.this,DashboardActivity.class);
        logoutCR.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(logoutCR);

    }
}
