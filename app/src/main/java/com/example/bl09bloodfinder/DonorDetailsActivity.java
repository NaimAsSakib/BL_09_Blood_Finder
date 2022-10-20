package com.example.bl09bloodfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class DonorDetailsActivity extends AppCompatActivity {

    TextView donorName, donorBlood, donorNumber, donorEmail, donorOccupation, donorLocation, donorOrganization;
    ImageView ivBack, call;
    private String fullName, blood, mobile, email, location, occupation,  organization;

    DatabaseReference databaseReference;  //Firebase


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_details);

        ivBack = findViewById(R.id.ivBackArrow);
        donorBlood = findViewById(R.id.tvDonorDetailsBloodGroupName);
        donorName = findViewById(R.id.tvDonorDetailsName);
        donorNumber = findViewById(R.id.tvDonorDetailsPhoneNumber);
        donorEmail = findViewById(R.id.tvDonorDetailsEmailAddress);
        donorLocation = findViewById(R.id.tvDonorDetailsLocation);
        donorOccupation = findViewById(R.id.tvDonorDetailsOccupation);
        donorOrganization = findViewById(R.id.tvDonorDetailsOrganization);

        call = findViewById(R.id.ivCardViewCall);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(DonorDetailsActivity.this, FirstActivity.class);
                startActivity(intentBack);
                finish();
            }
        });

        //getting passed value from ContactListRCVAdapter
        Intent intent = getIntent();
        //fullName & blood both are getting values from ContactListRCViewAdapter & AfterFilterRCVAdapter as their getStringExtra(value are same)
         fullName=intent.getStringExtra("Name");
         blood= intent.getStringExtra("Blood");
         mobile= intent.getStringExtra("Number");
         email= intent.getStringExtra("Email");
         location= intent.getStringExtra("Location");
         occupation= intent.getStringExtra("Occupation");
         organization= intent.getStringExtra("Organization");


        donorBlood.setText(blood);
        donorName.setText(fullName);

        if(mobile!=null){
            donorNumber.setText(mobile);
        }
        if(email!=null){
            donorEmail.setText(email);
        }
        if(location!=null){
            donorLocation.setText(location);
        }
        if(occupation!=null){
            donorOccupation.setText(occupation);
        }
        if(organization!=null){
            donorOrganization.setText(organization);
        }




        //Firebase database code
        //instead of 68-83, we can call firebase database as well, but that will take time to load, so use upper system to set text
        //fetchFirebaseQueryDatabase();

    }
/*    private void fetchFirebaseQueryDatabase(){
        databaseReference = FirebaseDatabase.getInstance().getReference("students");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ModelClassDonorDetails donorDetails = dataSnapshot.getValue(ModelClassDonorDetails.class);

                    //condition for getting details of donor only whose name & blood both equal to firebase database
                    if(donorDetails.name.equals(fullName) && donorDetails.bloodGroup.equals(blood)){

                        *//*if (!donorDetails.mobile.isEmpty()){
                            donorNumber.setText(donorDetails.mobile);
                        }else {
                            donorNumber.setText("Not Available");
                        }*//*
                        donorNumber.setText(donorDetails.mobile);
                        donorLocation.setText(donorDetails.currentLocation);
                        donorEmail.setText(donorDetails.email);
                        donorOccupation.setText(donorDetails.occupation);
                        donorOrganization.setText(donorDetails.organizationName);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);

    }*/
}