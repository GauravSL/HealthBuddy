package com.example.healthbuddy.patient.dashboard.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthbuddy.databinding.BedAvailabilityListBinding;
import com.example.healthbuddy.databinding.DoctorsWithUsBinding;
import com.example.healthbuddy.databinding.FragmentHomeBinding;
import com.example.healthbuddy.databinding.HospitalListBinding;
import com.example.healthbuddy.databinding.UserHomeDashboardBinding;


public class BedAvailableFragment extends Fragment {

    private BedAvailabilityListBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = BedAvailabilityListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}