package com.example.healthbuddy.admin;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.dashboard.doctor.navigation.appointment.SelectedAppointmentActivity;
import com.example.healthbuddy.webservices.Response;
import com.example.healthbuddy.webservices.ServerDataTransfer;
import com.example.healthbuddy.webservices.model.DoctorAppointmentDetails;
import com.example.healthbuddy.webservices.model.DoctorDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ApproveDoctorAdapter extends RecyclerView.Adapter<ApproveDoctorAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DoctorDetails> doctorDetails;

    public ApproveDoctorAdapter(Context context, ArrayList<DoctorDetails> doctorDetails){
        this.context = context;
        this.doctorDetails = doctorDetails;
    }

    @NonNull
    @Override
    public ApproveDoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.admin_doctor_approval, parent, false);
        return new ApproveDoctorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApproveDoctorAdapter.ViewHolder holder, int position) {
        DoctorDetails doctorDetail = doctorDetails.get(position);
        holder.txt_doctor_with_us.setText(doctorDetail.getDoctorName());
        holder.txt_doctor_contact.setText(doctorDetail.getDoctorMobile());
        holder.txt_doctor_address.setText(doctorDetail.getDoctorAddress());
        holder.btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = holder.getAdapterPosition();
                callAcceptDoctor(doctorDetails.get(holder.getAdapterPosition()).getId().toString());
            }
        });

        holder.btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorDetails.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_doctor_with_us,txt_doctor_contact,txt_doctor_address;
        AppCompatButton btn_reject, btn_accept;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_doctor_with_us = itemView.findViewById(R.id.txt_doctor_with_us);
            txt_doctor_contact = itemView.findViewById(R.id.txt_doctor_contact);
            txt_doctor_address = itemView.findViewById(R.id.txt_doctor_address);
            btn_reject = itemView.findViewById(R.id.btn_reject);
            btn_accept = itemView.findViewById(R.id.btn_accept);
        }
    }

    AsyncTask<Void, Void, Response> asyncTaskGetAppointments;
    private void callAcceptDoctor(String doctorId){
        asyncTaskGetAppointments = new AsyncTask<Void, Void, Response>() {
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
                    JSONObject json = new JSONObject();
                    json.put("doctor_id", doctorId);
                    response = dataTransfer.accessAPI("acceptDoctor","POST",json.toString());
                } catch (IOException | JSONException exception) {
                    exception.printStackTrace();
                }

                return response;
            }

            @Override
            protected void onPostExecute(Response response) {
                super.onPostExecute(response);
                processResponse(response);
            }
        };
        asyncTaskGetAppointments.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    private int selectedPosition;

    private void processResponse(Response response){
        Toast.makeText(context, response.getResponse(), Toast.LENGTH_LONG).show();
        if (response.getStatusCode()==200){
            doctorDetails.remove(selectedPosition);
            notifyItemRemoved(selectedPosition);
        }
    }
}

