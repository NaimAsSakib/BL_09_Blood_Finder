package com.example.bl09bloodfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AfterFilterActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter programAdapter;
    RecyclerView.LayoutManager layoutManager;

    DatabaseReference databaseReference;  //Firebase

    private ArrayList<ModelClassContactList> modelClassContactListArrayList;

    String selectedValueFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_filter);

        recyclerView = findViewById(R.id.rcvAfterFilterAct);

        Intent intent = getIntent();
        selectedValueFilter = intent.getStringExtra("filterValue");

        modelClassContactListArrayList = new ArrayList<>();

        recyclerView = findViewById(R.id.rcvAfterFilterAct);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        programAdapter = new ContactListRCViewAdapter(this, modelClassContactListArrayList);
        recyclerView.setAdapter(programAdapter);

        modelClassContactListArrayList.clear();  //clearing data after every selection of blood group by user
        firebaseDataQuery();

    }

    private void firebaseDataQuery() {



        //firebase code. path is the name of database
        databaseReference = FirebaseDatabase.getInstance().getReference("students");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ModelClassContactList contactList = dataSnapshot.getValue(ModelClassContactList.class);
                    modelClassContactListArrayList.add(contactList);
                }
                programAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        //Code for querying by blood group
        // For dynamically querying students name with selected blood group by user
        Query query1 = FirebaseDatabase.getInstance().getReference("students")
                .orderByChild("bloodGroup")
                .equalTo(selectedValueFilter);
        query1.addListenerForSingleValueEvent(valueEventListener);

        Query query3 = FirebaseDatabase.getInstance().getReference("students")
                .orderByChild("currentLocation")
                .equalTo(selectedValueFilter);
        query3.addListenerForSingleValueEvent(valueEventListener);

        Query query2 = FirebaseDatabase.getInstance().getReference("students")
                .orderByChild("name")
                .equalTo(selectedValueFilter);
        query2.addListenerForSingleValueEvent(valueEventListener);



    }
}