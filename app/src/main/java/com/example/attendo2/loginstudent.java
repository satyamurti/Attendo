package com.example.attendo2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginstudent extends AppCompatActivity {
    Button login;
    DatabaseReference ref;
    String userid,pass;
    public static final String RMDATA= "rmdata";
    String dbpassword;
    Bundle basket, basket1;
    EditText username,password;
    customLoadingBar customLoadingBar = new customLoadingBar(loginstudent.this);
    CheckBox cbRememberme;
    public boolean verificationBl=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginstudent);

        login = findViewById(R.id.login);
        username = findViewById(R.id.adminEditText);
        password = findViewById(R.id.adminPasswordEditText);
        cbRememberme = findViewById(R.id.cbRememberme);

        final String rmdata = username.getText().toString();



        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
       String verification= preferences.getString("remember","");
        String userid22 = preferences.getString("rmdata","");
      //  Toast.makeText(this, userid22, Toast.LENGTH_SHORT).show();


       if (verification.equals("true")){

          Toast.makeText(this, userid22, Toast.LENGTH_SHORT).show();
           basket1 = new Bundle();
           basket1.putString("message", userid22);
           Intent intent = new Intent(this,  studentlogin.class);
           intent.putExtras(basket1);
           startActivity(intent);
       }




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userid = username.getText().toString();
                pass = password.getText().toString();
                customLoadingBar.startLoader();
                basket = new Bundle();
                basket.putString("message", userid);

                ref = FirebaseDatabase.getInstance().getReference();
                DatabaseReference dbuser = ref.child("Student").child(userid);


                dbuser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                       customLoadingBar.dismissLoader();
                        dbpassword = dataSnapshot.child("spass").getValue(String.class);
                        verify(dbpassword);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "database error", Toast.LENGTH_LONG).show();
                    }
                });


            }
        });



//        cbRememberme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (compoundButton.isChecked()){
//
//
//                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString("remember","true");
//                    editor.putString(RMDATA,username.getText().toString());
//                    editor.apply();
//                    Toast.makeText(loginstudent.this, "Kaam Hogaya Bro", Toast.LENGTH_SHORT).show();
//
//
//
//
//                }
//                else if (!compoundButton.isChecked())
//                {
//
//
//                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString("remember","false");
//
//                    editor.apply();
//                    Toast.makeText(loginstudent.this, "Kaam  Nahi Hogaya Bro", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
    }

    public void verify(String dbpassword) {
        if(userid.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Username cannot be empty", Toast.LENGTH_LONG).show();
        }
        else if (pass.equalsIgnoreCase(this.dbpassword) ) {
            //  if (userid.equalsIgnoreCase("admin") && pass.equals("admin")) {
            customLoadingBar.dismissLoader();
            Intent intent = new Intent(this, studentlogin.class);
            intent.putExtras(basket);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
            verificationBl=true;
            Remembermefun();

            finish();

            //  }
        }
        else {
            Toast.makeText(getApplicationContext(), "Please Enter valid user id or password", Toast.LENGTH_LONG).show();
            verificationBl = false;
        }
    }

    public  void Remembermefun(){
        if (cbRememberme.isChecked()){


            SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("remember","true");
            editor.putString(RMDATA,username.getText().toString());
            editor.apply();
            Toast.makeText(loginstudent.this, "Kaam Hogaya Bro", Toast.LENGTH_SHORT).show();




        }
        else if (!cbRememberme.isChecked())
        {


            SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("remember","false");

            editor.apply();
            Toast.makeText(loginstudent.this, "Kaam  Nahi Hua Bro", Toast.LENGTH_SHORT).show();

        }



    }



    public void GoToSignUp(View view) {
        Intent intent =  new Intent(this,SignUpForm.class);
        startActivity(intent);
    }

}
