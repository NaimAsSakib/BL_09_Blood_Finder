package com.example.bl09bloodfinder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class FragFilterBloodRCVAdapter extends RecyclerView.Adapter<FragFilterBloodRCVAdapter.Viewholder> {

    Context context;
    String[] bloodGroupNameTab1;

    int rcvRowIndex = -1;  //For changing CardView color when clicked & for selecting one at a time

    //For interface IteOnClickListener;
    ItemOnClickListener itemOnClickListener;


    public FragFilterBloodRCVAdapter(Context context, String[] bloodGroupNameTab1) {
        this.context = context;
        this.bloodGroupNameTab1 = bloodGroupNameTab1;
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView textView;

        public Viewholder(@NonNull View itemview) {
            super((itemview));
            cardView = itemview.findViewById(R.id.cardBloodGroupFilterRCV);
            textView = itemview.findViewById(R.id.tvBloodGroupFilterRCV);

        }
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_rcv_tablayout_bloodgroup, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(bloodGroupNameTab1[position]);

        // Code to change CardView color when clicked(from here to if else condition) for selecting one at a time
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rcvRowIndex = position;
                notifyDataSetChanged();

                Intent intent = new Intent(context, FilterActivity.class);
                String selectedBlood= bloodGroupNameTab1[position];
                intent.putExtra("selectedBlood",selectedBlood);
                context.startActivity(intent);

            }
        });
        if (rcvRowIndex == position) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#2e869a"));
            holder.textView.setTextColor(Color.WHITE);

        } else {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#e6e6e6"));
            holder.textView.setTextColor(Color.parseColor("#686d73"));
        }
    }

    @Override
    public int getItemCount() {
        return bloodGroupNameTab1.length;
    }

}
