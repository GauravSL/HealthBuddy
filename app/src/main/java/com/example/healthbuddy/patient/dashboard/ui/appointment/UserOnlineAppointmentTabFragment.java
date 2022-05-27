package com.example.healthbuddy.patient.dashboard.ui.appointment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.patient.dashboard.ui.appointment.adapter.UserOnlineAppointmentListAdapter;
import com.example.healthbuddy.webservices.model.UserAppointmentDetails;

import java.util.ArrayList;

public class UserOnlineAppointmentTabFragment extends Fragment {

    ArrayList<UserAppointmentDetails> userAppointmentsList;
    public UserOnlineAppointmentTabFragment(ArrayList<UserAppointmentDetails> userAppointmentsList) {
         this.userAppointmentsList = userAppointmentsList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        UserOnlineAppointmentListAdapter adapter = new UserOnlineAppointmentListAdapter(getContext(), userAppointmentsList);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
