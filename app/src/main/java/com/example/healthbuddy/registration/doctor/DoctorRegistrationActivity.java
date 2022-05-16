package com.example.healthbuddy.registration.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.patient.dashboard.ui.appointment.SlideshowViewModel;
import com.google.android.material.imageview.ShapeableImageView;

import org.w3c.dom.Text;

public class DoctorRegistrationActivity extends AppCompatActivity {
    private LinearLayout ll_password_container,main_container;
    private ShapeableImageView img_profile;
    private TextView tv_doctorId;

    String name ="",
    password = "",
    mail = "",
    mNumber = "",
    degree ="",
    address ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_profile);
        EditText editText = (EditText) findViewById(R.id.et_docname);
        ll_password_container=(LinearLayout) findViewById(R.id.password_container);
        img_profile=(ShapeableImageView) findViewById(R.id.img_profile);
        img_profile.setVisibility(View.GONE);
        tv_doctorId=(TextView) findViewById(R.id.tv_doctorId);
        tv_doctorId.setText("Register");
        ll_password_container.setVisibility(View.VISIBLE);
        main_container=(LinearLayout) findViewById(R.id.main_container);
        main_container.setBackgroundColor(getResources().getColor(R.color.white));

      name = editText.getText().toString();
      password = editText.getText().toString();
      mail = editText.getText().toString();
      mNumber = editText.getText().toString();
      degree = editText.getText().toString();
      address = editText.getText().toString();


    }
}