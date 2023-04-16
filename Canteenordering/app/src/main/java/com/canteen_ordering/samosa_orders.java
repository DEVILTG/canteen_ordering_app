package com.canteen_ordering;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class samosa_orders  extends AppCompatActivity {
    Button add_more;
    Button minus;
    Button plus;
    Button place_order;

    EditText eneter;
    Switch plate_check;

    public String full= "Full plate";

    public void savedata(){
        String full_plate_samosa = full;
        String amount_samosa = eneter.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("full_plate_samosa", full_plate_samosa);
        editor.putString("amount_samosa", amount_samosa);
        editor.apply();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.samosa_order);



        eneter = findViewById(R.id.sam_amount_entered);
        eneter.setText("0");

        minus = findViewById(R.id.sam_minus);
        minus.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int curr = Integer.parseInt(eneter.getText().toString());
                        int cur = curr - 1;
                        String curstr = Integer.toString(cur);
                        if(cur<0){
                            eneter.setText("0");
                        }
                        else {
                            eneter.setText(curstr);
                        }

                    }
                }
        );

        plus = findViewById(R.id.sam_plus);
        plus.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int curr = Integer.parseInt(eneter.getText().toString());
                        int cur;
                        if(curr >=10){
                            Toast.makeText(samosa_orders.this, "Itna kon order krta hai be", Toast.LENGTH_SHORT).show();
                             cur = 10;
                        }
                        else {
                            cur = curr + 1;
                        }
                        String curstr = Integer.toString(cur);
                        eneter.setText(curstr);
                    }
                }
        );

        plate_check= findViewById(R.id.full_half);
        plate_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // true if the switch is in the On position
                if (isChecked) {
                    full = "Half Plate";
                }
            }
        });








        //sending data to main activity
        add_more = findViewById(R.id.add_more);
        add_more.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        savedata();
                        onBackPressed();
                    }
                }
        );

        place_order = findViewById(R.id.final_click);

        place_order.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        savedata();
                        Intent billing= new Intent(samosa_orders.this, billing_activity.class);
                        startActivity(billing);
                    }
                }
        );



    }
}
