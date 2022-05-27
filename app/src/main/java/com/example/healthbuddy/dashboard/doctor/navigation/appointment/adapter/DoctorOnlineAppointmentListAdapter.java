package com.example.healthbuddy.dashboard.doctor.navigation.appointment.adapter;

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
import com.example.healthbuddy.webservices.model.UserAppointmentDetails;

import java.util.ArrayList;
import java.util.List;

public class DoctorOnlineAppointmentListAdapter extends RecyclerView.Adapter<DoctorOnlineAppointmentListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DoctorAppointmentDetails> doctorAppointmentsList;

    public DoctorOnlineAppointmentListAdapter(Context context, ArrayList<DoctorAppointmentDetails> doctorAppointmentsList){
         this.context = context;
         this.doctorAppointmentsList = doctorAppointmentsList;
    }

    @NonNull
    @Override
    public DoctorOnlineAppointmentListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);View view = mInflater.inflate(R.layout.doctor_online_appointment_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorOnlineAppointmentListAdapter.ViewHolder holder, int position) {
        DoctorAppointmentDetails appointmentDetails = doctorAppointmentsList.get(position);
        holder.txtAppointmentId.setText(appointmentDetails.getAppointmentId());
        holder.txtPatientName.setText(appointmentDetails.getUserName());
        holder.txtPatientContact.setText(appointmentDetails.getUserMobile());
        holder.txtPatientSlotDate.setText(appointmentDetails.getAppointmentDate().split("T")[0]);
        holder.txtPatientSlotTime.setText(appointmentDetails.getAppointmentDate().split("T")[1]);
        holder.itemView.setOnClickListener(view ->{
            Intent intent = new Intent(context, SelectedAppointmentActivity.class);
            context.startActivity(intent);
                });
    }

    @Override
    public int getItemCount() {
        return doctorAppointmentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtPatientName, txtAppointmentId, txtPatientContact,txtPatientSlotDate,txtPatientSlotTime ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAppointmentId = itemView.findViewById(R.id.txtAppointmentId);
            txtPatientName = itemView.findViewById(R.id.txtPatientName);
            txtPatientContact = itemView.findViewById(R.id.txtPatientContact);
            txtPatientSlotDate = itemView.findViewById(R.id.txtPatientSlotDate);
            txtPatientSlotTime = itemView.findViewById(R.id.txtPatientSlotTime);

        }
    }
}
