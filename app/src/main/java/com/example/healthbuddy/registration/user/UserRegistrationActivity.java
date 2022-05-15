package com.example.healthbuddy.registration.user;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthbuddy.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UserRegistrationActivity extends AppCompatActivity {
    final Calendar myCalendar= Calendar.getInstance();
    EditText editText;
    LinearLayout ll_password_container, main_container;
    private ShapeableImageView img_profile;
    private TextView tv_userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        editText=(EditText) findViewById(R.id.et_userDOB);
        ll_password_container=(LinearLayout) findViewById(R.id.password_container);
        ll_password_container.setVisibility(View.VISIBLE);
        main_container=(LinearLayout) findViewById(R.id.main_container);
        main_container.setBackgroundColor(getResources().getColor(R.color.white));
        img_profile=(ShapeableImageView) findViewById(R.id.img_profile);
        img_profile.setVisibility(View.GONE);
        tv_userId=(TextView) findViewById(R.id.tv_userId);
        tv_userId.setText("Register");
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(UserRegistrationActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(dateFormat.format(myCalendar.getTime()));
    }
}