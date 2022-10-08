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


public class FragFilterName extends Fragment {

    EditText nameInput;
    Button button;

    String name;
    private ItemOnClickListener listener;


    public FragFilterName(ItemOnClickListener listener) {
        // Required empty public constructor
        this.listener=  listener;
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
                name=nameInput.getText().toString().trim();

                if (name.isEmpty()){
                    Toast.makeText(getActivity(),"Please enter a name",Toast.LENGTH_LONG).show();
                }else {
                    listener.onItemClicked(name,"FragName");
                    Intent intent = new Intent(getActivity(), FilterActivity.class);
                    startActivity(intent);
                }
            }
        });

        return  view;

    }
}