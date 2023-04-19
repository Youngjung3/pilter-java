package com.cookandroid.home;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Fragment1 extends Fragment {

    public Fragment1() {
        // Required empty public constructor
    }
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    CalendarView calendarView;
    TextView day_text;
    FloatingActionButton plus_btn ,memo_btn , alarm_btn;
    long now = System.currentTimeMillis();
    Date mDate = new Date(now);
    SimpleDateFormat simpleDate = new SimpleDateFormat("M월 d일");
    String day = simpleDate.format(mDate);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment1, container, false);

        fab_open = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);

        calendarView = view.findViewById(R.id.calendarView);
        day_text = view.findViewById(R.id.day_text);
        day_text.setText(day);

        plus_btn = view.findViewById(R.id.plus_btn);
        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();
            }
        });
        memo_btn = view.findViewById(R.id.memo_btn);
        memo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();
                Intent intent = new Intent(getContext(),AddActivity.class);
                intent.putExtra("day",day);
                startActivity(intent);
            }
        });
        alarm_btn = view.findViewById(R.id.alarm_btn);
        alarm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();
                Intent intent = new Intent(getContext(),AlarmActivity.class);
                startActivity(intent);
            }
        });




        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month +=1;
                day = String.format("%d월 %d일" , month,dayOfMonth);
                day_text.setText(day);
            }
        });
        return view;
    }
    public void anim() {

        if (isFabOpen) {
            alarm_btn.startAnimation(fab_close);
            memo_btn.startAnimation(fab_close);
            alarm_btn.setClickable(false);
            memo_btn.setClickable(false);
            isFabOpen = false;
        } else {
            alarm_btn.startAnimation(fab_open);
            memo_btn.startAnimation(fab_open);
            alarm_btn.setClickable(true);
            memo_btn.setClickable(true);
            isFabOpen = true;
        }
    }
}
