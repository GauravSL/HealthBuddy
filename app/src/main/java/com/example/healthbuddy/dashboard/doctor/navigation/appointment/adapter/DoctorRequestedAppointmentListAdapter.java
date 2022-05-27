package com.example.healthbuddy.dashboard.doctor.navigation.appointment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.webservices.model.DoctorAppointmentDetails;

import java.util.ArrayList;
import java.util.List;

public class DoctorRequestedAppointmentListAdapter extends RecyclerView.Adapter<DoctorRequestedAppointmentListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DoctorAppointmentDetails> doctorAppointmentsList;

    public DoctorRequestedAppointmentListAdapter(Context context,  ArrayList<DoctorAppointmentDetails> doctorAppointmentsList){
         this.context = context;
         this.doctorAppointmentsList = doctorAppointmentsList;
    }

    @NonNull
    @Override
    public DoctorRequestedAppointmentListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.doctor_requested_appointment_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorRequestedAppointmentListAdapter.ViewHolder holder, int position) {
        DoctorAppointmentDetails appointmentDetails = doctorAppointmentsList.get(position);
        holder.patientName.setText(appointmentDetails.getUserName());
        holder.doctorContact.setText(appointmentDetails.getUserMobile());
        holder.txtAppointmentId.setText(appointmentDetails.getAppointmentId());
        holder.appointmentDate.setText(appointmentDetails.getAppointmentDate().split("T")[0]);
        holder.appointmentTime.setText(appointmentDetails.getAppointmentDate().split("T")[1]);
        holder.txtPatientHospitalAddress.setText(appointmentDetails.getUserAddress());
        if (appointmentDetails.getAppointmentStatus().equalsIgnoreCase("Rejected")){
            holder.rejectionContainer.setVisibility(View.VISIBLE);
            holder.buttonContainer.setVisibility(View.GONE);
            holder.txtRejectionReason.setText(appointmentDetails.getRejectionReason());
        }else{
            holder.rejectionContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return doctorAppointmentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView patientName, txtAppointmentId, doctorContact, appointmentDate,  appointmentTime, txtRejectionReason,
                txtPatientHospitalToVisit, txtPatientHospitalAddress;
        LinearLayout buttonContainer, rejectionContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.txtPatientName);
            doctorContact = itemView.findViewById(R.id.txtPatientContact);
            txtAppointmentId = itemView.findViewById(R.id.txtAppointmentId);
            appointmentDate = itemView.findViewById(R.id.txtPatientSlotDate);
            appointmentTime = itemView.findViewById(R.id.txtPatientSlotTime);
            txtRejectionReason = itemView.findViewById(R.id.txtRejectionReason);
            txtPatientHospitalToVisit = itemView.findViewById(R.id.txtPatientHospitalToVisit);
            txtPatientHospitalAddress = itemView.findViewById(R.id.txtPatientHospitalAddress);
            buttonContainer = itemView.findViewById(R.id.buttonContainer);
            rejectionContainer = itemView.findViewById(R.id.rejectionContainer);
        }
    }
}
