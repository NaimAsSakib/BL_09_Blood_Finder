package com.example.bl09bloodfinder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class BloodGroupRCVAdapter extends RecyclerView.Adapter<BloodGroupRCVAdapter.Viewholder> {
    Context context;
    String[] bloodGroupName;

    int bloodGroupIndex=-1;  //For changing CardView color when clicked

    ItemOnClickListener listener;

    // Constructor before adding listener
  /*  public BloodGroupRCVAdapter(Context context, String[] bloodGroupName) {
        this.context = context;
        this.bloodGroupName = bloodGroupName;
    }*/

    public BloodGroupRCVAdapter(Context context, String[] bloodGroupName, ItemOnClickListener listener) {
        this.context = context;
        this.bloodGroupName = bloodGroupName;
        this.listener = listener;
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView tvBloodName;
        CardView cardView;

        public Viewholder(@NonNull View itemView) {

            super(itemView);
            tvBloodName = itemView.findViewById(R.id.tvBloodGroupName);
            cardView=itemView.findViewById(R.id.rcv_CardView);

        }

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_horizontal_rcv_blood_group,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvBloodName.setText(bloodGroupName[position]);

        //Code to change CardView color when clicked(from here to if else condition)
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGroupIndex=position;
                notifyDataSetChanged();

                //These 2 lines are for adding listener
                String clickedBloodGroup= bloodGroupName[position];
                listener.onItemClicked(clickedBloodGroup,"");

            }
        });
        if(bloodGroupIndex==position){
            holder.cardView.setCardBackgroundColor(Color.parseColor("#2e869a"));
            holder.tvBloodName.setTextColor(Color.WHITE);
        }else{
            holder.cardView.setCardBackgroundColor(Color.parseColor("#f5f5f5"));
            holder.tvBloodName.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return bloodGroupName.length;
    }
}
