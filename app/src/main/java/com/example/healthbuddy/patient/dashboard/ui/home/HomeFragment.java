package com.example.healthbuddy.patient.dashboard.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthbuddy.databinding.FragmentHomeBinding;
import com.example.healthbuddy.databinding.UserHomeDashboardBinding;


public class HomeFragment extends Fragment {

    private UserHomeDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = UserHomeDashboardBinding.inflate(inflater, container, false);

        binding.cvAvailableAmbulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AmbulanceAvailableFragment.class);
                getActivity().startActivity(intent);
            }
        });

        binding.cvAvailableBeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BedAvailableFragment.class);
                getActivity().startActivity(intent);
            }
        });

        binding.cvAvailableHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AvailableHospitalFragment.class);
                getActivity().startActivity(intent);
            }
        });

        binding.cvDoctorWithUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DoctorWithUsFragment.class);
                getActivity().startActivity(intent);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}