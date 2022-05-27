package com.example.healthbuddy.patient.dashboard.ui.appointment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.webservices.model.UserAppointmentDetails;

import java.util.ArrayList;
import java.util.List;

public class UserRequestedAppointmentListAdapter extends RecyclerView.Adapter<UserRequestedAppointmentListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<UserAppointmentDetails> userAppointmentsList;

    public UserRequestedAppointmentListAdapter(Context context, ArrayList<UserAppointmentDetails> userAppointmentsList){
        this.context = context;
        this.userAppointmentsList = userAppointmentsList;
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
        UserAppointmentDetails appointmentDetails = userAppointmentsList.get(position);
        holder.doctorName.setText(appointmentDetails.getDoctorName());
        holder.doctorContact.setText(appointmentDetails.getDoctorMobile());
        holder.txtAppointmentId.setText(appointmentDetails.getAppointmentId());
        holder.appointmentDate.setText(appointmentDetails.getAppointmentDate().split("T")[0]);
        holder.appointmentTime.setText(appointmentDetails.getAppointmentDate().split("T")[1]);
        holder.txtPatientHospitalToVisit.setText(appointmentDetails.getHospital_name());
        holder.txtPatientHospitalAddress.setText(appointmentDetails.getDoctorAddress());
        if (appointmentDetails.getAppointment_status().equalsIgnoreCase("Rejected")){
            holder.rejectionContainer.setVisibility(View.VISIBLE);
            holder.txtRejectionReason.setText(appointmentDetails.getRejection_reason());
        }else{
            holder.rejectionContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return userAppointmentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView doctorName, txtAppointmentId, doctorContact, appointmentDate,  appointmentTime, txtRejectionReason,
                txtPatientHospitalToVisit, txtPatientHospitalAddress;
        LinearLayout buttonContainer, rejectionContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(R.id.txtPatientName);
            doctorContact = itemView.findViewById(R.id.txtPatientContact);
            txtAppointmentId = itemView.findViewById(R.id.txtAppointmentId);
            appointmentDate = itemView.findViewById(R.id.txtPatientSlotDate);
            appointmentTime = itemView.findViewById(R.id.txtPatientSlotTime);
            txtRejectionReason = itemView.findViewById(R.id.txtRejectionReason);
            txtPatientHospitalToVisit = itemView.findViewById(R.id.txtPatientHospitalToVisit);
            txtPatientHospitalAddress = itemView.findViewById(R.id.txtPatientHospitalAddress);
            buttonContainer = itemView.findViewById(R.id.buttonContainer);
            rejectionContainer = itemView.findViewById(R.id.rejectionContainer);
            buttonContainer.setVisibility(View.GONE);
        }
    }
}