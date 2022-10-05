package com.example.bl09bloodfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity implements ItemOnClickListener{
    //Recyclerview code
    RecyclerView recyclerViewBloodGroup, recyclerViewContactList;
    RecyclerView.Adapter programAdapterBloodGroup, programAdapterContactList;
    RecyclerView.LayoutManager layoutManagerBloodGroup, layoutManagerContactList;

    String[] bloodGroupName = {"A+", "O+", "B+", "AB+", "A-", "O-", "B-", "AB-"};

    DatabaseReference databaseReference;  //Firebase

    private ArrayList<ModelClassContactList> modelClassContactListArrayList;


    ImageView imageFilter;
    TextView tvFilter;

    private String bloodName="A+"; //for initially showing api data in RCV for A+


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        imageFilter = findViewById(R.id.ivDonorContactList1);
        tvFilter = findViewById(R.id.tvDonorContactList1);



        recyclerViewBloodGroup = findViewById(R.id.horizontalRecyclerView);
        recyclerViewBloodGroup.setHasFixedSize(true);
        layoutManagerBloodGroup = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBloodGroup.setLayoutManager(layoutManagerBloodGroup);


       // programAdapterBloodGroup = new BloodGroupRCVAdapter(this, bloodGroupName);  //without listener
        programAdapterBloodGroup = new BloodGroupRCVAdapter(this, bloodGroupName, this);
        recyclerViewBloodGroup.setAdapter(programAdapterBloodGroup);

        modelClassContactListArrayList = new ArrayList<>();
        //for designing only except API
       /* modelClassContactListArrayList.add(new ModelClassContactList("O+", "Arafat Hossain"));
        modelClassContactListArrayList.add(new ModelClassContactList("O+", "Ishtiaque Pial"));
        modelClassContactListArrayList.add(new ModelClassContactList("O+", "Rakib Hasan"));
        modelClassContactListArrayList.add(new ModelClassContactList("O+", "Anika Iffat"));
        modelClassContactListArrayList.add(new ModelClassContactList("O+", "Arafat Hossain"));
        modelClassContactListArrayList.add(new ModelClassContactList("O+", "Ishtiaque Pial"));
        modelClassContactListArrayList.add(new ModelClassContactList("O+", "Rakib Hasan"));
        modelClassContactListArrayList.add(new ModelClassContactList("O+", "Anika Iffat"));
*/
        recyclerViewContactList = findViewById(R.id.verticalRecyclerView_ContactList);
        recyclerViewBloodGroup.setHasFixedSize(true);
        layoutManagerContactList = new LinearLayoutManager(this);

        recyclerViewContactList.setLayoutManager(layoutManagerContactList);
        programAdapterContactList = new ContactListRCViewAdapter(this, modelClassContactListArrayList);
        recyclerViewContactList.setAdapter(programAdapterContactList);

        //Firebase code. path is the name of database
        databaseReference= FirebaseDatabase.getInstance().getReference("students");

        //we can use it directly but didn't use because we have query of blood group
        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelClassContactList contactList= dataSnapshot.getValue(ModelClassContactList.class);
                    modelClassContactListArrayList.add(contactList);
                }
                programAdapterContactList.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ModelClassContactList contactList = dataSnapshot.getValue(ModelClassContactList.class);
                    modelClassContactListArrayList.add(contactList);
                }
                programAdapterContactList.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        databaseReference.addListenerForSingleValueEvent(valueEventListener);


        imageFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, FilterActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemClicked(String value, String name) {

        bloodName=value;
        Log.e("passed value","BGName "+bloodName);

        //Firebase code. path is the name of database
        databaseReference= FirebaseDatabase.getInstance().getReference("students");
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                modelClassContactListArrayList.clear();  //clearing data after every selection of blood group by user

                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ModelClassContactList contactList = dataSnapshot.getValue(ModelClassContactList.class);
                    modelClassContactListArrayList.add(contactList);
                }
                programAdapterContactList.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        Query query= FirebaseDatabase.getInstance().getReference("students")
                .orderByChild("bloodGroup")
                .equalTo(bloodName);
        query.addListenerForSingleValueEvent(valueEventListener);

    }
}