package com.example.healthbuddy.patient.dashboard.ui.appointment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;

import java.util.List;

public class AmbulanceAvailableAdapter extends RecyclerView.Adapter<AmbulanceAvailableAdapter.ViewHolder> {

    private Context context;
    private List<String> appointmentData;


    public AmbulanceAvailableAdapter(Context context, List<String> appointmentData){
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.available_ambulance, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ambulanceName = appointmentData.get(position);
        holder.ambulanceName.setText(ambulanceName);
    }

    @Override
    public int getItemCount() {
        return appointmentData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView ambulanceName ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ambulanceName = itemView.findViewById(R.id.txt_ambulance_owner_name);
        }
    }
}