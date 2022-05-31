package com.example.healthbuddy.dashboard.doctor.navigation.appointment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthbuddy.dashboard.doctor.navigation.appointment.adapter.DoctorChatAdapter;
import com.example.healthbuddy.databinding.ChatFragmentBinding;
import com.example.healthbuddy.webservices.Response;
import com.example.healthbuddy.webservices.ServerDataTransfer;
import com.example.healthbuddy.webservices.model.ChatDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ChatFragment extends Fragment {

    ChatFragmentBinding binding;
    private ArrayList<ChatDetails> chatDetails;
    private String appointmentId, appointmentIdUser;

    public ChatFragment(String appointmentId, String appointmentIdUser) {
        this.appointmentId = appointmentId;
        this.appointmentIdUser = appointmentIdUser;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ChatFragmentBinding.inflate(inflater, container, false);
        binding.rvChatList.setLayoutManager(new LinearLayoutManager(getContext()));
        callChatListDetails();
        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.tvMessageToSend.getText().toString().isEmpty()){
                    callSendMessageService();
                }
            }
        });
        return binding.getRoot();
    }

    AsyncTask<Void, Void, Response> asyncTaskSendMessage;

    private void callChatListDetails() {
        asyncTaskSendMessage = new AsyncTask<Void, Void, Response>() {
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
                    if (!appointmentId.isEmpty())
                    json.put("appointment_id", appointmentId);
                    else if (!appointmentIdUser.isEmpty())
                        json.put("appointment_id", appointmentIdUser);
                    response = dataTransfer.accessAPI("getChatList", "POST", json.toString());
                } catch (IOException | JSONException exception) {
                    exception.printStackTrace();
                }

                return response;
            }

            @Override
            protected void onPostExecute(Response response) {
                super.onPostExecute(response);
                processChatList(response);
            }
        };
        asyncTaskSendMessage.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void processChatList(Response response) {
        binding.progressDialog.setVisibility(View.GONE);

        if (response.getStatusCode()==200) {
            Type type = new TypeToken<ArrayList<ChatDetails>>() {
            }.getType();
            chatDetails = new Gson().fromJson(response.getResponse(), type);
            if (chatDetails.size() > 0) {
                binding.txtNoAppointment.setVisibility(View.GONE);
                binding.rvChatList.setVisibility(View.VISIBLE);
                DoctorChatAdapter adapter = null;
                if (!appointmentId.isEmpty()){
                 adapter = new DoctorChatAdapter(getContext(), chatDetails, true);

                }else if(!appointmentIdUser.isEmpty()){
                     adapter = new DoctorChatAdapter(getContext(), chatDetails, false);
                }
                binding.rvChatList.setAdapter(adapter);
            } else {
                binding.txtNoAppointment.setVisibility(View.VISIBLE);
            }
        }else{
            binding.txtNoAppointment.setVisibility(View.VISIBLE);
        }
    }


    AsyncTask<Void, Void, Response> asyncTask;
    private void callSendMessageService(){
        asyncTask = new AsyncTask<Void, Void, Response>() {
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
                    Date c = Calendar.getInstance().getTime();
                    System.out.println("Current time => " + c);

                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM", Locale.getDefault());
                    String formattedDate = df.format(c);
                    JSONObject json = new JSONObject();
                    if (!appointmentId.isEmpty())
                        json.put("sent_by", "Doctor");
                    else if (!appointmentIdUser.isEmpty())
                        json.put("sent_by", "User");
                    if (!appointmentId.isEmpty())
                        json.put("appointment_id", appointmentId);
                    else if (!appointmentIdUser.isEmpty())
                        json.put("appointment_id", appointmentIdUser);
                    json.put("message_text", binding.tvMessageToSend.getText().toString());
                    json.put("message_time", formattedDate);

                    response = dataTransfer.accessAPI("saveChatList","POST",json.toString());


                } catch (IOException | JSONException exception) {
                    exception.printStackTrace();
                }

                return response;
            }

            @Override
            protected void onPostExecute(Response response) {
                super.onPostExecute(response);
                processLoginResponse(response);
            }
        };
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    private void processLoginResponse(Response response){
        binding.progressDialog.setVisibility(View.GONE);
        if (response.getStatusCode()==200){
            callChatListDetails();
            binding.tvMessageToSend.setText("");
        }else{
            Toast.makeText(getContext(), response.getResponse(), Toast.LENGTH_LONG).show();
        }
    }

}
