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
import android.widget.TextView;

import com.google.firebase.database.Query;

public class FilterActivity extends AppCompatActivity {

    CardView cardViewName, cardViewBlood, cardViewLocation, cardViewBottom;

    TextView selectedText, tvToGoBack;
    Button searchButton;


    String inputName, inputBloodGroup, inputLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        cardViewName = findViewById(R.id.cardView1);
        cardViewBlood = findViewById(R.id.cardView2);
        cardViewLocation = findViewById(R.id.cardView3);

        selectedText=findViewById(R.id.tvBloodGroupNameFilter);
        tvToGoBack=findViewById(R.id.tvToGoBack);

        searchButton=findViewById(R.id.btnFilterApply);



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

            Intent intent=getIntent();
        inputName=intent.getStringExtra("inputName");
        inputBloodGroup=intent.getStringExtra("selectedBlood");
        inputLocation=intent.getStringExtra("locationName");

            if(inputName!=null){
                selectedText.setText(inputName);
            }
            else if(inputBloodGroup!=null){
                selectedText.setText(inputBloodGroup);
            }
            else if(inputLocation!=null){
                selectedText.setText(inputLocation);
            }

            //setting button visibility rule
            if (selectedText.getText().toString().isEmpty()){
                searchButton.setVisibility(View.GONE);
            }else{
                searchButton.setVisibility(View.VISIBLE);

            }

            tvToGoBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(FilterActivity.this,FirstActivity.class);
                    startActivity(intent);
                }
            });

    }

    //method for opening fragments
    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit();
    }

}