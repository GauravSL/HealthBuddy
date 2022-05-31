package com.example.healthbuddy.patient.dashboard.ui.appointment.adapter;

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
import com.example.healthbuddy.webservices.model.UserAppointmentDetails;

import java.util.ArrayList;
import java.util.List;

public class UserOnlineAppointmentListAdapter extends RecyclerView.Adapter<UserOnlineAppointmentListAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<UserAppointmentDetails> userAppointmentsList;

    public UserOnlineAppointmentListAdapter(Context context, ArrayList<UserAppointmentDetails> userAppointmentsList){
        this.context = context;
        this.userAppointmentsList = userAppointmentsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.user_online_appointment_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserAppointmentDetails appointmentDetails = userAppointmentsList.get(position);
        holder.doctorName.setText(appointmentDetails.getDoctorName());
        holder.doctorContact.setText(appointmentDetails.getDoctorMobile());
        holder.txtAppointmentId.setText(appointmentDetails.getAppointmentId());
        holder.appointmentDate.setText(appointmentDetails.getAppointmentDate().split("T")[0]);
        holder.appointmentTime.setText(appointmentDetails.getAppointmentDate().split("T")[1]);
        holder.itemView.setOnClickListener(view ->{
            Intent intent = new Intent(context, SelectedAppointmentActivity.class);
            intent.putExtra("appointmentDetailsUser", appointmentDetails);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userAppointmentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView doctorName, txtAppointmentId, doctorContact, appointmentDate,  appointmentTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(R.id.txtPatientName);
            doctorContact = itemView.findViewById(R.id.txtPatientContact);
            txtAppointmentId = itemView.findViewById(R.id.txtAppointmentId);
            appointmentDate = itemView.findViewById(R.id.txtPatientSlotDate);
            appointmentTime = itemView.findViewById(R.id.txtPatientSlotTime);
        }
    }
}