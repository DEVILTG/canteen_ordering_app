package com.canteen_ordering;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class coke_activity  extends AppCompatActivity {
    Button add_more;
    Button minus;
    Button plus;
    Button place_order;

    EditText eneter;


    public void savedata(){

        String amount_coke = eneter.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("amount_coke", amount_coke);
        editor.apply();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coke_order);



        plus = findViewById(R.id.coke_plus);
        minus = findViewById(R.id.coke_minus);
        eneter = findViewById(R.id.coke_amount_entered);
        place_order = findViewById(R.id.coke_final_click);
        add_more = findViewById(R.id.coke_add_more);




        eneter.setText("0");


        minus.setOnClickListener(
                v -> {
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
        );


        plus.setOnClickListener(
                v -> {
                    int curr = Integer.parseInt(eneter.getText().toString());
                    int cur;
                    if(curr >=10){
                        Toast.makeText(coke_activity.this, "Itna kon order krta hai be", Toast.LENGTH_SHORT).show();
                        cur = 10;
                    }
                    else {
                        cur = curr + 1;
                    }
                    String curstr = Integer.toString(cur);
                    eneter.setText(curstr);
                }
        );


        //sending data to main activity
        add_more.setOnClickListener(
                v -> {
                    savedata();
                    onBackPressed();
                }
        );



        place_order.setOnClickListener(
                v -> {
                    savedata();
                    Intent billing= new Intent(coke_activity.this, billing_activity.class);
                    startActivity(billing);
                }
        );



    }
}
