package com.example.bl09bloodfinder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AfterFilterRCVAdapter extends RecyclerView.Adapter<AfterFilterRCVAdapter.Viewholder>{

    Context context;
    ArrayList<ModelClassAfterFilterAct> modelClassAfterFilterAct;

    public AfterFilterRCVAdapter(Context context, ArrayList<ModelClassAfterFilterAct> modelClassAfterFilterAct) {
        this.context = context;
        this.modelClassAfterFilterAct = modelClassAfterFilterAct;
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        CardView bloodGroupCardView, callCardView;
        ImageView call;
        TextView bloodGroup, name, currentLocation;

        LinearLayout layoutDonorDetails;

        public Viewholder(@NonNull View itemView){
            super(itemView);
            bloodGroupCardView = itemView.findViewById(R.id.rcv_AfterFilter);
            callCardView = itemView.findViewById(R.id.rcv_Vertical_CardView_Call_Filtered);
            bloodGroup = itemView.findViewById(R.id.tv_BloodGroup);
            name = itemView.findViewById(R.id.tvNameContactListAfterFilter);
            currentLocation=itemView.findViewById(R.id.tvLocation);
            call = itemView.findViewById(R.id.ivCallFiltered);

            layoutDonorDetails = itemView.findViewById(R.id.linearLayoutContactListAfterFilter);
        }
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_rcv_filtered_output, parent, false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        ModelClassAfterFilterAct item=modelClassAfterFilterAct.get(position);
        holder.bloodGroup.setText(item.getBloodGroup());
        holder.name.setText(item.getName());
        holder.currentLocation.setText(item.getCurrentLocation());

        holder.layoutDonorDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String donorBlood=item.getBloodGroup();
                String donorName=item.getName();
                String donorLocation= item.getCurrentLocation();



                Intent intent=new Intent(context,DonorDetailsActivity.class);
                intent.putExtra("Blood",donorBlood);
                intent.putExtra("Name",donorName);
                intent.putExtra("Location",donorLocation);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelClassAfterFilterAct.size();
    }



}
