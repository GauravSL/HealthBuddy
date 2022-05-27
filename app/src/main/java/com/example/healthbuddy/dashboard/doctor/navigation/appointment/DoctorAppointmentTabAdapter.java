package com.example.healthbuddy.dashboard.doctor.navigation.appointment;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.healthbuddy.webservices.model.DoctorAppointmentDetails;
import com.example.healthbuddy.webservices.model.UserAppointmentDetails;

import java.util.ArrayList;

public class DoctorAppointmentTabAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    private ArrayList<DoctorAppointmentDetails> doctorAppointmentsList;

    public DoctorAppointmentTabAdapter(Context context, FragmentManager fm, int totalTabs, ArrayList<DoctorAppointmentDetails> doctorAppointmentsList) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
        this.doctorAppointmentsList = doctorAppointmentsList;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ArrayList<DoctorAppointmentDetails> onlineAppointments = new ArrayList<>();
                for (DoctorAppointmentDetails appointmentDetails : doctorAppointmentsList) {
                    if (appointmentDetails.getAppointmentMode().equalsIgnoreCase("Online")
                            && appointmentDetails.getAppointmentStatus().equalsIgnoreCase("Confirmed")) {
                        onlineAppointments.add(appointmentDetails);
                    }
                }
                return new DoctorOnlineAppointmentTabFragment(onlineAppointments);
            case 1:
                ArrayList<DoctorAppointmentDetails> offLineAppointments = new ArrayList<>();
                for (DoctorAppointmentDetails appointmentDetails : doctorAppointmentsList) {
                    if (appointmentDetails.getAppointmentMode().equalsIgnoreCase("Offline")
                            && appointmentDetails.getAppointmentStatus().equalsIgnoreCase("Confirmed")) {
                        offLineAppointments.add(appointmentDetails);
                    }
                }
                return new DoctorOfflineAppointmentTabFragment(offLineAppointments);
            case 2:
                ArrayList<DoctorAppointmentDetails> rejectedAppointments = new ArrayList<>();
                for (DoctorAppointmentDetails appointmentDetails : doctorAppointmentsList) {
                    if (appointmentDetails.getAppointmentStatus().equalsIgnoreCase("Rejected")
                            || appointmentDetails.getAppointmentStatus().equalsIgnoreCase("Pending")) {
                        rejectedAppointments.add(appointmentDetails);
                    }
                }
                return new DoctorRequestedAppointmentTabFragment(rejectedAppointments);
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