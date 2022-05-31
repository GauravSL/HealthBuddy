package com.example.healthbuddy.admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.dashboard.doctor.navigation.appointment.SelectedAppointmentActivity;
import com.example.healthbuddy.webservices.model.DoctorAppointmentDetails;

import java.util.ArrayList;

public class ApproveDoctorAdapter extends RecyclerView.Adapter<ApproveDoctorAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DoctorAppointmentDetails> doctorAppointmentsList;

    public ApproveDoctorAdapter(Context context, ArrayList<DoctorAppointmentDetails> doctorAppointmentsList){
        this.context = context;
        this.doctorAppointmentsList = doctorAppointmentsList;
    }

    @NonNull
    @Override
    public ApproveDoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.admin_doctor_approval, parent, false);
        return new ApproveDoctorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApproveDoctorAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}

