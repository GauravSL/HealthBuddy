package com.example.healthbuddy.dashboard.doctor.navigation.appointment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.webservices.model.DoctorAppointmentDetails;

import java.util.ArrayList;
import java.util.List;

public class DoctorOfflineAppointmentListAdapter extends RecyclerView.Adapter<DoctorOfflineAppointmentListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DoctorAppointmentDetails> doctorAppointmentsList;

    public DoctorOfflineAppointmentListAdapter(Context context, ArrayList<DoctorAppointmentDetails> doctorAppointmentsList){
         this.context = context;
         this.doctorAppointmentsList = doctorAppointmentsList;
    }

    @NonNull
    @Override
    public DoctorOfflineAppointmentListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.doctor_offline_appointment_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorOfflineAppointmentListAdapter.ViewHolder holder, int position) {
        DoctorAppointmentDetails appointmentDetails = doctorAppointmentsList.get(position);
        holder.patientName.setText(appointmentDetails.getUserName());
        holder.doctorContact.setText(appointmentDetails.getUserMobile());
        holder.txtAppointmentId.setText(appointmentDetails.getAppointmentId());
        holder.appointmentDate.setText(appointmentDetails.getAppointmentDate().split("T")[0]);
        holder.appointmentTime.setText(appointmentDetails.getAppointmentDate().split("T")[1]);
        holder.txtPatientHospitalAddress.setText(appointmentDetails.getUserAddress());
    }

    @Override
    public int getItemCount() {
        return doctorAppointmentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView patientName, txtAppointmentId, doctorContact, appointmentDate,  appointmentTime,
                txtPatientHospitalToVisit, txtPatientHospitalAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.txtPatientName);
            doctorContact = itemView.findViewById(R.id.txtPatientContact);
            txtAppointmentId = itemView.findViewById(R.id.txtAppointmentId);
            appointmentDate = itemView.findViewById(R.id.txtPatientSlotDate);
            appointmentTime = itemView.findViewById(R.id.txtPatientSlotTime);
            txtPatientHospitalToVisit = itemView.findViewById(R.id.txtPatientHospitalToVisit);
            txtPatientHospitalAddress = itemView.findViewById(R.id.txtPatientHospitalAddress);
        }
    }
}
