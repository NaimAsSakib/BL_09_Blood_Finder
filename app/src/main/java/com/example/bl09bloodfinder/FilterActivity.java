package com.example.bl09bloodfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.Query;

public class FilterActivity extends AppCompatActivity implements ItemOnClickListener{

    CardView cardViewName, cardViewBlood, cardViewLocation;

    Button searchButton;

    ItemOnClickListener itemOnClickListener;

    String searchByName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        cardViewName = findViewById(R.id.cardView1);
        cardViewBlood = findViewById(R.id.cardView2);
        cardViewLocation = findViewById(R.id.cardView3);

        searchButton=findViewById(R.id.btnFilterApply);



        cardViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new FragFilterName(FilterActivity.this));
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


    }

    //method for opening fragments
    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit();
    }


    @Override
    public void onItemClicked(String value, String name) {
        searchByName=value;
        Log.e("Name is ",searchByName);

    }
}