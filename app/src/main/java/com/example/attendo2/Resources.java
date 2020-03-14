package com.example.attendo2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendo2.adapters.Recylerview_resources_adapter;
import com.example.attendo2.models.ResourcesUpload;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Resources extends AppCompatActivity implements Recylerview_resources_adapter.OnNoteListener {

    private static final String TAG = "HiiSPD";
    private RecyclerView mRecyclerView;
    private Recylerview_resources_adapter mAdapter;

    customLoadingBar customLoadingBar = new customLoadingBar(Resources.this);

    private DatabaseReference mDatabaseRef , ref;
    private List<ResourcesUpload> mUploads;
    String listcomponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        customLoadingBar.startLoader();

        Intent intent = getIntent();
        listcomponent = intent.getStringExtra("listcomponent");

        mRecyclerView = findViewById(R.id.resources_recylerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        ref = mDatabaseRef.child("Student").child("Resources").child(listcomponent);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ResourcesUpload upload = postSnapshot.getValue(ResourcesUpload.class);
                    mUploads.add(upload);
                    //Toast.makeText(Resources.this, "  "+postSnapshot.getKey().toString(), Toast.LENGTH_SHORT).show();


                }

                setadapter(mUploads);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                customLoadingBar.dismissLoader();

                Toast.makeText(Resources.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setadapter(List<ResourcesUpload> mUploads) {
        mAdapter = new Recylerview_resources_adapter(Resources.this, mUploads,this);

        mRecyclerView.setAdapter(mAdapter);
        customLoadingBar.dismissLoader();
    }

    @Override
    public void onNoteClickkk(int position) {
        String urll=  mUploads.get(position).getUrl();
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("webviewurl",urll);
        startActivity(intent);

    }
}
