package com.example.bl09bloodfinder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class ContactListRCViewAdapter extends RecyclerView.Adapter<ContactListRCViewAdapter.Viewholder> {

    Context context;
    ArrayList<ModelClassContactList> modelClassContactLists;


    public ContactListRCViewAdapter(Context context, ArrayList<ModelClassContactList> modelClassContactLists) {
        this.context = context;
        this.modelClassContactLists = modelClassContactLists;
    }



    public class Viewholder extends RecyclerView.ViewHolder {

        CardView bloodGroupCardView, callCardView;
        ImageView call;
        TextView bloodGroup, name;

        LinearLayout layoutDonorDetails;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            bloodGroupCardView = itemView.findViewById(R.id.rcv_Vertical_CardView);
            callCardView = itemView.findViewById(R.id.rcv_Vertical_CardView_Call);
            bloodGroup = itemView.findViewById(R.id.tv_BloodGroup_ContactList);
            name = itemView.findViewById(R.id.tvNameContactList);
            call = itemView.findViewById(R.id.ivCall);

            layoutDonorDetails = itemView.findViewById(R.id.linearLayoutContactList);

        }
    }

    @NonNull
    @Override
    public ContactListRCViewAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_rcv_bloodgroup_contactlist, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListRCViewAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {


        ModelClassContactList item=modelClassContactLists.get(position);
        holder.bloodGroup.setText(item.getBloodGroup());
        holder.name.setText(item.getName());


        holder.layoutDonorDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String donorBlood=item.bloodGroup;
                String donorName= item.name;
                String donorNumber=item.mobile;
                String donorEmail=item.email;
                String donorLocation=item.currentLocation;
                String donorOccupation= item.occupation;
                String donorOrganization=item.organizationName;



                Intent intent=new Intent(context,DonorDetailsActivity.class);
                intent.putExtra("Blood",donorBlood);
                intent.putExtra("Name",donorName);
                intent.putExtra("Number",donorNumber);
                intent.putExtra("Email",donorEmail);
                intent.putExtra("Location",donorLocation);
                intent.putExtra("Occupation",donorOccupation);
                intent.putExtra("Organization",donorOrganization);
                context.startActivity(intent);
            }
        });

        // For calling functionality
        String donorNumber=item.mobile;

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + donorNumber));
                context.startActivity(intentCall);
            }
        });


    }

    @Override
    public int getItemCount() {
         return modelClassContactLists.size();
    }
}
