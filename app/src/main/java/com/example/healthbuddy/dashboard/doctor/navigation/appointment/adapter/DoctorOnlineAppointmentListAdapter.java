package com.example.healthbuddy.dashboard.doctor.navigation.appointment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;

import java.util.List;

public class DoctorOnlineAppointmentListAdapter extends RecyclerView.Adapter<DoctorOnlineAppointmentListAdapter.ViewHolder> {

    private Context context;
    private List<String> appointmentData;

    public DoctorOnlineAppointmentListAdapter(Context context, List<String> appointmentData){
         this.context = context;
         this.appointmentData = appointmentData;
    }

    @NonNull
    @Override
    public DoctorOnlineAppointmentListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.doctor_online_appointment_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorOnlineAppointmentListAdapter.ViewHolder holder, int position) {
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
