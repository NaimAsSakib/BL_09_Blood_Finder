package com.example.bl09bloodfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.Query;

public class FilterActivity extends AppCompatActivity {

    CardView cardViewName, cardViewBlood, cardViewLocation;
    ImageView backButton;
    TextView tvSelectedText;
    Button searchButton;


    String inputName, inputBloodGroup, inputLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        cardViewName = findViewById(R.id.cardView1);
        cardViewBlood = findViewById(R.id.cardView2);
        cardViewLocation = findViewById(R.id.cardView3);

        backButton = findViewById(R.id.ivBackFilterAct);

        tvSelectedText = findViewById(R.id.tvBloodGroupNameFilter);

        searchButton = findViewById(R.id.btnFilterApply);


        cardViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // openFragment(new FragFilterName());  without listener
                openFragment(new FragFilterName());
            }
        });

        cardViewBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new FragFilterBlood());
            }
        });

        cardViewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new FragFilterLocation());
            }
        });

        //Decoding passed values from fragments
        Intent intent = getIntent();
        inputName = intent.getStringExtra("inputName");
        inputBloodGroup = intent.getStringExtra("selectedBlood");
        inputLocation = intent.getStringExtra("locationName");

        if (inputName != null) {
            tvSelectedText.setText(inputName);
        } else if (inputBloodGroup != null) {
            tvSelectedText.setText(inputBloodGroup);
        } else if (inputLocation != null) {
            tvSelectedText.setText(inputLocation);
        }

        //setting button visibility rule
        if (tvSelectedText.getText().toString().isEmpty()) {
            searchButton.setVisibility(View.GONE);
        } else {
            searchButton.setVisibility(View.VISIBLE);

            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String selectedValue = tvSelectedText.getText().toString();

                    Intent intentNextAct = new Intent(FilterActivity.this, AfterFilterActivity.class);
                    intentNextAct.putExtra("filterValue", selectedValue);
                    startActivity(intentNextAct);
                }
            });

        }

        //going to back activity functionality
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilterActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });


    }

    //method for opening fragments
    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FilterActivity.this, FirstActivity.class);
        startActivity(intent);
    }

}