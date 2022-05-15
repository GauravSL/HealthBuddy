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

public class UserRequestedAppointmentListAdapter extends RecyclerView.Adapter<UserRequestedAppointmentListAdapter.ViewHolder> {

    private Context context;
    private List<String> appointmentData;

    public UserRequestedAppointmentListAdapter(Context context, List<String> appointmentData){
        this.context = context;
        this.appointmentData = appointmentData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.user_requested_appointment_adapter_item, parent, false);
        return new com.example.healthbuddy.patient.dashboard.ui.appointment.adapter.UserRequestedAppointmentListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String patientName = appointmentData.get(position);
        holder.patientName.setText(patientName);
    }

    @Override
    public int getItemCount() {
        return appointmentData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView patientName ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.txtPatientName);
        }
    }
}