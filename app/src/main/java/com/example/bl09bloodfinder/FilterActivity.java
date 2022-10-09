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

    TextView selectedText;
    Button searchButton;


    String inputName, inputBloodGroup, inputCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        cardViewName = findViewById(R.id.cardView1);
        cardViewBlood = findViewById(R.id.cardView2);
        cardViewLocation = findViewById(R.id.cardView3);

        selectedText=findViewById(R.id.tvBloodGroupNameFilter);
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

            if(inputName!=null){
                selectedText.setText(inputName);
            }
            else if(inputBloodGroup!=null){
                selectedText.setText(inputBloodGroup);

            }





    }

    //method for opening fragments
    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit();
    }

}