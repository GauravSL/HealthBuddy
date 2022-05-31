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
import com.example.healthbuddy.webservices.model.BedDetails;

import java.util.ArrayList;

public class AvailableBedAdapter extends RecyclerView.Adapter<AvailableBedAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<BedDetails> bedDetailsArrayList;

    public AvailableBedAdapter(Context context, ArrayList<BedDetails> bedDetailsArrayList){
        this.context = context;
        this.bedDetailsArrayList = bedDetailsArrayList;
    }

    @NonNull
    @Override
    public AvailableBedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.bed_availability_list, parent, false);
        return new AvailableBedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableBedAdapter.ViewHolder holder, int position) {
        BedDetails bedDetails = bedDetailsArrayList.get(position);
       holder.txtHospital_bed_Avail.setText(bedDetails.getHospital_name());
       holder.txt_Genaral_bed_avail.setText(bedDetails.getGeneral_bed());
       holder.txt_Special_beds_avail.setText(bedDetails.getSpecial_bed());
       holder.txt_ICU_beds_avail.setText(bedDetails.getIcu_bed());
       holder.txt_bed_avail_address.setText(bedDetails.getHospital_address());
    }

    @Override
    public int getItemCount() {
        return bedDetailsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtHospital_bed_Avail,txt_Genaral_bed_avail,txt_Special_beds_avail,txt_ICU_beds_avail,txt_bed_avail_address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHospital_bed_Avail = itemView.findViewById(R.id.txtHospital_bed_Avail);
            txt_Genaral_bed_avail = itemView.findViewById(R.id.txt_Genaral_bed_avail);
            txt_Special_beds_avail = itemView.findViewById(R.id.txt_Special_beds_avail);
            txt_ICU_beds_avail = itemView.findViewById(R.id.txt_ICU_beds_avail);
            txt_bed_avail_address = itemView.findViewById(R.id.txt_bed_avail_address);
        }
    }
}
