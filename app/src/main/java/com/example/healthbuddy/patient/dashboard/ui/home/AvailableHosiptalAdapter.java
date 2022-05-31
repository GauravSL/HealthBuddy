package com.example.healthbuddy.patient.dashboard.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.webservices.model.AmbulanceDetails;
import com.example.healthbuddy.webservices.model.HospitalDetails;

import java.util.ArrayList;

public class AvailableHosiptalAdapter extends RecyclerView.Adapter<AvailableHosiptalAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<HospitalDetails> hospitalDetails;

    public AvailableHosiptalAdapter(Context context, ArrayList<HospitalDetails> hospitalDetails){
        this.context = context;
        this.hospitalDetails = hospitalDetails;
    }

    @NonNull
    @Override
    public AvailableHosiptalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.hospital_list, parent, false);
        return new AvailableHosiptalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableHosiptalAdapter.ViewHolder holder, int position) {
        HospitalDetails hospitalDetail = hospitalDetails.get(position);
        holder.txtHospitalName.setText(hospitalDetail.getHospital_name());
        holder.txtHospital_contact.setText(hospitalDetail.getHospital_contact());
        holder.txtHospital_Timings.setText(hospitalDetail.getHospital_timing());
        holder.txt_hospital_Avail_Address.setText(hospitalDetail.getHospital_address());

    }

    @Override
    public int getItemCount() {
        return hospitalDetails.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtHospitalName,txtHospital_contact,txtHospital_Timings,txt_hospital_Avail_Address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHospitalName = itemView.findViewById(R.id.txtHospitalName);
            txtHospital_contact = itemView.findViewById(R.id.txtHospital_contact);
            txtHospital_Timings = itemView.findViewById(R.id.txtHospital_Timings);
            txt_hospital_Avail_Address = itemView.findViewById(R.id.txt_hospital_Avail_Address);
        }
    }
}
