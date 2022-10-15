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
    private String fullName, blood;

    ModelClassDonorDetails modelClassDonorDetails;
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
         fullName=intent.getStringExtra("Name");
         blood= intent.getStringExtra("Blood");

        donorBlood.setText(blood);
        donorName.setText(fullName);


        fetchFirebaseQueryDatabase();

    }
    private void fetchFirebaseQueryDatabase(){
        databaseReference = FirebaseDatabase.getInstance().getReference("students");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ModelClassDonorDetails donorDetails = dataSnapshot.getValue(ModelClassDonorDetails.class);

                    if(donorDetails.name.equals(fullName) && donorDetails.bloodGroup.equals(blood)){
                        if (!donorDetails.mobile.isEmpty()){
                            donorNumber.setText(donorDetails.mobile);
                        }else {
                            donorNumber.setText("Not Available");
                        }

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
        //databaseReference.addListenerForSingleValueEvent(valueEventListener);
        Query query = FirebaseDatabase.getInstance().getReference("students")
                .orderByChild("name")
                .equalTo(fullName);
        query.addListenerForSingleValueEvent(valueEventListener);


    }
}