package com.example.healthbuddy.admin;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.webservices.Response;
import com.example.healthbuddy.webservices.ServerDataTransfer;
import com.example.healthbuddy.webservices.model.DoctorDetails;
import com.example.healthbuddy.webservices.model.OrderDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ApproveOrderFragment extends Fragment {
    RecyclerView recyclerView;

    public ApproveOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);



        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        callGetOrderDetails();
        return view;
    }

    AsyncTask<Void, Void, Response> asyncTaskGetDoctors;
    private void callGetOrderDetails(){
        asyncTaskGetDoctors = new AsyncTask<Void, Void, Response>() {
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
                    response = dataTransfer.accessAPI("getOrderList","GET",null);
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
        asyncTaskGetDoctors.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private ArrayList<OrderDetails> orderDetails;
    private void processDoctorList(Response response){
        Type type = new TypeToken<ArrayList<OrderDetails>>() {
        }.getType();
        orderDetails = new Gson().fromJson(response.getResponse(), type);
        ApproveOrderAdapter adapter = new ApproveOrderAdapter(getContext(), orderDetails);
        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(adapter);
    }

}