package com.example.healthbuddy.patient.dashboard.ui.appointment.adapter;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.healthbuddy.R;
import com.example.healthbuddy.databinding.DoctorAppointmentFragmentBinding;
import com.example.healthbuddy.patient.dashboard.ui.appointment.AppointmentTabAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class AppointmentFragment extends Fragment {

    private DoctorAppointmentFragmentBinding binding;

    @SuppressLint("ResourceAsColor")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = DoctorAppointmentFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Online"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Offline"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Requested"));
        binding.tabLayout.setTabTextColors(getActivity().getResources().getColor(R.color.white),getActivity().getResources().getColor(R.color.white));


        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        binding.tabLayout.setBackgroundColor(getActivity().getResources().getColor(R.color.purple_500));


        final AppointmentTabAdapter adapter = new AppointmentTabAdapter(getContext(), requireActivity().getSupportFragmentManager(), binding.tabLayout.getTabCount());
        binding.viewPager.setAdapter(adapter);

        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}