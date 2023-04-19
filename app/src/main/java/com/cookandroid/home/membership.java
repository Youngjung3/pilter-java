package com.cookandroid.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class membership extends login{

    private ArrayAdapter adapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership);


        spinner = (Spinner)findViewById(R.id.ageSpinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.age,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button sign = (Button)findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(membership.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }


}
