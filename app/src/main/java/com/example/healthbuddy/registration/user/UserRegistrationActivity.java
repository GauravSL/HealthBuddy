package com.example.healthbuddy.registration.user;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.healthbuddy.R;
import com.example.healthbuddy.webservices.Response;
import com.example.healthbuddy.webservices.ServerDataTransfer;
import com.google.android.material.imageview.ShapeableImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UserRegistrationActivity extends AppCompatActivity {
    final Calendar myCalendar= Calendar.getInstance();
    //EditText et_userDOB;
    LinearLayout ll_password_container;
            RelativeLayout main_container;
    private ShapeableImageView img_profile;
    private TextView tv_userId;
    private EditText et_user_Name, et_user_Mail, et_user_Mob, et_userAddress, et_password, et_confirm_password;
    private AppCompatButton btn_submit;
    private ProgressBar progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
       initView();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        /*et_userDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(UserRegistrationActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });*/
        btn_submit.setOnClickListener(view -> callRegistrationService());

    }

    public void initView(){
     //   et_userDOB=(EditText) findViewById(R.id.et_userDOB);
        ll_password_container=(LinearLayout) findViewById(R.id.password_container);
        ll_password_container.setVisibility(View.VISIBLE);
        main_container=(RelativeLayout) findViewById(R.id.main_container);
        main_container.setBackgroundColor(getResources().getColor(R.color.white));
        img_profile=(ShapeableImageView) findViewById(R.id.img_profile);
        img_profile.setVisibility(View.GONE);
        tv_userId=(TextView) findViewById(R.id.tv_userId);
        tv_userId.setText("Register");
        et_user_Name = findViewById(R.id.et_user_Name);
        et_user_Mail= findViewById(R.id.et_user_Mail);
        et_user_Mob= findViewById(R.id.et_user_Mob);
        et_userAddress= findViewById(R.id.et_userAddress);
        et_password= findViewById(R.id.et_password);
        et_confirm_password= findViewById(R.id.et_confirm_password);
        progressDialog= findViewById(R.id.progressDialog);
        btn_submit= findViewById(R.id.btn_submit);
    }

    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
    //    et_userDOB.setText(dateFormat.format(myCalendar.getTime()));
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
                    json.put("username", et_user_Name.getText().toString());
                    json.put("password", et_password.getText().toString());
                    json.put("user_email", et_user_Mail.getText().toString());
                    json.put("user_mobile", et_user_Mob.getText().toString());
                    json.put("user_address",et_userAddress.getText().toString());

                    response = dataTransfer.accessAPI("userRegistration","POST",json.toString());


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
        finish();
    }
}