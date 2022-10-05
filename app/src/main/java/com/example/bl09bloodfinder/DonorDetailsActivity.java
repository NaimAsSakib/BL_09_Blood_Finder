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
    private String number;

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

        Intent intent = getIntent();
        String fullName=intent.getStringExtra("Name");
        String blood= intent.getStringExtra("Blood");
        donorBlood.setText(blood);
        donorName.setText(fullName);



    }
}