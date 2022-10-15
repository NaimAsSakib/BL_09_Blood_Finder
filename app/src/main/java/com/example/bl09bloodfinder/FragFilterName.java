package com.example.bl09bloodfinder;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;


public class FragFilterName extends Fragment {

    EditText nameInput;
    Button button;

    String name;


    public FragFilterName() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_frag_filter_name, container, false);

        nameInput=view.findViewById(R.id.etDonorName);
        button=view.findViewById(R.id.btnFragName);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=nameInput.getText().toString().trim().toUpperCase(Locale.ROOT);

                if (name.isEmpty()){
                    Toast.makeText(getActivity(),"Please enter a name",Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(getActivity(), FilterActivity.class);
                    intent.putExtra("inputName", name);
                    startActivity(intent);
                }
            }
        });

        return  view;

    }
}