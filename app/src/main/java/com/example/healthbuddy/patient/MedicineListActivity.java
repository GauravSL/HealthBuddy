package com.example.healthbuddy.patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.healthbuddy.R;

import java.util.ArrayList;
import java.util.List;

public class MedicineListActivity extends AppCompatActivity {
    RecyclerView rvMedicienList ;
    MedicineListAdapter medicineListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);
        List<String> medicineData = new ArrayList<>();
        medicineData.add("paracetamol");
        medicineData.add("dolo 650");
        medicineData.add("paracetamol");
        medicineData.add("betadin");

        rvMedicienList = findViewById(R.id.rv_medicinelist);
        rvMedicienList.setLayoutManager(new LinearLayoutManager(this));
        medicineListAdapter = new MedicineListAdapter(this ,null);
        rvMedicienList.setAdapter(medicineListAdapter);
    }
}