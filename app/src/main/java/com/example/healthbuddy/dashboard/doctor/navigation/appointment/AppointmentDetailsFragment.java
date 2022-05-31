package com.example.healthbuddy.dashboard.doctor.navigation.appointment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.healthbuddy.databinding.AppointmentDetailsFragmentBinding;
import com.example.healthbuddy.webservices.model.DoctorAppointmentDetails;
import com.example.healthbuddy.webservices.model.UserAppointmentDetails;

public class AppointmentDetailsFragment extends Fragment {
    private DoctorAppointmentDetails appointmentDetails;
    private UserAppointmentDetails userAppointmentDetails;
    public AppointmentDetailsFragment(DoctorAppointmentDetails appointmentDetails,
                                       UserAppointmentDetails userAppointmentDetails){
        this.appointmentDetails = appointmentDetails;
        this.userAppointmentDetails = userAppointmentDetails;
    }

    AppointmentDetailsFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AppointmentDetailsFragmentBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        if (appointmentDetails!=null) {
            binding.txtPatientName.setText(appointmentDetails.getUserName());
            binding.txtPatientContact.setText(appointmentDetails.getUserMobile());
            binding.txtAppointmentId.setText(appointmentDetails.getAppointmentId());
            binding.txtPatientSlotDate.setText(appointmentDetails.getAppointmentDate().split("T")[0]);
            binding.txtPatientSlotDate.setText(appointmentDetails.getAppointmentDate().split("T")[1]);
            binding.txtPatientHospitalAddress.setText(appointmentDetails.getUserAddress());
        }else if (userAppointmentDetails != null){
            binding.txtPatientName.setText(userAppointmentDetails.getDoctorName());
            binding.txtPatientContact.setText(userAppointmentDetails.getDoctorMobile());
            binding.txtAppointmentId.setText(userAppointmentDetails.getAppointmentId());
            binding.txtPatientSlotDate.setText(userAppointmentDetails.getAppointmentDate().split("T")[0]);
            binding.txtPatientSlotDate.setText(userAppointmentDetails.getAppointmentDate().split("T")[1]);
            binding.txtPatientHospitalAddress.setText(userAppointmentDetails.getDoctorAddress());
        }
    }
}
