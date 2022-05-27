package com.example.healthbuddy.dashboard.doctor.navigation.appointment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.dashboard.doctor.navigation.appointment.adapter.DoctorOnlineAppointmentListAdapter;
import com.example.healthbuddy.webservices.model.DoctorAppointmentDetails;
import com.example.healthbuddy.webservices.model.UserAppointmentDetails;

import java.util.ArrayList;

public class DoctorOnlineAppointmentTabFragment extends Fragment {

    private final ArrayList<DoctorAppointmentDetails> doctorAppointmentsList;

    public DoctorOnlineAppointmentTabFragment(ArrayList<DoctorAppointmentDetails> doctorAppointmentsList) {
       this.doctorAppointmentsList =doctorAppointmentsList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DoctorOnlineAppointmentListAdapter adapter = new DoctorOnlineAppointmentListAdapter(getContext(), doctorAppointmentsList);
        recyclerView.setAdapter(adapter);
        return view;
    }

}