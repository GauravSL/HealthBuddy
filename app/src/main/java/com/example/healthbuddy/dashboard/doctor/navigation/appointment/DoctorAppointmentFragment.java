package com.example.healthbuddy.dashboard.doctor.navigation.appointment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.healthbuddy.databinding.DoctorAppointmentFragmentBinding;
import com.example.healthbuddy.webservices.Response;
import com.example.healthbuddy.webservices.ServerDataTransfer;
import com.example.healthbuddy.webservices.model.DoctorAppointmentDetails;
import com.example.healthbuddy.webservices.model.UserAppointmentDetails;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DoctorAppointmentFragment extends Fragment {

    private DoctorAppointmentFragmentBinding binding;
    private ArrayList<DoctorAppointmentDetails> doctorAppointmentsList = new ArrayList();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       // HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = DoctorAppointmentFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        callGetAppointmentsDetails();

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Online"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Offline"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Requested"));
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

      //  final AppointmentTabAdapter adapter = new AppointmentTabAdapter(getContext(), requireActivity().getSupportFragmentManager(), binding.tabLayout.getTabCount());
      //  binding.viewPager.setAdapter(adapter);

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
                    json.put("doctor_id", "2");
                    response = dataTransfer.accessAPI("doctorAppointmentList","POST",json.toString());
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

        Type type = new TypeToken<ArrayList<DoctorAppointmentDetails>>() {
        }.getType();
        doctorAppointmentsList = new Gson().fromJson(response.getResponse(), type);
        if (doctorAppointmentsList.size() > 0) {
            binding.tabContainer.setVisibility(View.VISIBLE);
            final DoctorAppointmentTabAdapter adapter = new DoctorAppointmentTabAdapter(getContext(), requireActivity().getSupportFragmentManager(), binding.tabLayout.getTabCount(), doctorAppointmentsList);
            binding.viewPager.setAdapter(adapter);
        } else {
            binding.txtNoAppointment.setVisibility(View.VISIBLE);
        }
    }
}