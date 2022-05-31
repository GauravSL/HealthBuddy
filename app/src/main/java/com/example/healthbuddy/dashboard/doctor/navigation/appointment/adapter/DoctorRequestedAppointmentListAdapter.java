package com.example.healthbuddy.dashboard.doctor.navigation.appointment.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.webservices.Response;
import com.example.healthbuddy.webservices.ServerDataTransfer;
import com.example.healthbuddy.webservices.model.DoctorAppointmentDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoctorRequestedAppointmentListAdapter extends RecyclerView.Adapter<DoctorRequestedAppointmentListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DoctorAppointmentDetails> doctorAppointmentsList;
    private int selectedPosition;

    public DoctorRequestedAppointmentListAdapter(Context context,  ArrayList<DoctorAppointmentDetails> doctorAppointmentsList){
         this.context = context;
         this.doctorAppointmentsList = doctorAppointmentsList;
    }

    @NonNull
    @Override
    public DoctorRequestedAppointmentListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.doctor_requested_appointment_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorRequestedAppointmentListAdapter.ViewHolder holder, int position) {
        DoctorAppointmentDetails appointmentDetails = doctorAppointmentsList.get(position);
        holder.patientName.setText(appointmentDetails.getUserName());
        holder.doctorContact.setText(appointmentDetails.getUserMobile());
        holder.txtAppointmentId.setText(appointmentDetails.getAppointmentId());
        holder.appointmentDate.setText(appointmentDetails.getAppointmentDate().split("T")[0]);
        holder.appointmentTime.setText(appointmentDetails.getAppointmentDate().split("T")[1]);
        holder.txtPatientHospitalAddress.setText(appointmentDetails.getUserAddress());
        if (appointmentDetails.getAppointmentStatus().equalsIgnoreCase("Rejected")){
            holder.rejectionContainer.setVisibility(View.VISIBLE);
            holder.buttonContainer.setVisibility(View.GONE);
            holder.txtRejectionReason.setText(appointmentDetails.getRejectionReason());
        }else{
            holder.rejectionContainer.setVisibility(View.GONE);
        }

        holder.btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = holder.getAdapterPosition();
                callAcceptAppointment(doctorAppointmentsList.get(holder.getAdapterPosition()).getDoctorId()
                        ,doctorAppointmentsList.get(holder.getAdapterPosition()).getAppointmentId() );

            }
        });

        holder.btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.rejectionReasonContainer.getVisibility() == View.VISIBLE){
                    if (holder.etRejectionReason.getText().toString().isEmpty()){
                        Toast.makeText(context, "Enter Rejection Reason", Toast.LENGTH_LONG).show();
                    }else{
                        selectedPosition = holder.getAdapterPosition();
                        callRejectAppointment(doctorAppointmentsList.get(holder.getAdapterPosition()).getAppointmentId(),
                                holder.etRejectionReason.getText().toString());
                    }
                }else{
                    holder.rejectionReasonContainer.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    AsyncTask<Void, Void, Response> asyncTaskGetAppointments;
    private void callAcceptAppointment(String doctorId, String appointmentId){
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
                    json.put("appointment_id", appointmentId);
                    json.put("doctor_id", doctorId);
                    response = dataTransfer.accessAPI("acceptAppointment","POST",json.toString());
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

    AsyncTask<Void, Void, Response> asyncTaskRejectAppointments;
    private void callRejectAppointment(String appointmentId, String rejectionReason){
        asyncTaskRejectAppointments = new AsyncTask<Void, Void, Response>() {
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
                    json.put("appointment_id", appointmentId);
                    json.put("rejection_reason", rejectionReason);
                    response = dataTransfer.accessAPI("rejectAppointment","POST",json.toString());
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
        asyncTaskRejectAppointments.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void processResponse(Response response){
        Toast.makeText(context, response.getResponse(), Toast.LENGTH_LONG).show();
        if (response.getStatusCode()==200){
            doctorAppointmentsList.remove(selectedPosition);
            notifyItemRemoved(selectedPosition);
        }
    }


    @Override
    public int getItemCount() {
        return doctorAppointmentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView patientName, txtAppointmentId, doctorContact, appointmentDate,  appointmentTime, txtRejectionReason,
                txtPatientHospitalToVisit, txtPatientHospitalAddress;
        LinearLayout buttonContainer, rejectionContainer, rejectionReasonContainer;
        AppCompatButton btn_reject, btn_accept;
        EditText etRejectionReason;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.txtPatientName);
            doctorContact = itemView.findViewById(R.id.txtPatientContact);
            txtAppointmentId = itemView.findViewById(R.id.txtAppointmentId);
            appointmentDate = itemView.findViewById(R.id.txtPatientSlotDate);
            appointmentTime = itemView.findViewById(R.id.txtPatientSlotTime);
            txtRejectionReason = itemView.findViewById(R.id.txtRejectionReason);
            txtPatientHospitalToVisit = itemView.findViewById(R.id.txtPatientHospitalToVisit);
            txtPatientHospitalAddress = itemView.findViewById(R.id.txtPatientHospitalAddress);
            buttonContainer = itemView.findViewById(R.id.buttonContainer);
            rejectionContainer = itemView.findViewById(R.id.rejectionContainer);
            rejectionReasonContainer = itemView.findViewById(R.id.rejectionReasonContainer);
            etRejectionReason = itemView.findViewById(R.id.etRejectionReason);
            btn_reject = itemView.findViewById(R.id.btn_reject);
            btn_accept = itemView.findViewById(R.id.btn_accept);
        }
    }
}
