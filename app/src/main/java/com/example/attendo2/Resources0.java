package com.example.attendo2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendo2.adapters.Recylerview_resourses0;
import com.example.attendo2.models.ResoursesUpload0;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Resources0 extends AppCompatActivity implements Recylerview_resourses0.OnNoteListener {
    private static final String TAG = "HiiSPD";
    private RecyclerView mRecyclerView;
    private Recylerview_resourses0 mAdapter;
    String imageurlll, global_imageurl;

    customLoadingBar customLoadingBar = new customLoadingBar(Resources0.this);

    private DatabaseReference mDatabaseRef, ref, ref2;
    private List<ResoursesUpload0> mUploads;
    ArrayList<String> urlList = new ArrayList<>();
    Resources0.FirebaseCallback firebaseCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources0);

        customLoadingBar.startLoader();

        mRecyclerView = findViewById(R.id.resources_recylerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        ref = mDatabaseRef.child("Student").child("Resources");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count1 = (int) dataSnapshot.getChildrenCount();
                Log.d(TAG, "onDataChange: count " + count1);
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ResoursesUpload0 upload = new ResoursesUpload0();
                    upload.setName(postSnapshot.getKey());
                    //   getImageurl(firebaseCallback,postSnapshot.getKey());
                    getImageurl(new FirebaseCallback() {
                        @Override
                        public void onCallback(String urlv) {
                            Log.d(TAG, "onDataChange: ImageUrls are here  " + urlv);
                            upload.setImageUrl(urlv);
                        }
                    }, postSnapshot.getKey());
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mUploads.add(upload);
                        }
                    }, 20);
                    if (count1 == 1) {
                        Log.d(TAG, "onDataChange: count will be 1 yes or no check , is  "+ count1);
                        setadapter(mUploads);

                        break;
                    }
                    count1 = count1 - 1;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                customLoadingBar.dismissLoader();

                Toast.makeText(Resources0.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getImageurl(FirebaseCallback firebaseCallback, String key) {


        ref2 = mDatabaseRef.child("Student").child("ImageUrls").child(key);
        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                imageurlll = dataSnapshot.child("01").getValue().toString();
                //    Log.d(TAG, "onDataChange: "+ imageurlll);
                firebaseCallback.onCallback(imageurlll);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }


    private void setadapter(List<ResoursesUpload0> mUploads) {
        mAdapter = new Recylerview_resourses0(Resources0.this, mUploads, this);
        mRecyclerView.setAdapter(mAdapter);
        Toast.makeText(this, "Please Swipe Down to Notification Once :)", Toast.LENGTH_SHORT).show();
        mAdapter.notifyDataSetChanged();
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        customLoadingBar.dismissLoader();
    }

    @Override
    public void onNoteClickkk(int position) {

        Intent intent = new Intent(this, Resources.class);
        intent.putExtra("listcomponent", mUploads.get(position).getName().toString());
        startActivity(intent);

    }


    private interface FirebaseCallback {
        void onCallback(String urlv);
    }
}
