package com.example.attendo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class DashboardActivity extends AppCompatActivity {
    Button admin, student, teacher, CR;
    private static long back_pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        admin = findViewById(R.id.buttonAdmin);
        student = findViewById(R.id.buttonStudent);
        teacher = findViewById(R.id.buttonTeacher);
        CR = findViewById(R.id.buttonCr);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, adminLoginActivity.class);
                startActivity(intent);

            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, loginstudent.class);
                startActivity(intent);

            }
        });

        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, loginteacherActivity.class);
                startActivity(intent);

            }
        });

        CR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, CRLogin.class);
                startActivity(intent);

            }
        });

    }
    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            finish();
            ActivityCompat.finishAffinity(this);
            System.exit(0);
        }
        else {
            Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }

    public void gotoinfo(View view) {

        Intent intent = new Intent(DashboardActivity.this, Information.class);
        startActivity(intent);


    }

    public void gotoinfo1(View view) {
        Intent intent = new Intent(DashboardActivity.this, Information.class);
        startActivity(intent);
    }
}
