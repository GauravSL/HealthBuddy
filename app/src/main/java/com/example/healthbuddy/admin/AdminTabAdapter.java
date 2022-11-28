package com.example.healthbuddy.admin;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.healthbuddy.dashboard.doctor.navigation.appointment.AppointmentDetailsFragment;
import com.example.healthbuddy.dashboard.doctor.navigation.appointment.ChatFragment;
import com.example.healthbuddy.webservices.model.DoctorAppointmentDetails;
import com.example.healthbuddy.webservices.model.UserAppointmentDetails;

public class AdminTabAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public AdminTabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ApproveDoctorFragment( );
            case 1:
                return new ApproveOrderFragment( );

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