package com.example.healthbuddy.patient.dashboard.ui.profile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.healthbuddy.databinding.UserProfileBinding;
import com.example.healthbuddy.webservices.Response;
import com.example.healthbuddy.webservices.ServerDataTransfer;
import com.example.healthbuddy.webservices.model.UserDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class UserProfileFragment extends Fragment {

    private UserProfileBinding binding;
    private ArrayList<UserDetails> userDetails;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = UserProfileBinding.inflate(inflater, container, false);
        initView();
        callGetUserDetails();

        return binding.getRoot();
    }

    private void initView() {
        binding.btnSubmit.setText("Edit");
        binding.btnSubmit.setOnClickListener(v -> {
            if (binding.btnSubmit.getText().toString().equalsIgnoreCase("Edit")){
                binding.btnSubmit.setText("Submit");
                handleEdittextEnable(true);
            } else if (binding.btnSubmit.getText().toString().equalsIgnoreCase("Submit")){
                handleEdittextEnable(false);
                callUserUpdateService();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    AsyncTask<Void, Void, Response> asyncTaskGetUserDetails;
    private void callGetUserDetails(){
        asyncTaskGetUserDetails = new AsyncTask<Void, Void, Response>() {
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
                    json.put("id", "4");
                    response = dataTransfer.accessAPI("getUserDetails","POST",json.toString());
                } catch (IOException | JSONException exception) {
                    exception.printStackTrace();
                }

                return response;
            }

            @Override
            protected void onPostExecute(Response response) {
                super.onPostExecute(response);
                processUserDetails(response);
            }
        };
        asyncTaskGetUserDetails.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void processUserDetails(Response response) {
        binding.progressDialog.setVisibility(View.GONE);
        if (response.getStatusCode()==200) {
            Type type = new TypeToken<ArrayList<UserDetails>>() {
            }.getType();
            userDetails = new Gson().fromJson(response.getResponse(), type);
            setUIValues();
        }else{
            Toast.makeText(getContext(), response.getResponse(), Toast.LENGTH_LONG).show();
        }

    }

    private void setUIValues() {
        binding.etUserName.setText(userDetails.get(0).getUserName());
        binding.etUserMail.setText(userDetails.get(0).getUserEmail());
        binding.etUserMob.setText(userDetails.get(0).getUserMobile());
        binding.etUserAddress.setText(userDetails.get(0).getUserAddress());
    }

    AsyncTask<Void, Void, Response> asyncTaskUpdateUserDetails;
    private void callUserUpdateService(){
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
                    json.put("id", "4");
                    json.put("username", binding.etUserName.getText().toString());
                    json.put("user_email", binding.etUserMail.getText().toString());
                    json.put("user_mobile", binding.etUserMob.getText().toString());
                    json.put("user_address",binding.etUserAddress.getText().toString());

                    response = dataTransfer.accessAPI("updateUserDetails","POST",json.toString());


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

    private void handleEdittextEnable(Boolean flag){
        binding.etUserName.setEnabled(flag);
        binding.etUserName.setClickable(flag);
        binding.etUserMail.setEnabled(flag);
        binding.etUserMail.setClickable(flag);
        binding.etUserMob.setEnabled(flag);
        binding.etUserMob.setClickable(flag);
        binding.etUserAddress.setEnabled(flag);
        binding.etUserAddress.setClickable(flag);
        // binding.etUserDOB.setEnabled(flag);
        //  binding.etUserDOB.setClickable(flag);
    }

}