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

public class ApproveOrderAdapter extends RecyclerView.Adapter<ApproveOrderAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DoctorAppointmentDetails> doctorAppointmentsList;

    public ApproveOrderAdapter(Context context, ArrayList<DoctorAppointmentDetails> doctorAppointmentsList){
        this.context = context;
        this.doctorAppointmentsList = doctorAppointmentsList;
    }

    @NonNull
    @Override
    public ApproveOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.admin_order_approval, parent, false);
        return new ApproveOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApproveOrderAdapter.ViewHolder holder, int position) {

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

