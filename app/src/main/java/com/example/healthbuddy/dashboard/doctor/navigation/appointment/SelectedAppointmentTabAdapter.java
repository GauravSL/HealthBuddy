package com.example.healthbuddy.dashboard.doctor.navigation.appointment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.healthbuddy.databinding.DoctorAppointmentFragmentBinding;
import com.google.android.material.tabs.TabLayout;

public class SelectedAppointmentTabAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public SelectedAppointmentTabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AppointmentDetailsFragment appointmentDetailsFragment = new AppointmentDetailsFragment();
                return appointmentDetailsFragment;
            case 1:
                ChatFragment chatFragment = new ChatFragment();
                return chatFragment;

            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}