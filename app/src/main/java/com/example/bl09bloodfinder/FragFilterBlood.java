package com.example.bl09bloodfinder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragFilterBlood extends Fragment {
    //Recyclerview code
    RecyclerView recyclerView;
    RecyclerView.Adapter programAdapter;
    RecyclerView.LayoutManager layoutManager;
    GridLayoutManager gridLayoutManager;


    String[] bloodGroupNAmeTab1={"A+","O+","B+","AB+","A-","O-","B-","AB-"};

    String pastedBloodGroup;

    ItemOnClickListener itemOnClickListener;


    public FragFilterBlood() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_frag_filter_blood, container, false);

        //recyclerview code
        recyclerView=view.findViewById(R.id.rcvFragBloodGroup);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        gridLayoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        programAdapter=new FragFilterBloodRCVAdapter(getContext(), bloodGroupNAmeTab1);
        recyclerView.setAdapter(programAdapter);


        return  view;
    }
}