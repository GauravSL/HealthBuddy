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
import com.example.healthbuddy.webservices.model.DoctorAppointmentDetails;
import com.example.healthbuddy.webservices.model.UserAppointmentDetails;
import com.google.android.material.tabs.TabLayout;

public class SelectedAppointmentTabAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    private DoctorAppointmentDetails doctorAppointmentDetails;
    private UserAppointmentDetails userAppointmentDetails;

    public SelectedAppointmentTabAdapter(Context context, FragmentManager fm, int totalTabs,
                                         DoctorAppointmentDetails doctorAppointmentDetails,
                                         UserAppointmentDetails userAppointmentDetails) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
        this.doctorAppointmentDetails = doctorAppointmentDetails;
        this.userAppointmentDetails = userAppointmentDetails;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AppointmentDetailsFragment(doctorAppointmentDetails, userAppointmentDetails);
            case 1:
                ChatFragment fragment = null;
                if (doctorAppointmentDetails!=null){
                    fragment = new ChatFragment(doctorAppointmentDetails.getAppointmentId(), "");
                }else if(userAppointmentDetails!=null){
                    fragment = new ChatFragment("", userAppointmentDetails.getAppointmentId());
                }
                return fragment;

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