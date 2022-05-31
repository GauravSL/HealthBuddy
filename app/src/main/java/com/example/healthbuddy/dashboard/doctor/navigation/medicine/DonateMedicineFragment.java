package com.example.healthbuddy.dashboard.doctor.navigation.medicine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.healthbuddy.databinding.DonateMedicineBinding;
import com.example.healthbuddy.webservices.Constants;
import com.example.healthbuddy.webservices.Response;
import com.example.healthbuddy.webservices.ServerDataTransfer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class DonateMedicineFragment extends Fragment {

    private DonateMedicineBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DonateMedicineBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.syrupRadio.setChecked(true);

        binding.donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.etMedicineName.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Enter Medicine Name", Toast.LENGTH_SHORT).show();
                }else if(binding.etExpiryDate.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Enter Expiry date", Toast.LENGTH_SHORT).show();
                }else if(binding.etQuantity.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Enter Quantity", Toast.LENGTH_SHORT).show();
                }else {
                    callAddMedicineService();
                }
            }
        });
        return root;
    }

    AsyncTask<Void, Void, Response> asyncTaskAddMedicineDetails;
    private void callAddMedicineService(){
        asyncTaskAddMedicineDetails = new AsyncTask<Void, Void, Response>() {
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
                    //json.put("id", "2");
                    json.put("medicine_name", binding.etMedicineName.getText().toString());
                    json.put("medicine_type", (binding.syrupRadio.isChecked()?"Syrup":"Tablet"));
                    json.put("medicine_exp_date", binding.etExpiryDate.getText().toString());
                    json.put("available_quantity",binding.etQuantity.getText().toString());
                    if (Constants.doctorDetails !=null){
                        json.put("pick_up_address",Constants.doctorDetails.getDoctorAddress());
                    }else if(Constants.userDetails !=null){
                        json.put("pick_up_address",Constants.userDetails.getUserAddress());

                    }

                    response = dataTransfer.accessAPI("addMedicineToBank","POST",json.toString());


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
        asyncTaskAddMedicineDetails.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void processUpdateResponse(Response response){
        binding.progressDialog.setVisibility(View.GONE);
        Toast.makeText(getContext(), response.getResponse(), Toast.LENGTH_LONG).show();
        clearForm();
    }

    private void clearForm() {
        binding.etMedicineName.setText("");
        binding.etQuantity.setText("");
        binding.etExpiryDate.setText("");
        binding.syrupRadio.setChecked(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}