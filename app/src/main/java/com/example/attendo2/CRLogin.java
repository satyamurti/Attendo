package com.example.attendo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CRLogin extends AppCompatActivity {

    Button login;
    DatabaseReference ref;
    String userid,pass;
    String dbpassword;
    Bundle basket;
    EditText username,password;
    customLoadingBar customLoadingBar = new customLoadingBar(CRLogin.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crlogin);

        login = findViewById(R.id.loginCR);
        username = findViewById(R.id.EditTextCR);
        password = findViewById(R.id.PasswordEditTextCR);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userid = username.getText().toString();
                pass = password.getText().toString();


               customLoadingBar.startLoader();



                basket = new Bundle();
                basket.putString("message", userid);

                ref = FirebaseDatabase.getInstance().getReference();
                DatabaseReference dbuser = ref.child("CSEB_CR");


                dbuser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       customLoadingBar.dismissLoader();
                        dbpassword = dataSnapshot.getValue().toString();
                       // Toast.makeText(CRLogin.this, dbpassword, Toast.LENGTH_SHORT).show();
                        verify(dbpassword);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }



    public void verify(String dbpassword){
        if (userid.equals("CSEBCR") && pass.equalsIgnoreCase(dbpassword) ) {
            //  if (userid.equalsIgnoreCase("admin") && pass.equals("admin")) {
            customLoadingBar.dismissLoader();
            Intent intent = new Intent(this, CRDashboard.class);
            intent.putExtras(basket);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Successfully Logged In ", Toast.LENGTH_LONG).show();
            finish();
            //  }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please Enter valid user id or password", Toast.LENGTH_LONG).show();
        }

    }
}
