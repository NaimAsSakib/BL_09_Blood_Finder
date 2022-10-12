package com.example.bl09bloodfinder;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class FragFilterLocation extends Fragment {
    EditText etInputLocation;
    String passLocation;
    Button btnLocationSearch;

    private RadioGroup radioGroup;
    private RadioButton radBtnDhaka, radBtnRaj, radBtnSiraj;


    public FragFilterLocation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_filter_location, container, false);

        etInputLocation = view.findViewById(R.id.locationName);
        btnLocationSearch = view.findViewById(R.id.btnFragLocation);

        radioGroup = view.findViewById(R.id.radGroup);
        radBtnDhaka = view.findViewById(R.id.radBtnDhaka);
        radBtnRaj = view.findViewById(R.id.radBtnRaj);
        radBtnSiraj = view.findViewById(R.id.radBtnSiraj);


        //checking radiobutton id & setting that value on edittext
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id= group.getCheckedRadioButtonId();

                switch (id){
                    case R.id.radBtnDhaka:
                        etInputLocation.setText(radBtnDhaka.getText());
                        break;
                    case R.id.radBtnRaj:
                        etInputLocation.setText(radBtnRaj.getText());
                        break;
                    case R.id.radBtnSiraj:
                        etInputLocation.setText(radBtnSiraj.getText());
                        break;
                }
            }
        });


        //Getting edittext input value & passing it to Filter activity
        btnLocationSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passLocation = etInputLocation.getText().toString().trim();
                if (passLocation.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter a name", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(getActivity(), FilterActivity.class);
                    intent.putExtra("locationName", passLocation);
                    startActivity(intent);
                }
            }
        });


        return view;
    }
}