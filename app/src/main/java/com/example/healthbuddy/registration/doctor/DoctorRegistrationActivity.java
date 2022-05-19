package com.example.healthbuddy.registration.doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthbuddy.LoginActivity;
import com.example.healthbuddy.MainActivity;
import com.example.healthbuddy.R;
import com.example.healthbuddy.dashboard.doctor.DashboardDoctor;
import com.example.healthbuddy.patient.dashboard.UserDashboardActivity;
import com.example.healthbuddy.patient.dashboard.ui.appointment.SlideshowViewModel;
import com.example.healthbuddy.webservices.Response;
import com.example.healthbuddy.webservices.ServerDataTransfer;
import com.google.android.material.imageview.ShapeableImageView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

public class DoctorRegistrationActivity extends AppCompatActivity {
    private LinearLayout ll_password_container;
    private RelativeLayout main_container;
    private ShapeableImageView img_profile;
    private TextView tv_doctorId;
    ProgressBar progressDialog;
    private EditText et_doctorName, et_doctorMail,et_doctorMob,et_doctorDegree,et_doctorAddress, et_password, et_confirm_password;
    private AppCompatButton btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_profile);
        initView();
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callRegistrationService();
            }
        });

    }

    public void initView(){
        ll_password_container=(LinearLayout) findViewById(R.id.password_container);
        img_profile=(ShapeableImageView) findViewById(R.id.img_profile);
        img_profile.setVisibility(View.GONE);
        tv_doctorId=(TextView) findViewById(R.id.tv_doctorId);
        tv_doctorId.setText("Register");
        ll_password_container.setVisibility(View.VISIBLE);
        main_container=(RelativeLayout) findViewById(R.id.main_container);
        main_container.setBackgroundColor(getResources().getColor(R.color.white));
        progressDialog = findViewById(R.id.progressDialog);
        et_doctorName= findViewById(R.id.et_doctorName);
        et_doctorMail= findViewById(R.id.et_doctorMail);
        et_doctorMob= findViewById(R.id.et_doctorMob);
        et_doctorDegree= findViewById(R.id.et_doctorDegree);
        et_doctorAddress= findViewById(R.id.et_doctorAddress);
        et_password= findViewById(R.id.et_password);
        et_confirm_password= findViewById(R.id.et_confirm_password);
        btn_submit= findViewById(R.id.btn_submit);
    }

    AsyncTask<Void, Void, Response> asyncTask;
    private void callRegistrationService(){
        asyncTask = new AsyncTask<Void, Void, Response>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setVisibility(View.VISIBLE);
            }

            @Override
            protected Response doInBackground(Void... strings) {
                ServerDataTransfer dataTransfer = new ServerDataTransfer();
                Response response = null;
                try {
                    /*  apiDATA.put(Constants.AUTHORIZATION, "");*/
                    // String categoryId = impactedCategory.getCategoryId();
                    JSONObject json = new JSONObject();
                     json.put("doctor_name", et_doctorName.getText().toString());
                     json.put("password", et_password.getText().toString());
                     json.put("doctor_email", et_doctorMail.getText().toString());
                    json.put("doctor_mobile", et_doctorMob.getText().toString());
                    json.put("doctor_address", et_doctorAddress.getText().toString());
                    json.put("doctor_speciality", et_doctorDegree.getText().toString());
                    response = dataTransfer.accessAPI("doctorRegistration","POST",json.toString());


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
        progressDialog.setVisibility(View.GONE);

        Toast.makeText(this, response.getResponse(), Toast.LENGTH_LONG).show();
    }
}