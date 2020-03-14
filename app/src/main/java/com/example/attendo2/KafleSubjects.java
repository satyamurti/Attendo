package com.example.attendo2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendo2.adapters.CustomListAdapterallAtt;
import com.example.attendo2.models.allsubjectsatt;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KafleSubjects extends AppCompatActivity {

    ProgressDialog mDialog;
    customLoadingBar customLoadingBar = new customLoadingBar(KafleSubjects.this);
    private ArrayList<allsubjectsatt> allAttendance;
    private CustomListAdapterallAtt customListAdapter;
    final ArrayList<String> averageatt = new ArrayList<>();

    int count = 0;
    int P = 0;
     int A =0;
    float average = (float) 0.0;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dbAttendance;

    String p1 ;
    String p2;
    String student_id,teacher_id,classname;

    ListView listView;


    private int[] photos={
            R.drawable.boat,R.drawable.ufo,R.drawable.car,R.drawable.hot_air_bollon,R.drawable.bike,
            R.drawable.helicopter,R.drawable.locomotive,R.drawable.spaceship};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kafle_subjects);


//        mDialog=new ProgressDialog(KafleSubjects.this);
//        mDialog.setMessage("Please Wait...");
//        mDialog.setTitle("Waking up Database...");


        listView = findViewById(R.id.listallsub);
        allAttendance = new ArrayList<>();
        customListAdapter=new CustomListAdapterallAtt(allAttendance,this);
        customLoadingBar.startLoader();

        Intent intent = getIntent();
        classname = intent.getStringExtra("classname");
        student_id = intent.getStringExtra("sid");
//        Toast.makeText(this, classname+student_id, Toast.LENGTH_SHORT).show();
        final ArrayList<String> subjectsall = new ArrayList<>();
        subjectsall.add("CSPC");
        subjectsall.add("MAIR22");
        subjectsall.add("HSIR11");
        subjectsall.add("CSIR21");
        subjectsall.add("CHIR11");
        subjectsall.add("CHIR12");
        subjectsall.add("MEIR12");
        final int xyz = subjectsall.size();
       // Toast.makeText(this,"hh"+xyz, Toast.LENGTH_SHORT).show();

        for (int count22=0;count22 < subjectsall.size();count22++)
        {


//            mDialog.show();
            dbAttendance = ref.child("attendance");
            final int finalCount2 = count22;
            final String finalClassname = classname;
            final String finalStudent_id = student_id;
            final int finalCount21 = count22;
            final int finalCount22 = count22;
            int finalCount23 = count22;
            dbAttendance.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                        p1 = dsp.child(classname).child(subjectsall.get(finalCount2)).child(student_id).child("p1").getValue().toString();


//                       dates.add(dsp.getKey() + "    " + p1[0]);
                        // result add karra bro into array list rukh jaa into array list

                        //count[0] =0;
                        //  Toast.makeText(getApplicationContext(),dsp.child(student_id).child("p1").getValue().toString(),Toast.LENGTH_LONG).show();
                        if (p1.equals("P")) {

                            P++;
                            count++;
                        }
                        if (p1.equals("A")) {
                            A++;
                            count++;
                        }

                    }

                    average = ((float) P / count) * 100;

                    P = 0;
                    A =0;
                    count = 0;

                        averageatt.add(Float.toString(average));

                    allAttendance.add(new allsubjectsatt(subjectsall.get(finalCount21),averageatt.get(finalCount22),photos[finalCount23]));


                    if (subjectsall.get(finalCount22) == "MEIR12"){

                        putData(allAttendance);

                    }



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
                }
            });
        }

    }
    public void putData(ArrayList studentlist){

//        mDialog.dismiss();

        customLoadingBar.dismissLoader();
        customListAdapter=new CustomListAdapterallAtt(studentlist,this);
        listView.setAdapter(customListAdapter);

    }
}



