package com.example.bl09bloodfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.Button;

public class FilterActivity extends AppCompatActivity {

    CardView cardViewName,cardViewBlood, cardViewLocation;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        cardViewName=findViewById(R.id.cardView1);
        cardViewBlood=findViewById(R.id.cardView2);
        cardViewLocation=findViewById(R.id.cardView3);
        button=findViewById(R.id.btnFilterApply);


    }
}