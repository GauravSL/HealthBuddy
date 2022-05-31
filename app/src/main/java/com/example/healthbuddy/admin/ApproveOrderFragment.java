package com.example.healthbuddy.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.R;

import java.util.ArrayList;

public class ApproveOrderFragment extends Fragment {


    public ApproveOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);



        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
         ApproveOrderAdapter adapter = new ApproveOrderAdapter(getContext(), null);
           recyclerView.setAdapter(adapter);
        return view;
    }

}