package com.cookandroid.home;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;


public class Fragment4 extends Fragment {

    private ArrayAdapter adapter;
    private Spinner spinner;


    public Fragment4() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment4, container, false);

        spinner = (Spinner) view.findViewById(R.id.ageSpinner2);
        adapter = ArrayAdapter.createFromResource(getActivity(),R.array.age,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

     return view;


    }
}

