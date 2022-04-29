package com.example.healthbuddy.patient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.dashboard.doctor.navigation.appointment.adapter.DoctorOfflineAppointmentListAdapter;

import java.util.List;

public class MedicineListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
  private   Context context ;
    private  List <String> medincineData;
public MedicineListAdapter(Context context , List <String> medincineData){
    this.context = context;
    this.medincineData = medincineData;
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
String sMedicineName = medincineData.get(position);
        MedicineView view  = (MedicineView)holder;
        view.medicineName.setText(sMedicineName);
        view.medicineQuant.setText(position+"");
    }

    @Override
    public int getItemCount() {
        return medincineData.size();
    }
    public class MedicineView extends RecyclerView.ViewHolder{
        TextView medicineName;
        TextView medincineType;
        TextView medicineQuant;

        public MedicineView(@NonNull View itemView) {
            super(itemView);
            medicineName = itemView.findViewById(R.id.tv_medicines);

            medicineQuant = itemView.findViewById(R.id.tv_Avail_Quabtity);
        }
    }
}
