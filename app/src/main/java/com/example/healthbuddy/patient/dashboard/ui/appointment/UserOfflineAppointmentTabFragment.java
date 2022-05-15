package com.example.healthbuddy.patient.dashboard.ui.appointment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.patient.dashboard.ui.appointment.adapter.UserOfflineAppointmentListAdapter;
import com.example.healthbuddy.patient.dashboard.ui.appointment.adapter.UserOnlineAppointmentListAdapter;

import java.util.ArrayList;

public class UserOfflineAppointmentTabFragment extends Fragment {


    public UserOfflineAppointmentTabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        ArrayList<String> appointmentData = new ArrayList<>();
        appointmentData.add("Gaurav Lakade");
        appointmentData.add("Nikhil Kewatkar");
        appointmentData.add("Praktan Raut");
        appointmentData.add("Mangal Thakur");
        appointmentData.add("Manoj Tawlarkar");

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        UserOfflineAppointmentListAdapter adapter = new UserOfflineAppointmentListAdapter(getContext(), appointmentData);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
