package com.example.healthbuddy.patient.dashboard.ui.appointment;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.healthbuddy.patient.dashboard.ui.appointment.UserOnlineAppointmentTabFragment;
import com.example.healthbuddy.patient.dashboard.ui.appointment.UserOfflineAppointmentTabFragment;
import com.example.healthbuddy.patient.dashboard.ui.appointment.UserRequestedAppointmentTabFragment;

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
                UserOnlineAppointmentTabFragment userOnlineAppointmentTabFragment = new UserOnlineAppointmentTabFragment();
                return userOnlineAppointmentTabFragment;
            case 1:
                UserOfflineAppointmentTabFragment userOfflineAppointmentTabFragment = new UserOfflineAppointmentTabFragment();
                return userOfflineAppointmentTabFragment;
            case 2:
                UserRequestedAppointmentTabFragment userRequestedAppointmentTabFragment = new UserRequestedAppointmentTabFragment();
                return userRequestedAppointmentTabFragment;
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
