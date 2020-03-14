package com.example.attendo2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class studentlogin extends AppCompatActivity {
    String message, message1, message2, classname;
    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    Toolbar mToolbar;
    DatabaseReference dbchangepass;
    DatabaseReference dbchangename;

    DatabaseReference dbStudent;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Teacher");
    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Student");
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);

        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("message");
        message1 = bundle.getString("message1");
     //   mToolbar = (Toolbar) findViewById(R.id.ftoolbar);
       // mToolbar.setTitle(message + "'s Dashboard" + "(" + date + ")");
        TextView txtView = (TextView) findViewById(R.id.textView1);


        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        txtView.setText("Welcome :" + message);



        /*ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String tid = dataSnapshot.getValue(String.class);
              //  Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
               // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

         */


        dbStudent = ref1.child(message);
        dbStudent.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                classname = dataSnapshot.child("classes").getValue(String.class);


               // Toast.makeText(studentlogin.this, classname, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

        public void viewAttendance (View v){
            Bundle basket = new Bundle();
            basket.putString("sid", message);

            Intent intent = new Intent(this, student_attendance_sheet.class);
            intent.putExtras(basket);
            intent.putExtra("classname", classname);

            startActivity(intent);
        }

        public void logoutStudent (View view){

            SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
            SharedPreferences.Editor editor= preferences.edit();
            editor.putString("remember","false");
            editor.apply();






            Intent logoutStudent = new Intent(studentlogin.this, DashboardActivity.class);
            logoutStudent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(logoutStudent);
        }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
            finish();
            ActivityCompat.finishAffinity(this);
            System.exit(0);
        }
    public void ChangePassword(View view) {

        dbchangepass=ref1.child(message);
        dbchangename=ref1.child(message);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Set your new password");
        final LayoutInflater inflater = this.getLayoutInflater();
        View add_menu_layout = inflater.inflate(R.layout.changepassword, null);
        final EditText password=(EditText)add_menu_layout.findViewById(R.id.newpassword);
        final EditText name=(EditText)add_menu_layout.findViewById(R.id.newnameid);

        alertDialog.setView(add_menu_layout);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, int which) {
                if (!TextUtils.isEmpty(password.getText().toString())  && !TextUtils.isEmpty(name.getText().toString()))
                {
                    dbchangepass.child("spass").setValue(password.getText().toString());
                    dbchangename.child("sname").setValue(name.getText().toString());
                    Toast.makeText(studentlogin.this, "Successfully Changed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(studentlogin.this, "Please Enter New Password", Toast.LENGTH_SHORT).show();
                }



            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();

    }

    public void gotoResources(View view) {

        Intent intent = new Intent(this,Resources0.class);
        startActivity(intent);
    }
}

