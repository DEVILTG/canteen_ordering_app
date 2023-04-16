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


public class aaloo_poha_activity  extends AppCompatActivity {
    Button add_more;
    Button minus;
    Button plus;
    Button place_order;

    EditText eneter;
    Switch plate_check;

    public String full = "Full Plate";

    public void savedata(){
        String full_plate_poha = full;
        String amount_poha = eneter.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("full_plate_poha", full_plate_poha);
        editor.putString("amount_poha", amount_poha);
        editor.apply();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aaloo_poha_order);





        plate_check= findViewById(R.id.poha_full_half);
        plus = findViewById(R.id.poha_plus);
        minus = findViewById(R.id.poha_minus);
        eneter = findViewById(R.id.poha_amount_entered);
        place_order = findViewById(R.id.poha_final_click);
        add_more = findViewById(R.id.poha_add_more);




        eneter.setText("0");


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


        plus.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int curr = Integer.parseInt(eneter.getText().toString());
                        int cur;
                        if(curr >=10){
                            Toast.makeText(aaloo_poha_activity.this, "Itna kon order krta hai be", Toast.LENGTH_SHORT).show();
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


        plate_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // true if the switch is in the On position
                if (isChecked) {
                    full = "Half Plate";
                }
            }
        });








        //sending data to main activity
        add_more.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        savedata();
                        onBackPressed();
                    }
                }
        );



        place_order.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        savedata();
                        Intent billing= new Intent(aaloo_poha_activity.this, billing_activity.class);
                        startActivity(billing);
                    }
                }
        );



    }
}
