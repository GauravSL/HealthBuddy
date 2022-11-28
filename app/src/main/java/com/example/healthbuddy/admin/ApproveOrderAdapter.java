package com.example.healthbuddy.admin;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.dashboard.doctor.navigation.appointment.SelectedAppointmentActivity;
import com.example.healthbuddy.webservices.Response;
import com.example.healthbuddy.webservices.ServerDataTransfer;
import com.example.healthbuddy.webservices.model.DoctorAppointmentDetails;
import com.example.healthbuddy.webservices.model.OrderDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ApproveOrderAdapter extends RecyclerView.Adapter<ApproveOrderAdapter.ViewHolder> {

    private Context context;
    private ArrayList<OrderDetails> orderDetails;
    public ApproveOrderAdapter(Context context,  ArrayList<OrderDetails> orderDetails){
        this.context = context;
        this.orderDetails = orderDetails;
    }

    @NonNull
    @Override
    public ApproveOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.admin_order_approval, parent, false);
        return new ApproveOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApproveOrderAdapter.ViewHolder holder, int position) {
    OrderDetails orderDetail = orderDetails.get(position);
    holder.txt_order_id.setText(orderDetail.getOrder_id());
    holder.txt_order_date.setText(orderDetail.getOrder_date());
    //holder.txt_order_id.setText(orderDetail.o());
        holder.btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = holder.getAdapterPosition();
                callAcceptDoctor(orderDetails.get(holder.getAdapterPosition()).getId());
            }
        });

        holder.btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderDetails.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_order_id, txt_order_id_contact, txt_order_address,txt_order_date;
        AppCompatButton btn_reject, btn_accept;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_order_id = itemView.findViewById(R.id.txt_order_id);
            txt_order_id_contact = itemView.findViewById(R.id.txt_order_id_contact);
            txt_order_address = itemView.findViewById(R.id.txt_order_address);
            txt_order_date = itemView.findViewById(R.id.txt_order_date);
            btn_reject = itemView.findViewById(R.id.btn_reject);
            btn_accept = itemView.findViewById(R.id.btn_accept);
        }
    }

    AsyncTask<Void, Void, Response> asyncTaskGetAppointments;
    private void callAcceptDoctor(String order_id){
        asyncTaskGetAppointments = new AsyncTask<Void, Void, Response>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // binding.progressDialog.setVisibility(View.VISIBLE);
            }

            @Override
            protected Response doInBackground(Void... strings) {
                ServerDataTransfer dataTransfer = new ServerDataTransfer();
                Response response = null;
                try {
                    JSONObject json = new JSONObject();
                    json.put("order_id", order_id);
                    response = dataTransfer.accessAPI("deliverOrder","POST",json.toString());
                } catch (IOException | JSONException exception) {
                    exception.printStackTrace();
                }

                return response;
            }

            @Override
            protected void onPostExecute(Response response) {
                super.onPostExecute(response);
                processResponse(response);
            }
        };
        asyncTaskGetAppointments.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    private int selectedPosition;

    private void processResponse(Response response){
        Toast.makeText(context, response.getResponse(), Toast.LENGTH_LONG).show();
        if (response.getStatusCode()==200){
            orderDetails.remove(selectedPosition);
            notifyItemRemoved(selectedPosition);
        }
    }
}

