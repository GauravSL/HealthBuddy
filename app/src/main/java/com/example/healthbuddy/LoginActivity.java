package com.example.healthbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthbuddy.dashboard.doctor.DashboardDoctor;
import com.example.healthbuddy.patient.dashboard.UserDashboardActivity;
import com.example.healthbuddy.registration.doctor.DoctorRegistrationActivity;
import com.example.healthbuddy.registration.user.UserRegistrationActivity;
import com.example.healthbuddy.webservices.Response;
import com.example.healthbuddy.webservices.ServerDataTransfer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class LoginActivity extends AppCompatActivity {

    Button btn_Login, btn_register;
    EditText etUserName;
    EditText etPassword;
    TextView txt_password_error;
    TextView txt_username_error;
    TextView txt_forgot_password,txt_login_again;
    CardView cv_login, cv_forgotPassword;
    RadioButton rb_Doctor, rb_User, rb_Admin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        Animation slideInLeft = AnimationUtils.loadAnimation(this, R.anim.anim_slide_in_left);
        Animation slideInRight = AnimationUtils.loadAnimation(this, R.anim.anim_slide_in_right);
        Animation slideOutLeft = AnimationUtils.loadAnimation(this, R.anim.anim_slide_out_left);
        Animation slideOutRight = AnimationUtils.loadAnimation(this, R.anim.anim_slide_out_right);

        btn_Login.setOnClickListener(view -> {
            if (etUserName.getText().toString().equalsIgnoreCase("")) {
                txt_username_error.setVisibility(View.VISIBLE);
                txt_username_error.setText("Please enter username");
            } else if (etPassword.getText().toString().equalsIgnoreCase("")) {
                txt_password_error.setVisibility(View.VISIBLE);
                txt_password_error.setText("Please enter password");
            } else if (etUserName.getText().toString().equalsIgnoreCase("doctor")
                    && etPassword.getText().toString().equalsIgnoreCase("doctor")) {
                Toast.makeText(LoginActivity.this, "Doctor Login Success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, DashboardDoctor.class);
                startActivity(intent);
            }else{
                Intent intent = null;
                if(rb_Doctor.isChecked()){
                 //   intent = new Intent(LoginActivity.this, DashboardDoctor.class);
                    callService(etUserName.getText().toString(), etPassword.getText().toString(), "doctor");
                }else if(rb_User.isChecked()){
                   // intent = new Intent(LoginActivity.this, UserDashboardActivity.class);
                    callService(etUserName.getText().toString(), etPassword.getText().toString(), "user");

                }else if(rb_Admin.isChecked()){
                    //intent = new Intent(LoginActivity.this, MainActivity.class);
                    callService(etUserName.getText().toString(), etPassword.getText().toString(), "admin");

                }else {
                    Toast.makeText(LoginActivity.this,"Plese Select Valid Profile",Toast.LENGTH_SHORT).show();
                }

                //startActivity(intent);


                //Toast.makeText(LoginActivity.this, "Invalid Login Credentials", Toast.LENGTH_SHORT).show();
            }

           // Intent intent = new Intent(LoginActivity.this, UserRegistrationActivity.class);
          //  startActivity(intent);
        });

        btn_register.setOnClickListener(view -> {

                Intent intent = null;
                if(rb_Doctor.isChecked()){
                    intent = new Intent(LoginActivity.this, DoctorRegistrationActivity.class);
                }else if(rb_User.isChecked()){
                    intent = new Intent(LoginActivity.this, UserRegistrationActivity.class);
                }else if(rb_Admin.isChecked()){
                    Toast.makeText(LoginActivity.this,"You can not register as Admin",Toast.LENGTH_SHORT).show();
                return;
                }else {
                    Toast.makeText(LoginActivity.this,"Please Select Valid Profile",Toast.LENGTH_SHORT).show();
                     return;
                }
                startActivity(intent);

        });

        txt_forgot_password.setOnClickListener(view -> {
            cv_login.startAnimation(slideOutLeft);
        });
        txt_login_again.setOnClickListener(view -> {
            cv_forgotPassword.startAnimation(slideOutRight);
        });




        slideOutLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cv_login.setVisibility(View.GONE);
                cv_forgotPassword.startAnimation(slideInRight);
                cv_forgotPassword.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        slideOutRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cv_forgotPassword.setVisibility(View.GONE);
                cv_login.startAnimation(slideInLeft);
                cv_login.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initView(){
        btn_Login = findViewById(R.id.btn_Login);
        btn_register = findViewById(R.id.btn_register);
        etUserName = findViewById(R.id.user_name);
        etPassword = findViewById(R.id.password);
        txt_password_error = findViewById(R.id.txt_password_error);
        txt_username_error = findViewById(R.id.txt_username_error);
        txt_forgot_password = findViewById(R.id.txt_forgot_password);
        cv_login = findViewById(R.id.cv_login);
        cv_forgotPassword = findViewById(R.id.cv_forgotPassword);
        txt_login_again = findViewById(R.id.txt_login_again);
        rb_Doctor = findViewById(R.id.rb_doctor_rb);
        rb_User = findViewById(R.id.rb_patient_rb);
        rb_Admin = findViewById(R.id.rb_admin_rb);
    }

    AsyncTask<Void, Void, Response> asyncTask;
    private void callService(String username, String password, String role){
         asyncTask = new AsyncTask<Void, Void, Response>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Response doInBackground(Void... strings) {
                ServerDataTransfer dataTransfer = new ServerDataTransfer();
                Response response = null;
                try {
                    /*  apiDATA.put(Constants.AUTHORIZATION, "");*/
                    // String categoryId = impactedCategory.getCategoryId();
                    JSONObject obj = new JSONObject();
                    obj.put("username", username);
                    obj.put("password", password);
                    obj.put("role", role);
                    response = dataTransfer.accessAPI("","",obj.toString());


                } catch (IOException | JSONException exception) {
                    exception.printStackTrace();
                }

                return response;
            }

            @Override
            protected void onPostExecute(Response response) {
                super.onPostExecute(response);
            }
        };
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}