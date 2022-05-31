package com.example.healthbuddy.patient.dashboard.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.databinding.DoctorsWithUsBinding;
import com.example.healthbuddy.databinding.FragmentHomeBinding;
import com.example.healthbuddy.databinding.HospitalListBinding;
import com.example.healthbuddy.databinding.UserHomeDashboardBinding;
import com.example.healthbuddy.webservices.Response;
import com.example.healthbuddy.webservices.ServerDataTransfer;
import com.example.healthbuddy.webservices.model.BedDetails;
import com.example.healthbuddy.webservices.model.DoctorDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class DoctorWithUsFragment extends AppCompatActivity {

    private ArrayList<DoctorDetails> doctorDetails;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_fragment);
        recyclerView = findViewById(R.id.recyclerView);
        callGetAmbulanceDetails();
    }

    AsyncTask<Void, Void, Response> asyncTaskGetAmbulance;
    private void callGetAmbulanceDetails(){
        asyncTaskGetAmbulance = new AsyncTask<Void, Void, Response>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // binding.progressDialog.setVisibility(View.VISIBLE);
            }

            @Override
            protected Response doInBackground(Void... strings) {
                ServerDataTransfer dataTransfer = new ServerDataTransfer();
                Response response = null;
                try {
                    response = dataTransfer.accessAPI("getDoctorList","GET",null);
                } catch (IOException exception) {
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
        asyncTaskGetAmbulance.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void processDoctorList(Response response){
        Type type = new TypeToken<ArrayList<DoctorDetails>>() {
        }.getType();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        doctorDetails = new Gson().fromJson(response.getResponse(), type);
        DoctorWithUsAdapter adapter = new DoctorWithUsAdapter(this, doctorDetails);
        recyclerView.setAdapter(adapter);
    }

}