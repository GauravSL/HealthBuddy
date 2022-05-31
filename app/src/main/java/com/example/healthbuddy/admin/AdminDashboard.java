package com.example.healthbuddy.admin;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthbuddy.dashboard.doctor.navigation.appointment.SelectedAppointmentTabAdapter;
import com.example.healthbuddy.databinding.DoctorAppointmentFragmentBinding;
import com.example.healthbuddy.webservices.model.DoctorAppointmentDetails;
import com.example.healthbuddy.webservices.model.UserAppointmentDetails;
import com.google.android.material.tabs.TabLayout;

public class AdminDashboard extends AppCompatActivity {
    private DoctorAppointmentFragmentBinding binding;
    private DoctorAppointmentDetails doctorAppointmentDetails = null;
    private UserAppointmentDetails userAppointmentDetails = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DoctorAppointmentFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        doctorAppointmentDetails = (DoctorAppointmentDetails) getIntent().getSerializableExtra("appointmentDetails");
        userAppointmentDetails = (UserAppointmentDetails) getIntent().getSerializableExtra("appointmentDetailsUser");

        binding.progressDialog.setVisibility(View.GONE);
        binding.tabContainer.setVisibility(View.VISIBLE);

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Doctors"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Orders"));
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final AdminTabAdapter adapter = new AdminTabAdapter(this , getSupportFragmentManager(), binding.tabLayout.getTabCount(),
                doctorAppointmentDetails, userAppointmentDetails);
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

    }
}