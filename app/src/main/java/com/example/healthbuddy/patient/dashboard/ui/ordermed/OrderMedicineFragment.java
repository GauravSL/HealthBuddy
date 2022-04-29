package com.example.healthbuddy.patient.dashboard.ui.ordermed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.healthbuddy.databinding.ActivityMedicineListBinding;
import com.example.healthbuddy.patient.MedicineListAdapter;
import com.example.healthbuddy.patient.dashboard.ui.appointment.SlideshowViewModel;
import java.util.ArrayList;
import java.util.List;


public class OrderMedicineFragment extends Fragment {

    private ActivityMedicineListBinding binding;
    MedicineListAdapter medicineListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = ActivityMedicineListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        List<String> medicineData = new ArrayList<>();
        medicineData.add("paracetamol");
        medicineData.add("dolo 650");
        medicineData.add("paracetamol");
        medicineData.add("betadin");

        binding.rvMedicinelist.setLayoutManager(new LinearLayoutManager(getContext()));
        medicineListAdapter = new MedicineListAdapter(getContext() ,medicineData);
        binding.rvMedicinelist.setAdapter(medicineListAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}