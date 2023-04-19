package com.cookandroid.home;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class Fragment2 extends Fragment {

    private List<String> list;
    private ListView listView;
    private EditText editSearch;
    private SearchAdapter adapter;
    private ArrayList<String> arrayList;


    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment2, container, false);

        editSearch = (EditText) view.findViewById(R.id.editSearch);
        listView = (ListView) view.findViewById(R.id.listView);

        list = new ArrayList<String>();

        settingList();

        arrayList = new ArrayList<String>();
        arrayList.addAll(list);

        adapter = new SearchAdapter(list, getActivity());

        listView.setAdapter(adapter);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String text = editSearch.getText().toString();
                search(text);

            }
        });

        return view;
    }
    public void search(String charText){
        list.clear();

        if(charText.length() == 0){
            list.addAll(arrayList);
        }
        else {
            for(int i = 0; i<arrayList.size(); i++)
            {
                if(arrayList.get(i).toLowerCase().contains(charText))
                {
                    list.add(arrayList.get(i));
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
    private void settingList(){
        list.add("타이레놀정");
        list.add("중외5%포도당생리식염액");
        list.add("영신단");
        list.add("제일포도당주사액");
        list.add("비오베이비");
        list.add("안중환");
        list.add("테라싸이클린캅셀250밀리그람");
        list.add("다이크로짇정");
        list.add("헤로세친주");
        list.add("우먼스 타이레놀정");
        list.add("페니라민정");
        list.add("푸라콩정");
        list.add("디고신정");
        list.add("온보왕");
        list.add("어린이용 타이레놀정");
        list.add("라식스정");
        list.add("원비디");
    }

}
