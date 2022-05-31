package com.example.healthbuddy.patient.dashboard.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.webservices.model.BedDetails;
import com.example.healthbuddy.webservices.model.DoctorDetails;

import java.util.ArrayList;

public class DoctorWithUsAdapter extends RecyclerView.Adapter<DoctorWithUsAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<DoctorDetails> doctorDetails;

    public DoctorWithUsAdapter(Context context, ArrayList<DoctorDetails> doctorDetails){
        this.context = context;
        this.doctorDetails = doctorDetails;
    }

    @NonNull
    @Override
    public DoctorWithUsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.doctors_with_us, parent, false);
        return new DoctorWithUsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorWithUsAdapter.ViewHolder holder, int position) {
        DoctorDetails doctorDetail = doctorDetails.get(position);
        holder.txt_doctor_with_us.setText(doctorDetail.getDoctorName());
        holder.txt_doctor_contact.setText(doctorDetail.getDoctorMobile());
        holder.txt_doctor_address.setText(doctorDetail.getDoctorAddress());
    }

    @Override
    public int getItemCount() {
        return doctorDetails.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_doctor_with_us,txt_doctor_contact,txt_doctor_address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_doctor_with_us = itemView.findViewById(R.id.txt_doctor_with_us);
            txt_doctor_contact = itemView.findViewById(R.id.txt_doctor_contact);
            txt_doctor_address = itemView.findViewById(R.id.txt_doctor_address);
        }
    }
}
