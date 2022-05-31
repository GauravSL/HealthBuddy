package com.example.healthbuddy.dashboard.doctor.navigation.profile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthbuddy.databinding.DoctorProfileBinding;
import com.example.healthbuddy.webservices.Constants;
import com.example.healthbuddy.webservices.Response;
import com.example.healthbuddy.webservices.ServerDataTransfer;
import com.example.healthbuddy.webservices.model.DoctorDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class DoctorProfileFragment extends Fragment {

    private DoctorProfileBinding binding;
    private ArrayList<DoctorDetails> doctorList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DoctorProfileBinding.inflate(inflater, container, false);
        initView();
        callGetDoctorDetails();

        return binding.getRoot();
    }

    private void initView() {
        binding.btnSubmit.setText("Edit");
        handleEdittextEnable(false);
        binding.btnSubmit.setOnClickListener(v -> {
            if (binding.btnSubmit.getText().toString().equalsIgnoreCase("Edit")){
                binding.btnSubmit.setText("Submit");
                handleEdittextEnable(true);
            } else if (binding.btnSubmit.getText().toString().equalsIgnoreCase("Submit")){
                handleEdittextEnable(false);
                callDoctorUpdateService();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    AsyncTask<Void, Void, Response> asyncTaskGetDoctor;
    private void callGetDoctorDetails(){
        asyncTaskGetDoctor = new AsyncTask<Void, Void, Response>() {
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
                    json.put("id", Constants.doctorDetails.getId());
                    response = dataTransfer.accessAPI("getDoctorDetails","POST",json.toString());                } catch (IOException | JSONException exception) {
                    exception.printStackTrace();
                }

                return response;
            }

            @Override
            protected void onPostExecute(Response response) {
                super.onPostExecute(response);
                processDoctorList(response);
            }
        };
        asyncTaskGetDoctor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void processDoctorList(Response response){
        binding.progressDialog.setVisibility(View.GONE);
        if (response.getStatusCode()==200) {
            Type type = new TypeToken<ArrayList<DoctorDetails>>() {
            }.getType();
            doctorList = new Gson().fromJson(response.getResponse(), type);
            setUIValues();
        }else{
            Toast.makeText(getContext(), response.getResponse(), Toast.LENGTH_LONG).show();
        }
    }

    AsyncTask<Void, Void, Response> asyncTaskUpdateUserDetails;
    private void callDoctorUpdateService(){
        asyncTaskUpdateUserDetails = new AsyncTask<Void, Void, Response>() {
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
                    /*  apiDATA.put(Constants.AUTHORIZATION, "");*/
                    // String categoryId = impactedCategory.getCategoryId();
                    JSONObject json = new JSONObject();
                    json.put("id", Constants.doctorDetails.getId());
                    json.put("doctor_name", binding.etDoctorName.getText().toString());
                    json.put("doctor_email", binding.etDoctorMail.getText().toString());
                    json.put("doctor_mobile", binding.etDoctorMob.getText().toString());
                    json.put("doctor_address",binding.etDoctorAddress.getText().toString());
                    json.put("doctor_speciality",binding.etDoctorDegree.getText().toString());
                    json.put("hospital_name",binding.etDoctorHospital.getText().toString());

                    response = dataTransfer.accessAPI("updateDoctorDetails","POST",json.toString());


                } catch (IOException | JSONException exception) {
                    exception.printStackTrace();
                }

                return response;
            }

            @Override
            protected void onPostExecute(Response response) {
                super.onPostExecute(response);
                processUpdateResponse(response);
            }
        };
        asyncTaskUpdateUserDetails.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void processUpdateResponse(Response response){
        binding.progressDialog.setVisibility(View.GONE);
        Toast.makeText(getContext(), response.getResponse(), Toast.LENGTH_LONG).show();
    }

    private void setUIValues() {
        binding.etDoctorName.setText(doctorList.get(0).getDoctorName());
        binding.etDoctorMail.setText(doctorList.get(0).getDoctorEmail());
        binding.etDoctorMob.setText(doctorList.get(0).getDoctorMobile());
        binding.etDoctorDegree.setText(doctorList.get(0).getDoctorSpeciality());
        binding.etDoctorAddress.setText(doctorList.get(0).getDoctorAddress());
        binding.etDoctorHospital.setText(doctorList.get(0).getHospital_name());
    }

    private void handleEdittextEnable(Boolean flag) {
        binding.etDoctorName.setEnabled(flag);
        binding.etDoctorName.setClickable(flag);
        binding.etDoctorMail.setEnabled(flag);
        binding.etDoctorMail.setClickable(flag);
        binding.etDoctorMob.setEnabled(flag);
        binding.etDoctorMob.setClickable(flag);
        binding.etDoctorDegree.setEnabled(flag);
        binding.etDoctorDegree.setClickable(flag);
        binding.etDoctorAddress.setEnabled(flag);
        binding.etDoctorAddress.setClickable(flag);
        binding.etDoctorHospital.setEnabled(flag);
        binding.etDoctorHospital.setClickable(flag);
    }
}