package com.example.healthbuddy.patient.dashboard.ui.appointment.adapter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.healthbuddy.R;
import com.example.healthbuddy.databinding.DoctorAppointmentFragmentBinding;
import com.example.healthbuddy.patient.dashboard.ui.appointment.AppointmentTabAdapter;
import com.example.healthbuddy.webservices.Response;
import com.example.healthbuddy.webservices.ServerDataTransfer;
import com.example.healthbuddy.webservices.model.UserAppointmentDetails;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class AppointmentFragment extends Fragment {

    private DoctorAppointmentFragmentBinding binding;
    private ArrayList userAppointmentsList = new ArrayList();

    @SuppressLint("ResourceAsColor")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = DoctorAppointmentFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        callGetAppointmentsDetails();
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Online"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Offline"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Requested"));
        binding.tabLayout.setTabTextColors(getActivity().getResources().getColor(R.color.white),getActivity().getResources().getColor(R.color.white));


        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        binding.tabLayout.setBackgroundColor(getActivity().getResources().getColor(R.color.purple_500));




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

    AsyncTask<Void, Void, Response> asyncTaskGetAppointments;
    private void callGetAppointmentsDetails(){
        asyncTaskGetAppointments = new AsyncTask<Void, Void, Response>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                binding.progressDialog.setVisibility(View.VISIBLE);
            }

            @Override
            protected Response doInBackground(Void... strings) {
                ServerDataTransfer dataTransfer = new ServerDataTransfer();
                Response response = null;
                try {
                    JSONObject json = new JSONObject();
                    json.put("user_id", "4");
                    response = dataTransfer.accessAPI("userAppointmentList","POST",json.toString());
                } catch (IOException | JSONException exception) {
                    exception.printStackTrace();
                }

                return response;
            }

            @Override
            protected void onPostExecute(Response response) {
                super.onPostExecute(response);
                processAppointmentList(response);
            }
        };
        asyncTaskGetAppointments.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void processAppointmentList(Response response) {
        binding.progressDialog.setVisibility(View.GONE);

        Type type = new TypeToken<ArrayList<UserAppointmentDetails>>() {
        }.getType();
        userAppointmentsList = new Gson().fromJson(response.getResponse(), type);
        if (userAppointmentsList.size() > 0) {
            binding.tabContainer.setVisibility(View.VISIBLE);
            final AppointmentTabAdapter adapter = new AppointmentTabAdapter(getContext(), requireActivity().getSupportFragmentManager(), binding.tabLayout.getTabCount(), userAppointmentsList);
            binding.viewPager.setAdapter(adapter);
        } else {
            binding.txtNoAppointment.setVisibility(View.VISIBLE);
        }
    }
}