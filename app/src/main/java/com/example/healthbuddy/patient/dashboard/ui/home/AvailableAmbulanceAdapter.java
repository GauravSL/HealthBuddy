package com.example.healthbuddy.patient.dashboard.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.webservices.model.AmbulanceDetails;
import com.example.healthbuddy.webservices.model.HospitalDetails;

import java.util.ArrayList;

public class AvailableAmbulanceAdapter extends RecyclerView.Adapter<AvailableAmbulanceAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<AmbulanceDetails> ambulanceDetails;

    public AvailableAmbulanceAdapter(Context context, ArrayList<AmbulanceDetails> ambulanceDetails){
        this.context = context;
        this.ambulanceDetails = ambulanceDetails;
    }

    @NonNull
    @Override
    public AvailableAmbulanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.available_ambulance, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableAmbulanceAdapter.ViewHolder holder, int position) {
        AmbulanceDetails ambulanceDetail = ambulanceDetails.get(position);
        holder.txt_ambulance_owner_name.setText(ambulanceDetail.getAmbulance_driver());
        holder.txt_ambulance_contact.setText(ambulanceDetail.getAmbulance_contact());
        holder.txt_seats_available.setText(ambulanceDetail.getAbmulance_capacity());
        holder.txt_ambulance_address.setText(ambulanceDetail.getAmbulance_address());

    }

    @Override
    public int getItemCount() {
        return ambulanceDetails.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_ambulance_owner_name,txt_ambulance_contact,txt_ambulance_address,txt_seats_available;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_ambulance_owner_name = itemView.findViewById(R.id.txt_ambulance_owner_name);
            txt_ambulance_contact = itemView.findViewById(R.id.txt_ambulance_contact);
            txt_seats_available = itemView.findViewById(R.id.txt_seats_available);
            txt_ambulance_address = itemView.findViewById(R.id.txt_ambulance_address);
        }
    }
}
