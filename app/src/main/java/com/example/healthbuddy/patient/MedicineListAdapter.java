package com.example.healthbuddy.patient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.webservices.model.MedicineDetails;

import java.util.ArrayList;
import java.util.List;

public class MedicineListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<MedicineDetails> medicineList;

    public MedicineListAdapter(Context context, ArrayList<MedicineDetails> medicineList) {
        this.context = context;
        this.medicineList = medicineList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.medicine_list_adapter_item, parent, false);
        return new MedicineView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MedicineDetails medicineDetails = medicineList.get(position);
        MedicineView view = (MedicineView) holder;
        view.medicineName.setText(medicineDetails.getMedicine_name());
        view.medicineQuant.setText("Available Quantity :- " + medicineDetails.getAvailable_quantity());
        if (medicineDetails.getMedicine_type().equalsIgnoreCase("Syrup")) {
            view.medicineType.setBackgroundResource(R.drawable.syrup);
        }
        view.plusButton.setOnClickListener(v -> {
            if (Integer.parseInt(view.orderQuantity.getText().toString()) < Integer.parseInt(medicineDetails.getAvailable_quantity())) {
                int quantity = Integer.parseInt(view.orderQuantity.getText().toString()) + 1;
                view.orderQuantity.setText(quantity + "");
                medicineList.get(position).setOrderMedicineQuantity(quantity);
            }
        });

        view.minusButton.setOnClickListener(v -> {
            if (Integer.parseInt(view.orderQuantity.getText().toString()) != 0) {
                int quantity = Integer.parseInt(view.orderQuantity.getText().toString()) - 1;
                view.orderQuantity.setText(quantity + "");
                medicineList.get(position).setOrderMedicineQuantity(quantity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    public List<MedicineDetails> getMedicineToOrder() {

        List<MedicineDetails> orderMed = new ArrayList<MedicineDetails>();
        for (MedicineDetails medicineDetails : medicineList) {
            if (medicineDetails.getOrderMedicineQuantity() > 0) {
                orderMed.add(medicineDetails);
            }
        }
        return orderMed;
    }

    public static class MedicineView extends RecyclerView.ViewHolder {
        TextView medicineName, medicineQuant, minusButton, orderQuantity, plusButton;
        ImageView medicineType;

        public MedicineView(@NonNull View itemView) {
            super(itemView);
            medicineName = itemView.findViewById(R.id.tv_medicines);
            medicineQuant = itemView.findViewById(R.id.tv_Avail_Quantity);
            medicineType = itemView.findViewById(R.id.iv_medicine_type);
            minusButton = itemView.findViewById(R.id.minusButton);
            orderQuantity = itemView.findViewById(R.id.orderQuantity);
            plusButton = itemView.findViewById(R.id.plusButton);
        }
    }
}
