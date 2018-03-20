package com.example.android.fitnessclubapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class HistoryFragment extends Fragment {

    private List<HistoryObject> list;
    private HistoryAdapter adapter;
    private RecyclerView recyclerView;
    private DBHelper helper;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = view.findViewById(R.id.recycler_view1);
        helper = new DBHelper(getContext());
        list = helper.returnAllRecords();
        if(!list.isEmpty()){
            adapter = new HistoryAdapter(getContext(),list);
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

}
