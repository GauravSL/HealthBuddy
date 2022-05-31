package com.example.healthbuddy.dashboard.doctor.navigation.appointment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;
import com.example.healthbuddy.webservices.model.ChatDetails;
import com.example.healthbuddy.webservices.model.DoctorAppointmentDetails;

import java.util.ArrayList;

public class DoctorChatAdapter extends RecyclerView.Adapter<DoctorChatAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ChatDetails> chatDetails;
    private boolean isDoctor;

    public DoctorChatAdapter(Context context, ArrayList<ChatDetails> chatDetails, boolean isDoctor){
        this.context = context;
        this.chatDetails = chatDetails;
        this.isDoctor = isDoctor;
    }

    @NonNull
    @Override
    public DoctorChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.sender_chat_messages, parent, false);
        return new DoctorChatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorChatAdapter.ViewHolder holder, int position) {
        ChatDetails chatDetail = chatDetails.get(position);
        if(isDoctor) {
            if (chatDetail.getSent_by().equalsIgnoreCase("Doctor")) {
                holder.tv_message_sender.setText(chatDetail.getMessage_text() + "\n" +chatDetail.getMessage_time().split("T")[0] +" "+chatDetail.getMessage_time().split("T")[1]);
                holder.tv_message_receiver.setVisibility(View.GONE);
            } else if (chatDetail.getSent_by().equalsIgnoreCase("User")) {
                holder.tv_message_receiver.setText(chatDetail.getMessage_text() + "\n" +chatDetail.getMessage_time().split("T")[0] +" "+chatDetail.getMessage_time().split("T")[1]);
                holder.tv_message_sender.setVisibility(View.GONE);
            }
        }else{
            if (chatDetail.getSent_by().equalsIgnoreCase("User")) {
                holder.tv_message_sender.setText(chatDetail.getMessage_text() + "\n" +
                        chatDetail.getMessage_time().split("T")[0] +" "+chatDetail.getMessage_time().split("T")[1]);
                holder.tv_message_receiver.setVisibility(View.GONE);
            } else if (chatDetail.getSent_by().equalsIgnoreCase("Doctor")) {
                holder.tv_message_receiver.setText(chatDetail.getMessage_text() + "\n" +chatDetail.getMessage_time().split("T")[0] +" "+chatDetail.getMessage_time().split("T")[1]);
                holder.tv_message_sender.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public int getItemCount() {
        return chatDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_message_receiver, tv_message_sender;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_message_receiver = itemView.findViewById(R.id.tv_message_receiver);
            tv_message_sender = itemView.findViewById(R.id.tv_message_sender);
        }
    }
}
