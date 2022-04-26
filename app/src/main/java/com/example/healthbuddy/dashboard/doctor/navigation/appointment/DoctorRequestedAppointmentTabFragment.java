package com.example.healthbuddy.dashboard.doctor.navigation.appointment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.healthbuddy.R;

public class DoctorRequestedAppointmentTabFragment extends Fragment {


    public DoctorRequestedAppointmentTabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.doctor_requested_appointment_adapter_item, container, false);
    }

}