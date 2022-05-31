package com.example.healthbuddy.patient.dashboard.ui.ordermed;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.healthbuddy.databinding.ActivityMedicineListBinding;
import com.example.healthbuddy.patient.MedicineListAdapter;
import com.example.healthbuddy.patient.dashboard.ui.appointment.AppointmentTabAdapter;
import com.example.healthbuddy.patient.dashboard.ui.appointment.SlideshowViewModel;
import com.example.healthbuddy.webservices.Constants;
import com.example.healthbuddy.webservices.Response;
import com.example.healthbuddy.webservices.ServerDataTransfer;
import com.example.healthbuddy.webservices.model.MedicineDetails;
import com.example.healthbuddy.webservices.model.UserAppointmentDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class OrderMedicineFragment extends Fragment {

    private ActivityMedicineListBinding binding;
    MedicineListAdapter medicineListAdapter;
    private ArrayList<MedicineDetails> medicineList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = ActivityMedicineListBinding.inflate(inflater, container, false);
        callGetMedicineListDetails();
        binding.btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<MedicineDetails> orderMed =  medicineListAdapter.getMedicineToOrder();
                if (orderMed.size()>0){
                    JSONArray listArray = new JSONArray();
                    for (MedicineDetails md: orderMed) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("medicine_id", md.getMedicine_id());
                            jsonObject.put("medicine_quantity", md.getOrderMedicineQuantity());
                            listArray.put(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    callOrderMedicine(listArray);
                }
            }
        });
        return binding.getRoot();
    }

    AsyncTask<Void, Void, Response> asyncTaskGetAppointments;
    private void callGetMedicineListDetails(){
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

                    response = dataTransfer.accessAPI("getMedicineList","GET",null);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

                return response;
            }

            @Override
            protected void onPostExecute(Response response) {
                super.onPostExecute(response);
                processMedicineList(response);
            }
        };
        asyncTaskGetAppointments.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void processMedicineList(Response response) {
        binding.progressDialog.setVisibility(View.GONE);

        Type type = new TypeToken<ArrayList<MedicineDetails>>() {
        }.getType();
        medicineList = new Gson().fromJson(response.getResponse(), type);
        if (medicineList.size() > 0) {
            binding.rvMedicinelist.setLayoutManager(new LinearLayoutManager(getContext()));
            medicineListAdapter = new MedicineListAdapter(getContext() ,medicineList);
            binding.rvMedicinelist.setAdapter(medicineListAdapter);
        } else {
            binding.txtNoAppointment.setVisibility(View.VISIBLE);
        }
    }

    AsyncTask<Void, Void, Response> asyncTaskUpdateUserDetails;
    private void callOrderMedicine(JSONArray orderDetails){
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
                    Date c = Calendar.getInstance().getTime();
                    System.out.println("Current time => " + c);

                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    String formattedDate = df.format(c);
                    JSONObject json = new JSONObject();
                    json.put("user_id", Constants.userDetails.getId());
                    json.put("order_date", formattedDate);
                    json.put("ordered_medicine", orderDetails);

                    response = dataTransfer.accessAPI("orderMedicine","POST",json.toString());


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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}