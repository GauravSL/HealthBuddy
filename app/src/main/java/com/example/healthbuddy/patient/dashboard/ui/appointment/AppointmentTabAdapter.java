package com.example.healthbuddy.patient.dashboard.ui.appointment;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.healthbuddy.patient.dashboard.ui.appointment.UserOnlineAppointmentTabFragment;
import com.example.healthbuddy.patient.dashboard.ui.appointment.UserOfflineAppointmentTabFragment;
import com.example.healthbuddy.patient.dashboard.ui.appointment.UserRequestedAppointmentTabFragment;
import com.example.healthbuddy.webservices.model.UserAppointmentDetails;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AppointmentTabAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    private ArrayList<UserAppointmentDetails> userAppointmentsList;

    public AppointmentTabAdapter(Context context, FragmentManager fm, int totalTabs, ArrayList<UserAppointmentDetails> userAppointmentsList) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
        this.userAppointmentsList = userAppointmentsList;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ArrayList<UserAppointmentDetails> onlineAppointments = new ArrayList<>();
                for (UserAppointmentDetails appointmentDetails : userAppointmentsList) {
                    if (appointmentDetails.getAppointmentMode().equalsIgnoreCase("Online")
                            && appointmentDetails.getAppointment_status().equalsIgnoreCase("Confirmed")) {
                        onlineAppointments.add(appointmentDetails);
                    }
                }
                return new UserOnlineAppointmentTabFragment(onlineAppointments);
            case 1:
                ArrayList<UserAppointmentDetails> offLineAppointments = new ArrayList<>();
                for (UserAppointmentDetails appointmentDetails : userAppointmentsList) {
                    if (appointmentDetails.getAppointmentMode().equalsIgnoreCase("Offline")
                            && appointmentDetails.getAppointment_status().equalsIgnoreCase("Confirmed")) {
                        offLineAppointments.add(appointmentDetails);
                    }
                }
                return new UserOfflineAppointmentTabFragment(offLineAppointments);
            case 2:
                ArrayList<UserAppointmentDetails> rejectedAppointments = new ArrayList<>();
                for (UserAppointmentDetails appointmentDetails : userAppointmentsList) {
                    if (appointmentDetails.getAppointment_status().equalsIgnoreCase("Rejected")
                            || appointmentDetails.getAppointment_status().equalsIgnoreCase("Pending")) {
                        rejectedAppointments.add(appointmentDetails);
                    }
                }
                return new UserRequestedAppointmentTabFragment(rejectedAppointments);
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
