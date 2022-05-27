package com.example.healthbuddy.dashboard.doctor.navigation.appointment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.dashboard.doctor.navigation.appointment.adapter.DoctorOfflineAppointmentListAdapter;
import com.example.healthbuddy.dashboard.doctor.navigation.appointment.adapter.DoctorRequestedAppointmentListAdapter;
import com.example.healthbuddy.webservices.model.DoctorAppointmentDetails;
import com.example.healthbuddy.webservices.model.UserAppointmentDetails;

import java.util.ArrayList;

public class DoctorOfflineAppointmentTabFragment extends Fragment {

    private ArrayList<DoctorAppointmentDetails> doctorAppointmentsList;

    public DoctorOfflineAppointmentTabFragment(ArrayList<DoctorAppointmentDetails> doctorAppointmentsList) {
        this.doctorAppointmentsList =doctorAppointmentsList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DoctorOfflineAppointmentListAdapter adapter = new DoctorOfflineAppointmentListAdapter(getContext(), doctorAppointmentsList);
        recyclerView.setAdapter(adapter);
        return view;
    }

}