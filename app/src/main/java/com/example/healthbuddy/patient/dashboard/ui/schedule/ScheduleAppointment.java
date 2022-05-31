package com.example.healthbuddy.patient.dashboard.ui.schedule;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthbuddy.R;
import com.example.healthbuddy.databinding.ScheduleAppointmentBinding;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class ScheduleAppointment extends Fragment {
    final Calendar myCalendar = Calendar.getInstance();
    ScheduleAppointmentBinding binding;
    String[] doctorsArray;
    ArrayList<DoctorDetails> doctorList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = ScheduleAppointmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Spinner spin = (Spinner) root.findViewById(R.id.sp_doc);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);

        binding.rbOnline.setChecked(true);


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };
        binding.etScheduleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(binding.etScheduleDate.getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        binding.etSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(binding.etSelectTime.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        binding.etSelectTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }

        });
        binding.btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.etScheduleDate.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Select appointment Date", Toast.LENGTH_SHORT).show();
                }else if(binding.etSelectTime.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Select appointment Time", Toast.LENGTH_SHORT).show();
                }else{
                    callScheduleAppointmentService();
                }
            }
        });

        binding.spDoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i< doctorList.size() ;i++) {
                    if (doctorList.get(i).getDoctorName().equalsIgnoreCase(doctorsArray[position])){
                        binding.doctorAddress.setText("Hospital Name :- "+doctorList.get(i).getHospital_name()+"\nAddress :- " + doctorList.get(i).getDoctorAddress());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        callGetDoctorDetails();

        return root;


    }

    private void updateLabel() {
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        binding.etScheduleDate.setText(dateFormat.format(myCalendar.getTime()));
    }

    AsyncTask<Void, Void, Response> asyncTask;
    private void callScheduleAppointmentService(){
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
                    /*  apiDATA.put(Constants.AUTHORIZATION, "");*/
                    // String categoryId = impactedCategory.getCategoryId();
                    String doctor_name = (String)binding.spDoc.getSelectedItem();
                    String doctor_id = null;
                    for (int i = 0; i< doctorList.size() ;i++) {
                        if (doctorList.get(i).getDoctorName().equalsIgnoreCase(doctor_name)){
                            doctor_id = doctorList.get(i).getId().toString();
                        }
                    }
                    JSONObject json = new JSONObject();
                    json.put("doctor_id", doctor_id);
                    json.put("user_id", Constants.userDetails.getId());
                    json.put("appointment_date", binding.etScheduleDate.getText().toString()+ " " + binding.etSelectTime.getText().toString());
                    if (binding.rbOnline.isChecked()){
                    json.put("appointment_mode", "Online");
                    }else{
                        json.put("appointment_mode", "Offline");
                    }
                    response = dataTransfer.accessAPI("scheduleAppointment","POST",json.toString());
                } catch (IOException | JSONException exception) {
                    exception.printStackTrace();
                }

                return response;
            }

            @Override
            protected void onPostExecute(Response response) {
                super.onPostExecute(response);
                processScheduleResponse(response);
            }
        };
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
        asyncTaskGetDoctor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void processScheduleResponse(Response response){
        binding.progressDialog.setVisibility(View.GONE);
        Toast.makeText(getContext(), response.getResponse(), Toast.LENGTH_LONG).show();
    }

    private void processDoctorList(Response response){
        binding.progressDialog.setVisibility(View.GONE);
        Type type = new TypeToken<ArrayList<DoctorDetails>>() {
        }.getType();
        doctorList = new Gson().fromJson(response.getResponse(), type);
        doctorsArray = new String[doctorList.size()];
        for (int i = 0; i< doctorList.size() ;i++) {
            doctorsArray[i] = doctorList.get(i).getDoctorName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, doctorsArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spDoc.setAdapter(adapter);

    }
    }