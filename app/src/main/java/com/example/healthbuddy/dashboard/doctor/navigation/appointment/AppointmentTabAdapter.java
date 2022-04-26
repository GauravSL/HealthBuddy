package com.example.healthbuddy.dashboard.doctor.navigation.appointment;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AppointmentTabAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public AppointmentTabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DoctorOnlineAppointmentTabFragment doctorOnlineAppointmentTabFragment = new DoctorOnlineAppointmentTabFragment();
                return doctorOnlineAppointmentTabFragment;
            case 1:
                DoctorOfflineAppointmentTabFragment doctorOfflineAppointmentTabFragment = new DoctorOfflineAppointmentTabFragment();
                return doctorOfflineAppointmentTabFragment;
            case 2:
                DoctorRequestedAppointmentTabFragment DoctorRequestedAppointmentTabFragment = new DoctorRequestedAppointmentTabFragment();
                return DoctorRequestedAppointmentTabFragment;
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