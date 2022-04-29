package com.example.healthbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
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
import com.example.healthbuddy.patient.MedicineListActivity;
import com.example.healthbuddy.patient.dashboard.UserDashboardActivity;
import com.example.healthbuddy.registration.user.UserRegistrationActivity;


public class LoginActivity extends AppCompatActivity {

    Button btn_Login;
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
                    intent = new Intent(LoginActivity.this, DashboardDoctor.class);
                }else if(rb_User.isChecked()){
                    intent = new Intent(LoginActivity.this, UserDashboardActivity.class);
                }else if(rb_Admin.isChecked()){
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                }else {
                    Toast.makeText(LoginActivity.this,"Plese Select Valid Profile",Toast.LENGTH_SHORT).show();
                }

                startActivity(intent);
                //Toast.makeText(LoginActivity.this, "Invalid Login Credentials", Toast.LENGTH_SHORT).show();
            }

           // Intent intent = new Intent(LoginActivity.this, UserRegistrationActivity.class);
          //  startActivity(intent);
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
}