package com.example.caycanh.Frame.show_information;

import android.view.View;
import android.widget.TextView;

import com.example.caycanh.R;

public class CountNumberProduct {
    private TextView number;
    private final int MAX_NUMBER = 99;
    private  int number_counter = 0;

    public CountNumberProduct(View view){
        number = view.findViewById(R.id.tv_number);
    }

    public void setNumberProduct(int n){
        if(n > MAX_NUMBER){
            number.setText("99+");
            return;
        }
        number.setText(String.valueOf(n));
    }
}
